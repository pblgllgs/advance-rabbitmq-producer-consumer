package com.pblgllgs.data;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.entities.InvoiceCreatedMessage;
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
        for (int i = 0; i < 200; i++) {
            var invoiceNumber = "INV" + (i % 60);
            var invoice = new InvoiceCreatedMessage(ThreadLocalRandom.current().nextInt(200), LocalDate.now(), "USD", invoiceNumber);
            producer.sendInvoice(invoice);
        }
        log.info("DONE!!");
    }
}
