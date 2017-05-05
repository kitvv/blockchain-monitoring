package org.hyperledger.monitoring.scheduler;

import org.hyperledger.fabric.client.fly.spring.FlyNet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    public OrganisationMetricWriter metricWriter;

    @Autowired
    public FlyNet flyNet;

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        log.debug("START The time is now {}", dateFormat.format(new Date()));
        flyNet.getOrganisations().forEach(metricWriter);
        log.debug("END The time is now {}", dateFormat.format(new Date()));
    }
}
