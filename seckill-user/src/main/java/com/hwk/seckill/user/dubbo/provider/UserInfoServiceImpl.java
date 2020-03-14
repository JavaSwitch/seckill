package com.hwk.seckill.user.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.hwk.seckill.api.service.user.UserService;
import com.hwk.seckill.common.dto.UserInfoDTO;
import com.hwk.seckill.common.vo.UserVO;
import com.hwk.seckill.user.entity.UserBase;
import com.hwk.seckill.user.service.UserBaseService;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserService {

    @Resource
    private UserBaseService userBaseService;


    @Override
    public UserVO queryViewByUserId(long userId) {
        UserBase userBase = userBaseService.queryById(userId);
        // 这里是用户信息视图信息，其实userVO不仅仅包含用户基本信息，还包括其他一些用户信息，这里就不做过多的set属性
        UserVO userVO = new UserVO();
        userVO.setName(userBase.getName());
        userVO.setPhone(userBase.getPhone());
        userVO.setAddress(userBase.getAddress());
        return userVO;
    }

    @Override
    public UserInfoDTO queryById(long userId) {
        UserBase userBase = userBaseService.queryById(userId);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName(userBase.getName());
        userInfoDTO.setPhone(userBase.getPhone());
        userInfoDTO.setAddress(userBase.getAddress());
        return userInfoDTO;
    }
}
