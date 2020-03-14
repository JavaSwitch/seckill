package com.hwk.seckill.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented()
public @interface WebLog {

    /**
     * 日志描述
     * @return
     */
    String description() default "";
}
