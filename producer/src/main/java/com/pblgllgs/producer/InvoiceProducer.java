package com.pblgllgs.producer;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
 *
 */

import com.pblgllgs.entities.InvoiceCreatedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${x.invoice}")
    private String exchange;

    public void sendInvoice(InvoiceCreatedMessage message) {
        rabbitTemplate.convertAndSend(exchange, message.getInvoiceNumber(), message);
    }

}
