package com.ntq.baseMgr.controller.support;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截service
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    /**
     * 拦截描述
     * @return
     */
    String description() default "";
}
