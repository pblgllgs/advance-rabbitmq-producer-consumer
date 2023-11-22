package com.pblgllgs.consumer;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
 *
 */

import com.pblgllgs.entities.DummyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SingleActiveConsumer {

    @RabbitListener(queues = "q.single", concurrency = "5")
    public void handleInvoiceCreated(DummyMessage message) throws InterruptedException {
        log.info("Queue x.single : {}", message);
        TimeUnit.SECONDS.sleep(1);
    }
}
