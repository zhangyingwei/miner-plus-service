package com.zhangyingwei.miner.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class DateUtils {

    public static String getCurrentDateTime(){
        return formateDateTime(new Date());
    }

    public static String formateRssDate(String datetime)  {
        SimpleDateFormat sdfTemp = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        SimpleTimeZone aZone = new SimpleTimeZone(8,"GMT");
        sdfTemp.setTimeZone(aZone);
        try {
            return formateDateTime(sdfTemp.parse(datetime));
        } catch (ParseException e) {
            return datetime;
        }
    }

    private static String formateDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
