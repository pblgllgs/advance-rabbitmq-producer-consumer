package com.pblgllgs.consumer.consumer;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.consumer.entities.DummyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AnotherDummyConsumer {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            name = "q.dummy",
                            durable = "true"
                    ),
                    exchange = @Exchange(
                            name = "x.dummy",
                            type = ExchangeTypes.FANOUT, durable = "true"
                    ),
                    key = "routing-key",
                    ignoreDeclarationExceptions = "true"
            ))
    public void consumerDummy(DummyMessage dummyMessage) {
        log.info(dummyMessage.toString());
    }
}
