package com.pblgllgs.producer;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
 *
 */

import com.pblgllgs.entities.DummyMessage;
import com.pblgllgs.entities.InvoiceCreatedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SingleActiveProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${x.single}")
    private String exchange;

    public void sendInvoice() {
        for (int i = 0; i < 10000; i++) {
            DummyMessage dummyMessage = new DummyMessage("Message " + i, i);
            rabbitTemplate.convertAndSend(exchange, "", dummyMessage);

        }
    }

}
