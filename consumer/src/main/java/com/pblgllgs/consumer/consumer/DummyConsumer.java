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

@Service
@Slf4j
public class DummyConsumer {

    @RabbitListener(queues = "q.dummy")
    public void consumerDummy(DummyMessage dummyMessage){
        log.info(dummyMessage.toString());
    }
}
