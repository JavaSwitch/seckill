package com.hwk.seckill.common.aop;

import com.hwk.seckill.goods.common.cache.RedisService;
import com.hwk.seckill.goods.common.response.BaseResponse;
import com.hwk.seckill.goods.common.response.CodeMsg;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Profile({"dev","test","prod"})
public class AccessLimitAspect {

    /**
     * 访问频率，测试数据
     */
    private static final int ACCESS_LIMIT_TIMES = 5;

    @Autowired
    private RedisService redisService;

    @Pointcut("@annotation(com.hwk.seckill.goods.common.annotation.AccessLimit)")
    public void accessLimit(){}


    @Before("accessLimit()")
    public void doBefore(JoinPoint joinPoint) {}

    @After("accessLimit()")
    public void doAfter(){}

    @Around("accessLimit()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 此处获取每个用户的session来限流，可以换成ip或其他可以验证来源同一用户的凭证。
        String sessionId = request.getRequestedSessionId();
        //检查是否超过访问频率
        if(isExceedAccessTimes(sessionId)){
            // 降级措施
            return BaseResponse.error(CodeMsg.ERROR);
        }

        return proceedingJoinPoint.proceed();
    }


    /**
     * 控制用户访问接口的频率，每10秒最多5次访问,具体频率根据业务量而定
     * @param sessionId
     * @return
     */
    private boolean isExceedAccessTimes(String sessionId){

        String key = "visited:"+sessionId;
        String timesStr = redisService.getString(key);

        if(timesStr == null){
            redisService.setExpireStringBySeconds(key,"1",100);
        } else{
            int times = Integer.parseInt(timesStr);
            if(times > ACCESS_LIMIT_TIMES){
                return true;
            }else{
                redisService.incrString(key);
            }
        }

        return false;

    }

}
