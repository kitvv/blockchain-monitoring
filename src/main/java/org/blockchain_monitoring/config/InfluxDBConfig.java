package org.blockchain_monitoring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.blockchain_monitoring.api.InfluxService;
import org.blockchain_monitoring.api.InfluxServiceImpl;
import org.blockchain_monitoring.api.ResultAdapter;
import org.blockchain_monitoring.api.annotation.invoke.InvokeMonitoring;
import org.blockchain_monitoring.model.MonitoringParams;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@ComponentScan("org.blockchain_monitoring")
public class InfluxDBConfig {

    @Autowired
    private MonitoringParams monitoringParams;

    @Bean
    public InfluxService influxDBFactory() {
        return new InfluxServiceImpl(
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

    @Bean
    public ResultAdapter resultAdapter() {
        return new ResultAdapter() {
            @Override
            public Optional<Point> apply(Object o, InvokeMonitoring invokeMonitoring) {
                return Optional.empty();
            }
        };
    }
}
