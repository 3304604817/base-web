package com.base.common.util.mybatis.helper;

import java.util.Collections;
import java.util.List;

/**
 * @author gaoyang
 */
public class OptionalHelper {
    private static ThreadLocal<List<String>> optionals = new ThreadLocal<>();

    private OptionalHelper() {

    }

    /**
     * 获取自定义更新列
     */
    public static List<String> optional() {
        if (optionals.get() == null) {
            optionals.set(Collections.emptyList());
        }
        return optionals.get();
    }

    public static void optional(List<String> optional) {
        optionals.set(optional);
    }
}
