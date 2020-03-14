package com.hwk.seckill.api.service.goods;

import com.hwk.seckill.common.dto.Exposer;
import com.hwk.seckill.common.dto.GoodsInfoDTO;

public interface GoodsService {

    public Exposer ExposeSeckillUrl(long userId, long goodsId);

    public boolean seckillGoods(long userId, long goodsId, String md5);

    public GoodsInfoDTO queryById(long goodsId);

}
