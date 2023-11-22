package com.pblgllgs.consumer.consumer;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.consumer.entities.DummyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
@Slf4j
public class DummyPrefetchConsumer {

    @RabbitListener(queues = "q.dummy", concurrency = "2")
    public void consumerDummy(DummyMessage dummyMessage) throws InterruptedException {
        log.info(dummyMessage.toString());
        TimeUnit.SECONDS.sleep(20);
    }
}
