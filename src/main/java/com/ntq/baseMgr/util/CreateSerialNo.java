package com.ntq.baseMgr.util;/**
 * Created by hejh on 17/4/10.
 */

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description:</b><br>
 *
 * @author hejh</a>
 * @version 1.0
 * @Note <b>ProjectName:</b> interpolation
 * <br><b>PackageName:</b>
 * <br><b>ClassName:</b>
 * <br><b>Date:</b> 2017-04-10
 */
public class CreateSerialNo {

    private static Map<String,String> map=new HashMap<String, String>();
    private static String STATNUM="000001";

    /**
     * 获取年月日
     * @return
     */
    public String getTime(){
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        return df.format(cal.getTime());
    }

    /**
     * 判断序号是否到了最后一个
     * @param s
     * @return
     */
    public String getLastSixNum(String s){
        String rs=s;
        int i=Integer.parseInt(rs);
        i+=1;
        rs=""+i;
        for (int j = rs.length(); j <6; j++) {
            //rs="0"+rs;
            //直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs,j+1, "0");
        }
        return rs;
    }

    /**
     * 产生不重复的号码  加锁
     * @return
     */
    public synchronized  String getNum(){
        String yearAMon = getTime();
        String last6Num=map.get(yearAMon);
        if(last6Num==null){
            map.put(yearAMon,STATNUM);
        }else{
            map.put(yearAMon,getLastSixNum(last6Num));
        }

        return yearAMon+map.get(yearAMon);
    }

    /**
     * main测试
     * @param args
     */
    public static void main(String[] args) {
        CreateSerialNo t= new CreateSerialNo();
        for (int i = 0; i < 200; i++) {
            System.out.println(t.getNum());
        }
    }
}
