package com.github.simple.core.annotation;

import java.util.List;

/**
 * @author: jianlei.shi
 * @date: 2020/12/17 2:42 下午
 * @description: ConfigSimpleMethod
 */

public abstract class ConfigSimpleMethod {
    protected List<SimpleMethodMetadata> methodMetadata;

    protected Class<?> configClazz;

    protected ConfigSimpleMethod(List<SimpleMethodMetadata> methodMetadata, Class<?> configClazz) {
        this.methodMetadata = methodMetadata;
        this.configClazz = configClazz;
    }

    protected ConfigSimpleMethod() {
    }

    public List<SimpleMethodMetadata> getMethodMetadata() {
        return methodMetadata;
    }

    public void setMethodMetadata(List<SimpleMethodMetadata> methodMetadata) {
        this.methodMetadata = methodMetadata;
    }

    public Class<?> getConfigClazz() {
        return configClazz;
    }

    public void setConfigClazz(Class<?> configClazz) {
        this.configClazz = configClazz;
    }
}
