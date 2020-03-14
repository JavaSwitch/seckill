package com.hwk.seckill.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 设置过期字符串，单位秒
     * @param key
     * @param value
     * @param expire
     */
    public void setExpireStringBySeconds(String key, String value, long expire){
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }


    /**
     * GET操作
     * @param key
     * @return
     */
    public String getString(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 自增1
     * @param key
     * @return
     */
    public Long incrString(String key){
        return redisTemplate.opsForValue().increment(key);
    }


    /**
     * 执行脚本
     * @param redisScript
     * @param keys
     * @param args
     * @param <T>
     * @return
     */
    public <T> Object executeScript(RedisScript<T> redisScript, List keys, Object... args){
        return redisTemplate.execute(redisScript, keys, args);
    }

}
