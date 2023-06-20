package com.shopping.search.receive;

import com.rabbitmq.client.Channel;
import com.shopping.mq.constant.MqConst;
import com.shopping.search.service.SkuService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-20 23:59
 * @description: shopping-parent
 */
@Component
public class SkuReceiver {
    @Resource
    private SkuService skuService;

    /**
     * 商品上架接收
     * @param skuId
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_GOODS_UPPER,durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_GOODS_DIRECT),
            key = {MqConst.ROUTING_GOODS_UPPER}))
    public void upperSku(Long skuId, Message message, Channel channel) throws IOException {
        if (skuId!=null){
            skuService.upperSku(skuId);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    /**
     * 下架接收
     * @param skuId
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_GOODS_LOWER,durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_GOODS_DIRECT),
            key = {MqConst.ROUTING_GOODS_LOWER}))
    public void lowerSku(Long skuId, Message message, Channel channel) throws IOException {
        if (skuId!=null){
            skuService.lower(skuId);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
