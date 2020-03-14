package com.hwk.seckill.common.dto;

import java.io.Serializable;

/**
 * 用于rpc接口的dto：用户信息
 */
public class UserInfoDTO implements Serializable {

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private long phone;

    /**
     * 地址
     */
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
