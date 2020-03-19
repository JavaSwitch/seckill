package com.hwk.seckill.order.mq;


import com.hwk.seckill.common.mq.RocketMQConfig;
import com.hwk.seckill.order.entity.SeckillOrder;
import com.hwk.seckill.order.service.SeckillOrderService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
public class MQConsumer {

    /**
     * 消费者实例
     */
    private DefaultMQPushConsumer consumer;

    /**
     * 消费者组
     */
    private static final String CONSUMER_GROUP = "order_consumer";


    @Autowired
    private SeckillOrderService seckillOrderService;


    public MQConsumer() throws MQClientException {

        // 初始化
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(RocketMQConfig.NAME_SERVER);
        consumer.subscribe(RocketMQConfig.ORDER_TOPIC, MessageSelector.byTag("*"));

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                try{
                    MessageExt messageExt = list.get(0);
                    String key = messageExt.getKeys();
                    String msg = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    SeckillOrder seckillOrder = new SeckillOrder();
                    seckillOrder.setUserId(Long.parseLong(key.substring(key.indexOf(":")+1, key.length())));
                    seckillOrder.setGoodsId(Long.parseLong(msg.substring(key.indexOf(":")+1, msg.length())));
                    seckillOrder.setState(1);

                    boolean result = seckillOrderService.save(seckillOrder);
                    System.out.println("下单结果：" + result);

                    if(result){ // 表示下单成功
                        // 下单后，去数据库层减商品库存
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }

                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }

                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });


        consumer.start();



    }

}
