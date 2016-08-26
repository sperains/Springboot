package com.rains.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Administrator on 2016-08-25.
 */
public class Order {

    public static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        StringBuilder ddh = new StringBuilder();
        String key = format.format(date);
        ddh.append(key);
        Random r = new Random();
        for(int i=0;i<6;i++){
            ddh.append(r.nextInt(10));
        }
        return ddh.toString();
    }
}
