package com.pblgllgs.data;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.entities.InvoiceCancelledMessage;
import com.pblgllgs.entities.InvoiceCreatedMessage;
import com.pblgllgs.entities.InvoicePaidMessage;
import com.pblgllgs.producer.InvoiceProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final InvoiceProducer producer;

    @Override
    public void run(String... args) {
        var randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(100, 200);
        InvoiceCreatedMessage invoiceCreatedMessage = new InvoiceCreatedMessage(
                152.26,
                LocalDate.now().minusDays(2),
                "USD",
                randomInvoiceNumber);
        producer.sendInvoice(invoiceCreatedMessage);
        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
        var randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(800, 1000);
        var invoicePaidMessage = new InvoicePaidMessage(
                randomInvoiceNumber,
                LocalDate.now(),
                randomPaymentNumber);
        producer.sendInvoice(invoicePaidMessage);

        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
        var invoiceCancelledMessage = new InvoiceCancelledMessage(
                LocalDate.now(),
                randomInvoiceNumber,
                "canceled") ;
        producer.sendInvoice(invoiceCancelledMessage);
        log.info("DONE!!");
    }
}
