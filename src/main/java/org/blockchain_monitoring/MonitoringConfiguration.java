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
import org.blockchain_monitoring.api.InfluxSearcher;
import org.blockchain_monitoring.api.InfluxWriter;
import org.blockchain_monitoring.fly_client_spring.FlyNet;
import org.blockchain_monitoring.fly_client_spring.event.EventsProcessor;
import org.blockchain_monitoring.model.MonitoringParams;
import org.blockchain_monitoring.model.grafana.dashboard.Dashboard;
import org.blockchain_monitoring.model.grafana.dashboard.Panel;
import org.blockchain_monitoring.model.grafana.dashboard.Row;
import org.blockchain_monitoring.model.grafana.dashboard.Target;
import org.blockchain_monitoring.model.grafana.datasource.Datasource;
import org.blockchain_monitoring.model.grafana.datasource.OrgPreferences;
import org.hyperledger.fabric.protos.common.Common;
import org.hyperledger.fabric.protos.msp.Identities;
import org.hyperledger.fabric.protos.peer.FabricTransaction;
import org.hyperledger.fabric.sdk.BlockEvent;
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
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String TRANSACTION_ID = "TRANSACTION_ID";
    private static final String VALIDATION_RESULT_CODE = "VALIDATION_RESULT_CODE";
    private static final String VALIDATION_RESULT_NAME = "VALIDATION_RESULT_NAME";
    private static final String ENDORSEMENTS = "ENDORSEMENTS";
    private static final String COMMON_BLOCK_EVENT_MEASUREMENT = "commonBlockEvent";

    private final InfluxWriter influxWriter;

    private final InfluxSearcher influxSearcher;

    private final MonitoringParams monitoringParams;

    private final FlyNet flyNet;

    private final EventsProcessor eventsProcessor;

    private ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    @Autowired
    public MonitoringConfiguration(InfluxWriter influxWriter, InfluxSearcher influxSearcher, MonitoringParams monitoringParams,
                                   FlyNet flyNet, EventsProcessor eventsProcessor) {
        this.influxSearcher = influxSearcher;
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
        } catch (HttpClientErrorException e) {
            final String responseBodyAsString = e.getResponseBodyAsString();
            log.error(responseBodyAsString);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            initDashboards();
        } catch (HttpClientErrorException e) {
            final String responseBodyAsString = e.getResponseBodyAsString();
            log.error(responseBodyAsString);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            orgPreferences();
        } catch (HttpClientErrorException e) {
            final String responseBodyAsString = e.getResponseBodyAsString();
            log.error(responseBodyAsString);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void orgPreferences() throws IOException {
        log.info("start init grafana datasources");
        final File orgPreferencesTemplate = new File(monitoringParams.getOrgPreferencesGrafana());
        final OrgPreferences orgPreferences = mapper.readValue(orgPreferencesTemplate, OrgPreferences.class);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrgPreferences> request = new HttpEntity<>(orgPreferences, headers);

        final String orgPreferencesURL = monitoringParams.getUrlGrafana() + "/api/org/preferences";
        restTemplate.postForObject(orgPreferencesURL, request, String.class);
        log.info("finish init grafana OrgPreferences");
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
        dashboard.getDashboard().setTitle(DASHBOARD_TITLE);
        // Individual
        final Row statusRow = dashboard.getDashboard().getRows().get(0);
        final Row channelRow = dashboard.getDashboard().getRows().get(1);
        final Row chaincodeRow = dashboard.getDashboard().getRows().get(2);

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
            final byte validationCode = blockEvent.getTransactionEvents().iterator().next().getValidationCode();
            FabricTransaction.TxValidationCode validationResult = FabricTransaction.TxValidationCode.forNumber(validationCode);

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
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (CertificateException e) {
                                e.printStackTrace();
                            }
                            return commonName;
                        }).collect(Collectors.toList());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
                endorsementsList = Collections.emptyList();
            }

            StringBuilder transactionIds = new StringBuilder();
            blockEvent.getTransactionEvents().forEach(transactionEvent -> transactionIds.append(transactionEvent.getTransactionID()).append(","));
            writeCommonBlockEvent(blockEvent, validationResult, endorsementsList, transactionIds.toString());
            writeBlockEvent(blockEvent, validationResult, endorsementsList, transactionIds.toString());
        };

        eventsProcessor.addListener("metrics", metricsEventListener);
    }

    private void writeBlockEvent(BlockEvent blockEvent, FabricTransaction.TxValidationCode validationResult, List<String> endorsementsList, String transactionID) {
        try {
            Point point = Point.measurement(blockEvent.getEventHub().getName())
                    .tag(CHANNEL_ID, blockEvent.getChannelId())
                    .tag(TRANSACTION_ID, transactionID)
                    .addField(TRANSACTION_ID, transactionID)
                    .addField(VALIDATION_RESULT_NAME, validationResult.name())
                    .addField(VALIDATION_RESULT_CODE, validationResult.getNumber())
                    .addField(ENDORSEMENTS, endorsementsList.toString())
                    .build();
            influxWriter.write(point);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    private synchronized void writeCommonBlockEvent(BlockEvent blockEvent, FabricTransaction.TxValidationCode validationResult, List<String> endorsementsList, String transactionID) {
        boolean isCommonBlockEventExists = influxSearcher.query("SELECT * FROM \"" + COMMON_BLOCK_EVENT_MEASUREMENT + "\" WHERE \"" + TRANSACTION_ID + "\" = '" + transactionID + "' AND \"" + VALIDATION_RESULT_CODE + "\" = '" + validationResult.getNumber() + "'").get().getResults().get(0).getSeries() == null;
        if (isCommonBlockEventExists) {

            try {
                final String channelId = blockEvent.getChannelId();
                Point commonPoint = Point.measurement(COMMON_BLOCK_EVENT_MEASUREMENT)
                        .addField(CHANNEL_ID, channelId)
                        .addField(TRANSACTION_ID, transactionID)
                        .addField(VALIDATION_RESULT_NAME, validationResult.name())
                        .addField(VALIDATION_RESULT_CODE, validationResult.getNumber())
                        .addField(ENDORSEMENTS, endorsementsList.toString())
                        .build();
                influxWriter.write(commonPoint);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
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

