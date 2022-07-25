package com.base.common.util.convert;

import com.alibaba.fastjson.JSONArray;
import java.util.List;

/**
 * @author yang.gao
 * @description List 转换工具类
 * @date 2022/7/25 14:45
 */
public class ListConvertUtil {

    /**
     * List转Json字符串
     * @param list
     * @return
     */
    public static String convertString(List<?> list){
        return JSONArray.toJSONString(list);
    }
}
