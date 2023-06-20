package com.shopping.mq.constant;

/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-20 23:52
 * @description: shopping-parent
 */

public class MqConst {
    /**
     * 消息补偿
     */
    public static final String MQ_KEY_PREFIX = "shopping.mq:list";
    public static final int RETRY_COUNT = 3;

    /**
     * 商品上下架
     */
    public static final String EXCHANGE_GOODS_DIRECT = "shopping.goods.direct";
    public static final String ROUTING_GOODS_UPPER = "shopping.goods.upper";
    public static final String ROUTING_GOODS_LOWER = "shopping.goods.lower";

    //队列
    public static final String QUEUE_GOODS_UPPER  = "shopping.goods.upper";
    public static final String QUEUE_GOODS_LOWER  = "shopping.goods.lower";

    /**
     * 团长上下线
     */
    public static final String EXCHANGE_LEADER_DIRECT = "shopping.leader.direct";
    public static final String ROUTING_LEADER_UPPER = "shopping.leader.upper";
    public static final String ROUTING_LEADER_LOWER = "shopping.leader.lower";
    //队列
    public static final String QUEUE_LEADER_UPPER  = "shopping.leader.upper";
    public static final String QUEUE_LEADER_LOWER  = "shopping.leader.lower";

    //订单
    public static final String EXCHANGE_ORDER_DIRECT = "shopping.order.direct";
    public static final String ROUTING_ROLLBACK_STOCK = "shopping.rollback.stock";
    public static final String ROUTING_MINUS_STOCK = "shopping.minus.stock";

    public static final String ROUTING_DELETE_CART = "shopping.delete.cart";
    //解锁普通商品库存
    public static final String QUEUE_ROLLBACK_STOCK = "shopping.rollback.stock";
    public static final String QUEUE_SECKILL_ROLLBACK_STOCK = "shopping.seckill.rollback.stock";
    public static final String QUEUE_MINUS_STOCK = "shopping.minus.stock";
    public static final String QUEUE_DELETE_CART = "shopping.delete.cart";

    //支付
    public static final String EXCHANGE_PAY_DIRECT = "shopping.pay.direct";
    public static final String ROUTING_PAY_SUCCESS = "shopping.pay.success";
    public static final String QUEUE_ORDER_PAY  = "shopping.order.pay";
    public static final String QUEUE_LEADER_BILL  = "shopping.leader.bill";

    //取消订单
    public static final String EXCHANGE_CANCEL_ORDER_DIRECT = "shopping.cancel.order.direct";
    public static final String ROUTING_CANCEL_ORDER = "shopping.cancel.order";
    //延迟取消订单队列
    public static final String QUEUE_CANCEL_ORDER  = "shopping.cancel.order";

    /**
     * 定时任务
     */
    public static final String EXCHANGE_DIRECT_TASK = "shopping.exchange.direct.task";
    public static final String ROUTING_TASK_23 = "shopping.task.23";
    //队列
    public static final String QUEUE_TASK_23  = "shopping.queue.task.23";

}