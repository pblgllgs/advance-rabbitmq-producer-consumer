package com.pblgllgs.config;
/*
 *
 * @author pblgl
 * Created on 21-11-2023
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${q.invoice}")
    private String queue;


    @Value("${x.invoice}")
    private String exchange;

    @Bean
    public Queue newQueue() {
        return new Queue(queue);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(exchange);
    }

    @Bean
    public Binding binding(Queue newQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(newQueue).to(exchange);
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
