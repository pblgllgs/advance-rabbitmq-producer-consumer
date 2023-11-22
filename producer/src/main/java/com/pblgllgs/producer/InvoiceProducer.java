package com.pblgllgs.producer;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
 *
 */

import com.pblgllgs.entities.InvoiceCancelledMessage;
import com.pblgllgs.entities.InvoiceCreatedMessage;
import com.pblgllgs.entities.InvoicePaidMessage;
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

    public void sendInvoice(InvoiceCreatedMessage message){
        rabbitTemplate.convertAndSend(exchange,"",message);
    }

    public void sendInvoice(InvoicePaidMessage message){
        rabbitTemplate.convertAndSend(exchange,"",message);
    }

    public void sendInvoiceCanceled(InvoiceCancelledMessage message){
        rabbitTemplate.convertAndSend(exchange,"",message);
    }

}
