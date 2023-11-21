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

    @Value("${q.dummy}")
    private String queue;


    @Value("${x.dummy}")
    private String exchange;

    @Bean
    public Queue newQueue() {
        return new Queue(queue);
    }

    @Bean
    public FanoutExchange exchangeFanout() {
        return new FanoutExchange(exchange);
    }

    @Bean
    public Binding binding(Queue newQueue, FanoutExchange exchangeFanout) {
        return BindingBuilder.bind(newQueue).to(exchangeFanout);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
