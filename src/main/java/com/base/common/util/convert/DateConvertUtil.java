package com.base.common.util.convert;

import com.alibaba.fastjson.JSONArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * 获取当前日期：默认yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowDateString(String formatter){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return now.format(dateTimeFormatter);
    }
}
