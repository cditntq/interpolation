package com.ntq.baseMgr.controller.support;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
    /**
     * 日志操作描述
     * @return
     */
    String description() default "";
}
