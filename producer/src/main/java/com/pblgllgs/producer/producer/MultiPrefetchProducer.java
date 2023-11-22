package com.pblgllgs.producer.producer;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.producer.entities.DummyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class MultiPrefetchProducer {

    @Value("${x.transaction}")
    private String exchangeTransaction;

    @Value("${x.scheduler}")
    private String exchangeScheduler;

    private final RabbitTemplate rabbitTemplate;


    public void simualteTransacion(){
        for (int i = 0; i < 20000; i++) {
            DummyMessage dummyMessage = new DummyMessage("Transaction" + LocalTime.now(), i);
            rabbitTemplate.convertAndSend(exchangeTransaction,"", dummyMessage);

        }
    }

    public void simualteScheduler(){
        for (int i = 0; i < 200; i++) {
            DummyMessage dummyMessage = new DummyMessage("Scheduler" + LocalTime.now(), i);
            rabbitTemplate.convertAndSend(exchangeScheduler,"", dummyMessage);

        }
    }

}
