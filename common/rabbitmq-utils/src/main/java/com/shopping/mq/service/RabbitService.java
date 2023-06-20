package com.shopping.mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-20 23:36
 * @description: shopping-parent
 */
@Component
public class RabbitService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param exchange
     * @param routingKey
     * @param message
     * @return
     */
    public boolean sendMsg(String exchange,String routingKey,Object message){
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
        return true;
    }


}
