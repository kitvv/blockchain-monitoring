package org.blockchain_monitoring.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.blockchain_monitoring.model.MonitoringParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.StandardEnvironment;

@Configuration
@PropertySource("classpath:monitoring.properties")
public class ProfileConfig {

    private Log log = LogFactory.getLog(ProfileConfig.class);

    @Autowired
    private StandardEnvironment environment;

    @Bean
    public MonitoringParams activeProfileInfo() throws Exception {
        final String configPath = getProperty("fly.netconfig");
        final String urlInfluxDB = getProperty("influxDB.url");
        final String urlGrafana = getProperty("grafana.url");

        final String dashboardsGrafana = getProperty("grafana.dashboards");
        final String datasourcesGrafana = getProperty("grafana.datasources");
        final String orgPreferencesGrafana = getProperty("grafana.orgPreferences");

        final String usernameInfluxDB = getProperty("influxDB.username");
        final String passwordInfluxDB = getProperty("influxDB.password");

        final MonitoringParams monitoringParams = new MonitoringParams(configPath, urlInfluxDB, urlGrafana, dashboardsGrafana, datasourcesGrafana, usernameInfluxDB, passwordInfluxDB, orgPreferencesGrafana);
        log.info(monitoringParams.toString());
        return monitoringParams;
    }

    private String getPropertyFromRoot(String name) {
        return (String) environment.getPropertySources().get("class path resource [spring-fly-application.properties]").getProperty(name);
    }

    private String getProperty(String name) {
        final String applicationProperty = environment.getProperty(name);
        if (applicationProperty == null) {
            return getPropertyFromRoot(name);
        }

        return applicationProperty;
    }
}
