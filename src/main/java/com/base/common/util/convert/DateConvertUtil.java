package com.base.common.util.convert;

import com.alibaba.fastjson.JSONArray;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateConvertUtil {

    /**
     * 获取当前日期：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowTimeString(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    /**
     * 获取当前时间：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String nowDataString(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return now.format(formatter);
    }
}
