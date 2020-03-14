package com.hwk.seckill.gateway.user;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hwk.seckill.api.service.user.UserService;
import com.hwk.seckill.common.response.BaseResponse;
import com.hwk.seckill.common.vo.UserVO;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户基础信息表 前端控制器
 * </p>
 *
 * @author mybatis-plus
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/user")
public class UserBaseController {

    @Reference
    private UserService userService;


    @GetMapping("/{userId}")
    public BaseResponse<UserVO> userInfo(@PathVariable int userId){
        return BaseResponse.ok(userService.queryViewByUserId(userId));
    }

}

