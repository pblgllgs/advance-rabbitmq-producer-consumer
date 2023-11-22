package com.pblgllgs.data;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.entities.InvoiceCancelledMessage;
import com.pblgllgs.producer.InvoiceProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final InvoiceProducer producer;

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10; i++) {
            String invoiceNumber = "INV-" + i;
            InvoiceCancelledMessage invoiceCancelledMessage = new InvoiceCancelledMessage(
                    LocalDate.now(),
                    invoiceNumber,
                    "Test " + i
            );
            producer.sendInvoiceCanceled(invoiceCancelledMessage);
        }
        log.info("DONE!!");
    }
}
