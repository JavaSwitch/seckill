package com.hwk.seckill.gateway.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hwk.seckill.api.service.goods.GoodsService;
import com.hwk.seckill.common.annotation.AccessLimit;
import com.hwk.seckill.common.annotation.WebLog;
import com.hwk.seckill.common.dto.Exposer;
import com.hwk.seckill.common.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @GetMapping("/{userId}/{goodsId}/exposer")
    public BaseResponse<Exposer> exposerSeckillUrl(@PathVariable long userId, @PathVariable long goodsId){
        return BaseResponse.ok(goodsService.ExposeSeckillUrl(userId,goodsId));
    }


    @AccessLimit
    @GetMapping("/{userId}/{goodsId}/{md5}/execution")
    @WebLog(description = "执行秒杀")
    public BaseResponse seckill(@PathVariable long userId, @PathVariable long goodsId,
                                @PathVariable String md5){
        boolean seckillResult = goodsService.seckillGoods(userId, goodsId, md5);
        return BaseResponse.ok(seckillResult ? "秒杀成功" : "秒杀失败");
    }
}
