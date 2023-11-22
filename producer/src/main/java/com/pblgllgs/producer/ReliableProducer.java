package com.pblgllgs.producer;
/*
 *
 * @author pblgl
 * Created on 22-11-2023
 *
 */

import com.pblgllgs.entities.DummyMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReliableProducer {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void registerCallback() {
        rabbitTemplate.setConfirmCallback(((correlationData, ack, reason) -> {
            if (correlationData == null) {
                return;
            }
            if (ack) {
                log.info("Message with correlation {} published", correlationData.getId());
            } else {
                log.warn("Invalid exchange for message with correlation {} published", correlationData.getId());
            }
        }));
        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("return callback");
            if (returned.getReplyText() != null && returned.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
                var id =  returned.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation").toString();
                log.warn("invalid routing key for message {}",id);
            }
        });
    }

    public void sendDummyWithInvalidRoutingKey(DummyMessage message){
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.single","invalid-routing-key",message,correlationData);
    }

    public void sendDummyToInvalidExchange(DummyMessage message){
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.non-exist-exchange","",message,correlationData);
    }

}
