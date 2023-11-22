package com.pblgllgs.data;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.entities.DummyMessage;
import com.pblgllgs.producer.ReliableProducer;
import com.pblgllgs.producer.SingleActiveProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final ReliableProducer producer;

    @Override
    public void run(String... args) {
        DummyMessage dummyMessage1 = new DummyMessage("Invalid test", 10);
        DummyMessage dummyMessage2 = new DummyMessage("Invalid test", 20);
        producer.sendDummyToInvalidExchange(dummyMessage1);
        producer.sendDummyWithInvalidRoutingKey(dummyMessage2);
        log.info("DONE!!");
    }
}
