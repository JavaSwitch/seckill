package com.hwk.seckill.goods.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwk.seckill.common.cache.RedisService;
import com.hwk.seckill.common.dto.Exposer;
import com.hwk.seckill.common.mq.MQProducer;
import com.hwk.seckill.common.mq.RocketMQConfig;
import com.hwk.seckill.common.util.Md5;
import com.hwk.seckill.goods.dao.SeckillGoodsMapper;
import com.hwk.seckill.goods.entity.SeckillGoods;
import com.hwk.seckill.goods.service.SeckillGoodsService;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;

/**
 * <p>
 * 秒杀商品表 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-16
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    /**
     * md5盐 ,随机定义
     */
    private static final String slat = "seckill-goods-slat#$@";

    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;

    @Resource
    private RedisService redisService;

    @Autowired
    private MQProducer mqProducer;

    @Override
    public Exposer exposedSeckillUrl(long userId, long goodsId) {
        SeckillGoods seckillGoods = seckillGoodsMapper
                .selectOne(Wrappers.<SeckillGoods>lambdaQuery().eq(SeckillGoods::getGoodsId, goodsId));

        if(seckillGoods.getStartTime().isAfter(LocalDateTime.now())){
            return new Exposer(false);
        }else{
            return new Exposer(true,md5(userId,goodsId),seckillGoods.getStartTime(),seckillGoods.getEndTime());
        }
    }

    @Override
    public boolean seckillGoods(long userId, long goodsId, String md5) {

        // 不符合正确秘钥的全部不给通过
        if(md5 == null || md5.equals("") || !checkMd5(userId,goodsId,md5)){
            return false;
        }

        // 执行 lua 脚本
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/decrStore.lua")));
        redisScript.setResultType(Boolean.class);
        boolean result = (Boolean)redisService.executeScript(redisScript, Collections.singletonList("store:"+goodsId));
        System.out.println("秒杀结果：" + result);

        if(result){
            // 异步下单发到MQ
            try {
                Message msg = new Message(RocketMQConfig.ORDER_TOPIC,"seckill",
                        "userId:"+userId,("goodId:"+goodsId).getBytes());
                mqProducer.getProducer().send(msg);
                System.out.println("消息发生成功：userId="+ userId + ",goodsId=" + goodsId);
            } catch (Exception e) {
                System.out.println("消息发生失败：userId="+ userId + ",goodsId=" + goodsId);
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 加密MD5
     * @param userId
     * @param goodsId
     * @return
     */
    private String md5(long userId, long goodsId){
        String encodeString = String.valueOf(userId) + String.valueOf(goodsId) + slat;
        return Md5.encode(encodeString);
    }

    /**
     * 检查md5
     * @param userId
     * @param goodsId
     * @param md5
     * @return
     */
    private boolean checkMd5(long userId, long goodsId, String md5){
        if(md5.equals(md5(userId,goodsId))){
            return true;
        }
        return false;
    }
}
