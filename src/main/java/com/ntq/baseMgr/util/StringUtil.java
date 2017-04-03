package com.ntq.baseMgr.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>@description: </p>
 *
 * @projectName: interpolation
 * @packageName: com.ntq.baseMgr.util
 * @className:
 * @author: shuangyang
 * @date: 17-3-30 下午12:07
 */
public class StringUtil {

    private StringUtil(){}

//    public

    public static List<Long> idsStr2List(String ids){
        List<Long> idList = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        return idList;
    }
}
