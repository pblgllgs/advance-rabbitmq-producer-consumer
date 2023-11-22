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

    @Value("${q.single}")
    private String queue;


    @Value("${x.single}")
    private String exchange;

    @Bean
    public Queue myQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-single-active-consumer", true);
        return new Queue(queue, true, false, false, arguments);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(exchange);
    }

    @Bean
    public Binding binding(Queue newQueue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(newQueue).to(fanoutExchange);
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
