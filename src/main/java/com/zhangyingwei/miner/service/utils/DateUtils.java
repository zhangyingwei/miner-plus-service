package com.zhangyingwei.miner.service.utils;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class DateUtils {

    private static Logger logger = Logger.getLogger(DateUtils.class);

    public static String getCurrentDateTime(){
        return formateDateTime(new Date());
    }

    public static String formateRssDate(String datetime)  {
//        if(datetime.contains("+")){
//            return formateRssDateZone(datetime);
//        }else{
//            return formateRssDateNomal(datetime);
//        }
        return DateTime.parse(datetime).toString("yyyy-MM-dd HH:mm:ss");
    }

    private static String formateRssDateZone(String datetime) {
        SimpleDateFormat format=new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.ENGLISH);
        try {
            return formateDateTime(format.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
            return datetime;
        }
    }

    private static String formateRssDateNomal(String datetime){
        SimpleDateFormat sdfTemp = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        SimpleTimeZone aZone = new SimpleTimeZone(8,"GMT");
        sdfTemp.setTimeZone(aZone);
        try {
            return formateDateTime(sdfTemp.parse(datetime));
        } catch (ParseException e) {
            e.printStackTrace();
            return datetime;
        }
    }

    private static String formateDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static void main(String[] args) {
        String date = "2017-03-23T07:40:26.000Z";
        System.out.println(DateTime.parse(date).toDate());
    }

    public static Date dateTimeToDate(String dateTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(dateTime);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 判断时间格式是否正确，暂时就Ian这样吧
     * @param pubdate
     * @return
     */
    public static boolean isDateTimeSring(String pubdate) {
        return pubdate != null && pubdate.split(" ").length == 2;
    }
}
