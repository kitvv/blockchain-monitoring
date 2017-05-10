package org.blockchain_monitoring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.blockchain_monitoring.model.MonitoringParams;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.blockchain_monitoring")
public class InfluxDBConfig {

    @Autowired
    private MonitoringParams monitoringParams;

    @Bean
    public InfluxDB influxDBFactory() {
        return InfluxDBFactory.connect(
                monitoringParams.getUrlInfluxDB(),
                monitoringParams.getUsernameInfluxDB(),
                monitoringParams.getPasswordInfluxDB());
    }

    @Bean
    public ObjectMapper jsonObjectMapper(){
        ObjectMapper m = new ObjectMapper();
        m.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return m;
    }
}
