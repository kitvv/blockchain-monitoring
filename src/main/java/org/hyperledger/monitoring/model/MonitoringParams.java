package org.hyperledger.monitoring.model;

/**
 * Created by Ruslan Kryukov on 04/05/2017.
 */
public class MonitoringParams {
    private final String configPath;
    private final String urlInfluxDB;
    private final String urlGrafana;
    private final String dashboardsGrafana;
    private final String datasourcesGrafana;
    private final String usernameInfluxDB;
    private final String passwordInfluxDB;

    public MonitoringParams(String configPath, String urlInfluxDB, String urlGrafana, String dashboardsGrafana, String datasourcesGrafana, String usernameInfluxDB, String passwordInfluxDB) {
        this.configPath = configPath;
        this.urlInfluxDB = urlInfluxDB;
        this.urlGrafana = urlGrafana;
        this.dashboardsGrafana = dashboardsGrafana;
        this.datasourcesGrafana = datasourcesGrafana;
        this.usernameInfluxDB = usernameInfluxDB;
        this.passwordInfluxDB = passwordInfluxDB;
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

    public String getUsernameInfluxDB() {
        return usernameInfluxDB;
    }

    public String getPasswordInfluxDB() {
        return passwordInfluxDB;
    }
}
