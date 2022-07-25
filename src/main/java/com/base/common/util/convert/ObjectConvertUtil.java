package com.base.common.util.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author gaoyang
 * Object 数据类型转换工具类
 */
public class ObjectConvertUtil {

    public static String convertString(Object o){
        return JSON.toJSONString(o);
    }

    public static JSONObject convertJsonObject(Object o){
        return JSONObject.parseObject(JSON.toJSONString(o));
    }
}
