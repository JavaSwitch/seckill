package com.hwk.seckill.goods.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hwk.seckill.common.dto.Exposer;
import com.hwk.seckill.goods.entity.SeckillGoods;

/**
 * <p>
 * 秒杀商品表 服务类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-16
 */
public interface SeckillGoodsService extends IService<SeckillGoods> {

    /**
     * 检查是否开启秒杀，秒杀开启则暴露秒杀url
     * @param userId
     * @param goodsId
     * @return
     */
    public Exposer exposedSeckillUrl(long userId, long goodsId);


    /**
     * 秒杀商品下单
     * @param userId
     * @param goodsId
     * @param md5
     * @return 简单示意，只返回一个布尔值
     */
    public boolean seckillGoods(long userId, long goodsId, String md5);

}
