package com.base.common.util.convert;

import com.alibaba.fastjson.JSONArray;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DateConvertUtil {

    public static String FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT2 = "yyyy-MM-dd HH:mm";
    public static String FORMAT3 = "yyyy-MM-dd HH";
    public static String FORMAT4 = "yyyy-MM-dd";
    public static String FORMAT5 = "yyyy-MM";
    public static String FORMAT6 = "yyyy";

    public static String FORMAT7 = "yyyyMMddHHmmss";
    public static String FORMAT8 = "yyyyMMddHHmm";
    public static String FORMAT9 = "yyyyMMddHH";
    public static String FORMAT10 = "yyyyMMdd";
    public static String FORMAT11 = "yyyyMM";
    public static String FORMAT12 = "yyyy";

    /**
     * 获取当前日期：自定义格式
     * @return
     */
    public static String nowDateString(String FORMAT){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT);
        return now.format(dateTimeFormatter);
    }

    /**
     * 获取当前日期：默认yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowDateTimeString(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT1);
        return now.format(dateTimeFormatter);
    }

    /**
     * 获取当前日期：默认yyyy-MM-dd
     * @return
     */
    public static String nowDateString(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT4);
        return now.format(dateTimeFormatter);
    }

    /**
     * Date 转 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateTimeString(Date date){
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT1);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * Date 转 yyyy-MM-dd
     * @return
     */
    public static String dateString(Date date){
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT4);
        return localDateTime.format(dateTimeFormatter);
    }
}
