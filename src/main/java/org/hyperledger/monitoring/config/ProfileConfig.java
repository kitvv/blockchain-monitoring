package org.hyperledger.monitoring.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperledger.monitoring.model.ProfileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Arrays;

@Configuration
@PropertySource("classpath:monitoring.properties")
public class ProfileConfig {

    private Log log = LogFactory.getLog(ProfileConfig.class);

    @Autowired
    private StandardEnvironment environment;

    @Bean
    public ProfileInfo activeProfileInfo() throws Exception {
        final boolean isLocal = Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("local")));
//        final boolean isDocker = Arrays.stream(environment.getActiveProfiles()).anyMatch(
//                env -> (env.equalsIgnoreCase("docker")));

        String type;
        if (isLocal) {
            type = "local";
        } else {
            type = "docker"; // default type
        }

        final String configPath = getProperty("fly.netconfig");
        final String urlInfluxDB = getProperty("influxDB.url." + type);
        final String urlGrafana = getProperty("grafana.url." + type);
        // TODO move to monitoring
        final String dashboardsGrafana = getProperty("grafana.dashboards." + type);
        final String datasourcesGrafana = getProperty("grafana.datasources." + type);

        final ProfileInfo profileInfo = new ProfileInfo(configPath, urlInfluxDB, urlGrafana, dashboardsGrafana, datasourcesGrafana);
        log.info(profileInfo.toString());
        return profileInfo;
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
