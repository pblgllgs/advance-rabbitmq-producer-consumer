package com.pblgllgs.consumer;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
 *
 */

import com.pblgllgs.entities.InvoiceCreatedMessage;
import com.pblgllgs.entities.InvoicePaidMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

    @RabbitHandler
    public void handleInvoiceCreated(InvoiceCreatedMessage message) {
        log.info("Invoice created: {}", message);
    }

    @RabbitHandler
    public void handleInvoicePaid(InvoicePaidMessage message) {
        log.info("Invoice paid: {}", message);
    }

    @RabbitHandler(isDefault = true)
    public void handle(Object message) {
        log.info("Invoice cancelled: {}", message);
    }
}
