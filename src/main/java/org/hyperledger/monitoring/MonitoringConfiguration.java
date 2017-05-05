package org.hyperledger.monitoring;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hyperledger.fabric.client.fly.spring.FlyNet;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.monitoring.api.InfluxWriter;
import org.hyperledger.monitoring.model.ProfileInfo;
import org.hyperledger.monitoring.model.grafana.dashboard.Dashboard;
import org.hyperledger.monitoring.model.grafana.dashboard.Panel;
import org.hyperledger.monitoring.model.grafana.dashboard.Row;
import org.hyperledger.monitoring.model.grafana.dashboard.Target;
import org.hyperledger.monitoring.model.grafana.datasource.Datasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

// TODO CHANGE TO @Configuration GrafanaConfiguration, InfluxConfiguration
@Component
public class MonitoringConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MonitoringConfiguration.class);
    private static final String DATABASE_NAME = "hyperledger";
    private static final String DASHBOARD_TITLE = "Monitoring Hyperledger";

    @Autowired
    private InfluxWriter influxWriter;

    @Autowired
    private ProfileInfo activeProfileInfo;

    @Autowired
    private FlyNet flyNet;

    private ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    @PostConstruct
    public void init() {
        initInflux();
        initGrafana();
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
        final File datasourcesTemplate = new File(activeProfileInfo.getDatasourcesGrafana());
        final Datasource datasource = mapper.readValue(datasourcesTemplate, Datasource.class);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic YWRtaW46YWRtaW4=");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Datasource> request = new HttpEntity<>(datasource, headers);
        ;
        final String datasourcesURL = activeProfileInfo.getUrlGrafana() + "/api/datasources";
        restTemplate.postForObject(datasourcesURL, request, String.class);
        log.info("finish init grafana datasources");
    }

    private void initDashboards() throws IOException {
        log.info("start init grafana dashboards");
        final File datasourcesTemplate = new File(activeProfileInfo.getDashboardsGrafana());
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

        final String dashboardsURL = activeProfileInfo.getUrlGrafana() + "/api/dashboards/db";
        restTemplate.postForObject(dashboardsURL, request, String.class);
        log.info("finish init grafana dashboards");
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

