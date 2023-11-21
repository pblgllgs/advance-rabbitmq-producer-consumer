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

@Service
@RequiredArgsConstructor
public class DummyProducer {

    @Value("${x.dummy}")
    private String exchange;

    private final RabbitTemplate rabbitTemplate;

    public void sendDummy(@Payload DummyMessage dummyMessage){
        rabbitTemplate.convertAndSend(exchange,"", dummyMessage);
    }

}
