package com.hwk.seckill.api.service.user;

import com.hwk.seckill.common.dto.UserInfoDTO;
import com.hwk.seckill.common.vo.UserVO;

public interface UserService {

    /**
     *查询用户视图信息
     * @param userId
     * @return
     */
    public UserVO queryViewByUserId(long userId);


    /**
     * 查询用户基础信息
     * @param userId
     * @return
     */
    public UserInfoDTO queryById(long userId);
}
