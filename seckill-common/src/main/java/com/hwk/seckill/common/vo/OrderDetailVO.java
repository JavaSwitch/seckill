package com.hwk.seckill.common.vo;

import java.io.Serializable;
import java.time.LocalDateTime;


public class OrderDetailVO implements Serializable {


    /**
     * 订单号
     */
    private long orderId;

    /**
     * 下单用户名
     */
    private String userName;

    /**
     * 下单手机号
     */
    private long phone;

    /**
     * 用户地址
     */
    private String address;


    /**
     * 商品名称
     */
    private String goodsName;


    /**
     * 下单时间
     */
    private LocalDateTime createTime;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
