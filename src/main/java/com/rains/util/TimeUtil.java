package com.rains.util;

import java.util.Date;

/**
 * Created by Administrator on 2016-08-24.
 */
public class TimeUtil {

    private static int overTime = 15 ;            //超时时间  分钟

    public static boolean isOverdue(Date date){

        long now = System.currentTimeMillis();

        if(now > date.getTime() + getOverDueTime(overTime)){
            return true;
        }
        return false;

    }

    public static String getOverTime(Date date){

        long now = System.currentTimeMillis();
        long overTime = now - date.getTime();
        String time  = String.valueOf(overTime / 60 /1000);
        return time + "分钟";
    }

    private static long getOverDueTime(int i){
        return  1000*60*i ;
    }
}
