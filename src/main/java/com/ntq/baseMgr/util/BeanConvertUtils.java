package com.ntq.baseMgr.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * <p>@description:beanL类型转换,用于vo dto和po互转</p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.util
 * @className:
 * @author: shuangyang
 * @date: 17-5-7 上午9:45
 */
public class BeanConvertUtils {
    /**
     * Bean转换
     * @param source
     * @param targetClass
     * @return
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        String jsonStr = JSON.toJSONString(source);
        return JSON.parseObject(jsonStr, targetClass);
    }
    /**
     * List转换
     * @param source
     * @param targetClass List存放的数据类型
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> convertList(List source, Class<T> targetClass) {
        String jsonStr = JSON.toJSONString(source);
        return JSON.parseArray(jsonStr, targetClass);
    }
}
