package org.hyperledger.monitoring;

import org.hyperledger.fabric.client.fly.spring.config.FlyConfig;
import org.hyperledger.monitoring.config.InfluxDBConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Import({InfluxDBConfig.class, FlyConfig.class})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}

