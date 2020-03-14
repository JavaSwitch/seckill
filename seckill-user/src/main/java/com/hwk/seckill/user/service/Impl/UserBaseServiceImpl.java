package com.hwk.seckill.user.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hwk.seckill.user.entity.UserBase;
import com.hwk.seckill.user.dao.UserBaseMapper;
import com.hwk.seckill.user.service.UserBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基础信息表 服务实现类
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-16
 */
@Service
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements UserBaseService {

    @Resource
    UserBaseMapper userBaseMapper;

    @Override
    public UserBase queryById(long userId) {
        UserBase userBase = userBaseMapper.selectOne(Wrappers.<UserBase>lambdaQuery().eq(UserBase::getId,userId));
        return userBase;
    }
}
