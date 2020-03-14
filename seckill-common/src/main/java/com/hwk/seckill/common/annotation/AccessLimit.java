package com.hwk.seckill.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented()
public @interface AccessLimit {
}
