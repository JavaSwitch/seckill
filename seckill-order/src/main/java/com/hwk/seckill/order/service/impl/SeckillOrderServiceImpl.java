package com.hwk.seckill.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hwk.seckill.order.entity.SeckillOrder;
import com.hwk.seckill.order.dao.SeckillOrderMapper;
import com.hwk.seckill.order.service.SeckillOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 秒杀商品订单表 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-19
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {


    @Resource
    private SeckillOrderMapper seckillOrderMapper;

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @Override
    public SeckillOrder detail(long orderId) {
        final SeckillOrder seckillOrder = seckillOrderMapper.selectOne(Wrappers.<SeckillOrder>lambdaQuery().eq(SeckillOrder::getOrderId, orderId));
        return seckillOrder;
    }
}
