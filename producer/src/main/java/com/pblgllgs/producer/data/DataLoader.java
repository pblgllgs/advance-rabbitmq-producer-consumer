package com.pblgllgs.producer.data;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.pblgllgs.producer.producer.MultiPrefetchProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final MultiPrefetchProducer producer;

    @Override
    public void run(String... args) throws InterruptedException {
            producer.simualteTransacion();
            producer.simualteScheduler();
            log.info("DONE!!");
    }
}
