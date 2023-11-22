package com.pblgllgs.config;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Value("${q.invoice}")
    private String queue1;

    @Value("${q.invoice-cancel}")
    private String queue2;


    @Value("${x.invoice}")
    private String exchange1;

    @Value("${x.invoice-cancel}")
    private String exchange2;


    @Bean
    public Queue newQueue1(){
        return new Queue(queue1);
    }

    @Bean
    public Queue newQueue2(){
        return new Queue(queue2);
    }

    @Bean
    public FanoutExchange fanoutExchange1(){
        return new FanoutExchange(exchange1);
    }

    @Bean
    public FanoutExchange fanoutExchange2(){
        return new FanoutExchange(exchange2);
    }

    @Bean
    public Binding binding1(Queue newQueue1,FanoutExchange fanoutExchange1){
        return BindingBuilder.bind(newQueue1).to(fanoutExchange1);
    }

    @Bean
    public Binding binding2(Queue newQueue2,FanoutExchange fanoutExchange2){
        return BindingBuilder.bind(newQueue2).to(fanoutExchange2);
    }

    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter jsonMessageConverter, ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter);
        return template;
    }
}
