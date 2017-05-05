package org.hyperledger.monitoring.model;

public class ProfileInfo {
    private final String configPath;
    private final String urlInfluxDB;
    private final String urlGrafana;
    private final String dashboardsGrafana;
    private final String datasourcesGrafana;

    public ProfileInfo(String configPath, String urlInfluxDB, String urlGrafana, String dashboardsGrafana, String datasourcesGrafana) {
        this.configPath = configPath;
        this.urlInfluxDB = urlInfluxDB;
        this.urlGrafana = urlGrafana;
        this.dashboardsGrafana = dashboardsGrafana;
        this.datasourcesGrafana = datasourcesGrafana;
    }

    public String getConfigPath() {
        return configPath;
    }

    public String getUrlInfluxDB() {
        return urlInfluxDB;
    }

    public String getUrlGrafana() {
        return urlGrafana;
    }

    public String getDashboardsGrafana() {
        return dashboardsGrafana;
    }

    public String getDatasourcesGrafana() {
        return datasourcesGrafana;
    }
}
