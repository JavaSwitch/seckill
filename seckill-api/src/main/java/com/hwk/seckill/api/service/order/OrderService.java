package com.hwk.seckill.api.service.order;

import com.hwk.seckill.common.vo.OrderDetailVO;

public interface OrderService {

    public OrderDetailVO queryOrder(long orderId);

}
