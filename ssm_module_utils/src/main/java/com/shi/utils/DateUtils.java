package com.shi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 千文sea
 * @create 2020-10-04 14:02
 */
public class DateUtils {

    //日期转换为字符串
    public static String dateToString(Date date, String pattern){
        SimpleDateFormat sdf =  new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    //字符串转为日期
    public static Date stringToDate(String str,String pattern) throws ParseException {
        SimpleDateFormat sdf =  new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        return date;
    }
}
