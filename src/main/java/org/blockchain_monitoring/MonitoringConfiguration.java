package org.blockchain_monitoring;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.blockchain_monitoring.api.InfluxWriter;
import org.blockchain_monitoring.fly_client_spring.FlyNet;
import org.blockchain_monitoring.fly_client_spring.event.EventsProcessor;
import org.blockchain_monitoring.model.MonitoringParams;
import org.blockchain_monitoring.model.grafana.dashboard.Dashboard;
import org.blockchain_monitoring.model.grafana.dashboard.Panel;
import org.blockchain_monitoring.model.grafana.dashboard.Row;
import org.blockchain_monitoring.model.grafana.dashboard.Target;
import org.blockchain_monitoring.model.grafana.datasource.Datasource;
import org.hyperledger.fabric.protos.common.Common;
import org.hyperledger.fabric.protos.msp.Identities;
import org.hyperledger.fabric.protos.peer.FabricTransaction;
import org.hyperledger.fabric.sdk.BlockListener;
import org.hyperledger.fabric.sdk.Peer;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sun.security.x509.X500Name;

// TODO CHANGE TO @Configuration GrafanaConfiguration, InfluxConfiguration
@Component
public class MonitoringConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MonitoringConfiguration.class);
    private static final String DATABASE_NAME = "hyperledger";
    private static final String DASHBOARD_TITLE = "Monitoring Hyperledger";

    private final InfluxWriter influxWriter;

    private final MonitoringParams monitoringParams;

    private final FlyNet flyNet;

    private final EventsProcessor eventsProcessor;

    private ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    @Autowired
    public MonitoringConfiguration(InfluxWriter influxWriter, MonitoringParams monitoringParams,
                                   FlyNet flyNet, EventsProcessor eventsProcessor) {
        this.influxWriter = influxWriter;
        this.monitoringParams = monitoringParams;
        this.flyNet = flyNet;
        this.eventsProcessor = eventsProcessor;
    }

    @PostConstruct
    public void init() {
        initInflux();
        initGrafana();
        initEventHandlers();
    }

    private void initInflux() {
        influxWriter.createDatabase(DATABASE_NAME);
    }

    private void initGrafana() {
        try {
            initDatasources();
            initDashboards();
        } catch (HttpClientErrorException e) {
            final String responseBodyAsString = e.getResponseBodyAsString();
            log.error(responseBodyAsString);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void initDatasources() throws IOException {
        log.info("start init grafana datasources");
        final File datasourcesTemplate = new File(monitoringParams.getDatasourcesGrafana());
        final Datasource datasource = mapper.readValue(datasourcesTemplate, Datasource.class);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Datasource> request = new HttpEntity<>(datasource, headers);

        final String datasourcesURL = monitoringParams.getUrlGrafana() + "/api/datasources";
        restTemplate.postForObject(datasourcesURL, request, String.class);
        log.info("finish init grafana datasources");
    }

    private void initDashboards() throws IOException {
        log.info("start init grafana dashboards");
        final File datasourcesTemplate = new File(monitoringParams.getDashboardsGrafana());
        final Dashboard dashboard = mapper.readValue(datasourcesTemplate, Dashboard.class);

        // TODO динамичая загрузка борды из flyNet
        dashboard.getDashboardInfo().setTitle(DASHBOARD_TITLE);
        // Individual
        final Row statusRow = dashboard.getDashboardInfo().getRows().get(0);
        final Row channelRow = dashboard.getDashboardInfo().getRows().get(1);
        final Row chaincodeRow = dashboard.getDashboardInfo().getRows().get(2);

        final List<Peer> allPeers = new ArrayList<>();
        flyNet.getOrganisations().forEach(organisation -> {
            final List<Peer> peers = organisation.getPeers();
            allPeers.addAll(peers);
        });
        try {
            fillStatusRow(statusRow, allPeers);
            fillChannelRow(channelRow, allPeers);
            fillChaincodeRow(chaincodeRow, allPeers);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // Common
//        final Row queryRow = dashboard.getDashboard().getRows().get(3);
//        final Row invokeRow = dashboard.getDashboard().getRows().get(4);
//        TODO add Event Listener
//        final Row eventHubRow = dashboard.getDashboard().getRows().get(5);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Dashboard> request = new HttpEntity<>(dashboard, headers);

        final String dashboardsURL = monitoringParams.getUrlGrafana() + "/api/dashboards/db";
        restTemplate.postForObject(dashboardsURL, request, String.class);
        log.info("finish init grafana dashboards");
    }

    private void initEventHandlers() {
        BlockListener metricsEventListener = blockEvent -> {
            List<FabricTransaction.TxValidationCode> resultValidationList = blockEvent.getTransactionEvents().parallelStream()
                    .map(transactionEvent -> FabricTransaction.TxValidationCode.forNumber(transactionEvent.validationCode()))
                    .collect(Collectors.toList());

            List<String> endorsementsList;
            try {
                endorsementsList = FabricTransaction.ChaincodeActionPayload.parseFrom(FabricTransaction.Transaction.parseFrom(Common.Payload.parseFrom(
                        Common.Envelope.parseFrom(blockEvent.getBlock().getData().getData(0)).getPayload()).getData())
                        .getActions(0).getPayload()).getAction().getEndorsementsList().stream().map(endorsement -> {
                    try {
                        return Identities.SerializedIdentity.parseFrom(endorsement.getEndorser());
                    } catch (InvalidProtocolBufferException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).filter(Objects::nonNull)
                        .map(serializedIdentity -> {
                            final X509Certificate certificate;
                            String commonName = "";
                            try {
                                certificate = X509Certificate.getInstance(serializedIdentity.getIdBytes().toByteArray());
                                try {
                                    commonName = ((X500Name) certificate.getSubjectDN()).getCommonName();
                                    System.out.println("commonName: " + commonName);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (CertificateException e) {
                                e.printStackTrace();
                            }
                            return String.join(commonName, "(", serializedIdentity.getMspid(), ")");
                        }).collect(Collectors.toList());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
                endorsementsList = Collections.emptyList();
            }

            final String CHANNEL_ID = "CHANNEL_ID";
            final String TRANSACTION_ID = "TRANSACTION_ID";
            final String EVENTHUB_NAME = "EVENTHUB_NAME";
            final String EVENTHUB_URL = "EVENTHUB_URL";
            final String VALIDATION_RESULT = "VALIDATION_RESULT";
            final String ENDORSEMENTS = "ENDORSEMENTS";

            Point point = Point.measurement("blockEvent")
                    .tag(CHANNEL_ID, blockEvent.getChannelID())
                    .addField(EVENTHUB_NAME, blockEvent.getEventHub().getName())
                    .addField(EVENTHUB_URL, blockEvent.getEventHub().getUrl())
                    .addField(TRANSACTION_ID, blockEvent.getTransactionEvents().get(0).getTransactionID())
                    .addField(VALIDATION_RESULT, resultValidationList.toString())
                    .addField(ENDORSEMENTS, endorsementsList.toString())
                    .build();
            influxWriter.write(point);
        };

        eventsProcessor.addListener("metrics", metricsEventListener);
    }

    private void fillStatusRow(Row row, List<Peer> allPeers) throws CloneNotSupportedException {
        final String selectForPeer = "SELECT \"status\" FROM \"autogen\".\"%s\"";
        selectForPeers(row, allPeers, selectForPeer);
    }

    private void fillChannelRow(Row row, List<Peer> allPeers) throws CloneNotSupportedException {
        final String selectForPeer = "SELECT \"channel\" FROM \"autogen\".\"%s\" WHERE \"TAG_STATUS\" = 'UP'";
        selectForPeers(row, allPeers, selectForPeer);
    }

    private void fillChaincodeRow(Row row, List<Peer> allPeers) throws CloneNotSupportedException {
        final String selectForPeer = "SELECT \"chaincode\" FROM \"%s\" WHERE \"TAG_STATUS\" = 'UP'";
        selectForPeers(row, allPeers, selectForPeer);
    }

    private void selectForPeers(Row row, List<Peer> allPeers, String selectForPeer) throws CloneNotSupportedException {
        List<Panel> panels = new ArrayList<>();
        final List<Panel> panels1 = row.getPanels();
        int i = 0;
        for (Peer peer : allPeers) {
            final Panel templatePanel = panels1.get(0).clone();
            final Target templateTarget = templatePanel.getTargets().get(0).clone();
            templatePanel.setId(i);
            templateTarget.setMeasurement(peer.getName());
            final String query = String.format(selectForPeer, peer.getName());
            templateTarget.setQuery(query);
            templatePanel.setTargets(Collections.singletonList(templateTarget));
            templatePanel.setTitle(peer.getName());
            panels.add(templatePanel);
            i++;
        }
        row.setPanels(panels);
    }
}

