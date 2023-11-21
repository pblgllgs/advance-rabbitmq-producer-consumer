package com.pblgllgs.consumer.scheduler;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@Slf4j
public class RabbitMQScheduler {

    private final RabbitListenerEndpointRegistry registry;

    public RabbitMQScheduler(@Qualifier("rabbitListenerEndpointRegistry") RabbitListenerEndpointRegistry registry) {
        this.registry = registry;
    }


    @Scheduled(cron = "0 23 18 * * *")
    public void stopAll() {
        registry.getListenerContainers().forEach(c -> {
            log.info("Stopping listener container {}", c);
            c.stop();
        });
    }

    @Scheduled(cron = "30 23 18 * * *")
    public void startAll() {
        registry.getListenerContainers().forEach(c -> {
            log.info("Stopping listener container {}", c);
            c.start();
        });
    }
}
