package com.hwk.seckill.common.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

@Component
public class MQProducer {

    private String producerGroup = "ORDER_GROUP";

    private DefaultMQProducer producer ;

    public MQProducer(){
        producer = new DefaultMQProducer("GROUP_TEST");
        // 设置mq地址
        producer.setNamesrvAddr(RocketMQConfig.NAME_SERVER);
        // 不开通vip通道
        producer.setVipChannelEnabled(false);
        // 设置异步发送失败重试次数，默认为2
        producer.setRetryTimesWhenSendAsyncFailed(0);
        start();
    }

    /**
     * 启动生产者
     */
    public void start(){
        try{
            this.producer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取生产者
     * @return
     */
    public DefaultMQProducer getProducer(){
        return this.producer;
    }

    /**
     * 关闭
     */
    public void shutdown(){
        this.producer.shutdown();
    }
}
