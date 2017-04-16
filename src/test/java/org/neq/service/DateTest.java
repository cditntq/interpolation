package org.neq.service;

import com.ntq.baseMgr.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>@description: </p>
 *
 * @projectName: interpolation
 * @packageName: org.neq.service
 * @className:
 * @author: shuangyang
 * @date: 17-4-16 下午5:56
 */
public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
//        Date
        Date date1 = DateUtil.addStartTime(date);
        Date date2 = DateUtil.addEndTime(date);
        System.out.println("hello world");

    }
}
