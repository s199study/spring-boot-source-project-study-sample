package com.github.simple.core.utils;

import java.lang.reflect.Field;

/**
 * @author: jianlei.shi
 * @date: 2020/12/16 8:47 下午
 * @description: StringUtils
 */

public class StringUtils {
    public static final String VALUE_PREFIX = "${";
    public static final String VALUE_SUFFIX = "}";

    public static String parsePlaceholder(Field field){
        String placeholder = ReflectUtils.parseValue(field);
        return resolvePlaceholder(placeholder);

    }
    public static String resolvePlaceholder(String value) {
        int startInterceptionIndex = value.indexOf(VALUE_PREFIX);
        int endInterceptionIndex = value.indexOf(VALUE_SUFFIX);
        String v = value.substring(startInterceptionIndex + 2, endInterceptionIndex);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(v)) {
            return v;
        }
        throw new RuntimeException(VALUE_PREFIX + value + VALUE_SUFFIX + "is null!");
    }
}
