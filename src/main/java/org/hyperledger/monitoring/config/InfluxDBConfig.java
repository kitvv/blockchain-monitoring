package org.hyperledger.monitoring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hyperledger.monitoring.model.ProfileInfo;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.hyperledger.monitoring")
public class InfluxDBConfig {

    @Value("${influxDB.username}")
    private String usernameInfluxDB;
    @Value("${influxDB.password}")
    private String passwordInfluxDB;

    @Autowired
    private ProfileInfo profileInfo;

    @Bean
    public InfluxDB influxDBFactory() {
        return InfluxDBFactory.connect(profileInfo.getUrlInfluxDB(), usernameInfluxDB, passwordInfluxDB);
    }

    @Bean
    public ObjectMapper jsonObjectMapper(){
        ObjectMapper m = new ObjectMapper();
        m.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return m;
    }
}
