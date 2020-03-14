package com.hwk.seckill.goods.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hwk.seckill.api.service.goods.GoodsService;
import com.hwk.seckill.common.dto.Exposer;
import com.hwk.seckill.common.dto.GoodsInfoDTO;
import com.hwk.seckill.goods.entity.SeckillGoods;
import com.hwk.seckill.goods.service.SeckillGoodsService;

import javax.annotation.Resource;

@Service
public class GoodsServiceImpl implements GoodsService {


    @Resource
    private SeckillGoodsService seckillGoodsService;

    @Override
    public Exposer ExposeSeckillUrl(long userId, long goodsId) {
        return seckillGoodsService.exposedSeckillUrl(userId,goodsId);
    }

    @Override
    public boolean seckillGoods(long userId, long goodsId, String md5) {
        return seckillGoodsService.seckillGoods(userId,goodsId,md5);
    }

    @Override
    public GoodsInfoDTO queryById(long goodsId) {
        SeckillGoods seckillGoods = seckillGoodsService.getOne(Wrappers.<SeckillGoods>lambdaQuery().eq(SeckillGoods::getGoodsId, goodsId));
        GoodsInfoDTO goodsInfoDTO = new GoodsInfoDTO();
        goodsInfoDTO.setName(seckillGoods.getName());
        return goodsInfoDTO;
    }

}
