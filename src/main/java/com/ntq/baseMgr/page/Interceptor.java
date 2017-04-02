package com.ntq.baseMgr.page;

import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * <p>@description:定义拦截器接口 </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.page
 * @className:
 * @author: shuangyang
 * @date: 17-4-2 下午12:17
 */
public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 封装目标对象
     * @param target
     * @return
     */
    Object plugin(Object target);

    /**
     *
     * @param properties
     */
    void setProperties(Properties properties);
}
