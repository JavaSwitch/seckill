package com.hwk.seckill.order.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.hwk.seckill.api.service.goods.GoodsService;
import com.hwk.seckill.api.service.order.OrderService;
import com.hwk.seckill.api.service.user.UserService;
import com.hwk.seckill.common.dto.GoodsInfoDTO;
import com.hwk.seckill.common.dto.UserInfoDTO;
import com.hwk.seckill.common.vo.OrderDetailVO;
import com.hwk.seckill.order.entity.SeckillOrder;
import com.hwk.seckill.order.service.SeckillOrderService;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    private GoodsService goodsService;

    @Reference
    private UserService userService;

    @Resource
    private SeckillOrderService SeckillOrderService;

    @Override
    public OrderDetailVO queryOrder(long orderId) {

        SeckillOrder seckillOrder = SeckillOrderService.detail(orderId);

        long userId = seckillOrder.getUserId();
        //rpc调用获取用户信息
        UserInfoDTO userInfoDTO = userService.queryById(userId);

        //rpc调用商品信息
        GoodsInfoDTO goodsInfoDTO = goodsService.queryById(seckillOrder.getGoodsId());

        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrderId(seckillOrder.getOrderId());
        orderDetailVO.setUserName(userInfoDTO.getName());
        orderDetailVO.setAddress(userInfoDTO.getAddress());
        orderDetailVO.setPhone(userInfoDTO.getPhone());
        orderDetailVO.setGoodsName(goodsInfoDTO.getName());
        orderDetailVO.setCreateTime(seckillOrder.getCreateTime());
        return orderDetailVO;
    }
}
