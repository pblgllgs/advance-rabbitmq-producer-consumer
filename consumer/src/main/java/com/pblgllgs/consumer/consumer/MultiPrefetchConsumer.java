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

@Service
@Slf4j
public class MultiPrefetchConsumer {

    @RabbitListener(queues = "q.transaction", concurrency = "2",containerFactory = "prefetchFiveContainerFactory")
    public void consumerTransaction(DummyMessage dummyMessage) throws InterruptedException {
        log.info(dummyMessage.toString());
        TimeUnit.MILLISECONDS.sleep(250);
    }

    @RabbitListener(queues = "q.scheduler", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
    public void consumerScheduler(DummyMessage dummyMessage) throws InterruptedException {
        log.info(dummyMessage.toString());
        TimeUnit.MINUTES.sleep(1);
    }
}
