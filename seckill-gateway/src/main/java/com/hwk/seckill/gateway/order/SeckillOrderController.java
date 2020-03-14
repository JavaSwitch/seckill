package com.hwk.seckill.gateway.order;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hwk.seckill.api.service.order.OrderService;
import com.hwk.seckill.common.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class SeckillOrderController {

    @Reference
    private OrderService OrderService;

    @GetMapping("/detail/{orderId}")
    public BaseResponse detail(@PathVariable long orderId){
        return BaseResponse.ok(OrderService.queryOrder(orderId));
    }

}
