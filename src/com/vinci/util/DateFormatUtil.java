package com.vinci.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:Vinci_Ma
 * @Oescription: 格式化日期工具
 * @Date Created in 2020-08-17-21:55
 * @Modified By:
 */
public class DateFormatUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date){
        return format.format(date);
    }
    public static long toTime(String formatString){
        try {
            return format.parse(formatString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
