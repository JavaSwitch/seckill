package com.hwk.seckill.user.service;

import com.hwk.seckill.user.entity.UserBase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基础信息表 服务类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-16
 */
public interface UserBaseService extends IService<UserBase> {


    public UserBase queryById(long userId);
}
