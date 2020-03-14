package com.hwk.seckill.order.service;

import com.hwk.seckill.common.vo.OrderDetailVO;
import com.hwk.seckill.order.entity.SeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 秒杀商品订单表 服务类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-19
 */
public interface SeckillOrderService extends IService<SeckillOrder> {


    public SeckillOrder detail(long orderId);

}
