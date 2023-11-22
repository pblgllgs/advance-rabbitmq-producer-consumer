package com.pblgllgs.producer.config;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${q.transaction}")
    private String queue1;

    @Value("${q.scheduler}")
    private String queue2;


    @Value("${x.transaction}")
    private String exchangeTransaction;

    @Value("${x.scheduler}")
    private String exchangeScheduler;

    @Bean
    public Queue newQueue1() {
        return new Queue(queue1);
    }
    @Bean
    public Queue newQueue2() {
        return new Queue(queue2);
    }

    @Bean
    public FanoutExchange exchange1() {
        return new FanoutExchange(exchangeTransaction);
    }
    @Bean
    public FanoutExchange exchange2() {
        return new FanoutExchange(exchangeScheduler);
    }

    @Bean
    public Binding binding1(Queue newQueue1, FanoutExchange exchange1) {
        return BindingBuilder.bind(newQueue1).to(exchange1);
    }

    @Bean
    public Binding binding2(Queue newQueue2, FanoutExchange exchange2) {
        return BindingBuilder.bind(newQueue2).to(exchange2);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
