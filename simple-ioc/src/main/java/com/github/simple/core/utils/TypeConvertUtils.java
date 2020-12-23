package com.github.simple.core.utils;

import com.github.simple.core.exception.SimpleTypeConvertException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: jianlei.shi
 * @date: 2020/12/20 4:41 下午
 * @description: TypeConvertUtils
 */
@Slf4j
public class TypeConvertUtils {

    public static <T> T convert(Class<T> type, String value) {
        if (value == null || "".equals(value)) {
            throw new SimpleTypeConvertException("value is null");

        }
        if (type.equals(Integer.class)) {
            Integer res;
            try {
                res = Integer.valueOf(value);
            } catch (Exception e) {
                log.error("["+value + "]is not convert Integer",e);
                throw new SimpleTypeConvertException("["+value + "] is not convert Integer");

            }

            return (T) res;
        }
        if (type.equals(String.class)) {
            return (T) value;
        }
        if (type.equals(Long.class)) {
            Long res;
            try {
                res = Long.valueOf(value);
            } catch (Exception e) {
                log.error("["+value + "]is not convert Long",e);
                throw new SimpleTypeConvertException("["+value + "]  is not convert Long");

            }

            return (T) res;
        }
        return null;

    }
}