package com.freekai.cloud.producer;

import com.freekai.cloud.config.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class MqMsgProducer implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(Map<String,Object> map, String routeKey){
        final CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("====>correlationData:" + correlationData);
        if(Objects.equals("a",routeKey)){
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.QUEUE_A, map, correlationData);
        }else{
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.QUEUE_B, map, correlationData);
        }

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("消息确认成功");
        }else{
            System.out.println("消息确认失败"+cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息未能成功路由到指定queues");
        System.out.println("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}
