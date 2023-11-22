package com.pblgllgs.producer.data;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.producer.entities.DummyMessage;
import com.pblgllgs.producer.producer.DummyProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final DummyProducer dummyProducer;

    @Override
    public void run(String... args) throws InterruptedException {
        for (int i = 0; i < 500; i++) {
            DummyMessage dummyMessage = DummyMessage.builder()
                    .content("Now is " + LocalDate.now())
                    .publishOrder(i)
                    .build();
            log.info(dummyMessage.toString());
            dummyProducer.sendDummy(dummyMessage);
        }
    }
}
