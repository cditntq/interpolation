package org.neq.service;

import java.text.ParseException;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateStop = "2017-04-11 19:20:00";
        try {
            Date parse = format.parse(dateStop);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println(dateStop);
    }
}
