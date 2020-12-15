package com.github.simple.demo.service;

import com.github.simple.ioc.annotation.SimpleComponent;
import com.github.simple.ioc.annotation.SimpleOrdered;
import com.github.simple.ioc.annotation.SimpleBeanPostProcessor;
import com.github.simple.ioc.factory.SimpleBeanFactory;
import com.github.simple.ioc.factory.SimpleBeanFactoryAware;

/**
 * @author: jianlei.shi
 * @date: 2020/12/14 8:06 下午
 * @description: CustomBeanPostProcessor
 */
@SimpleComponent
@SimpleOrdered(-2)
public class CustomBeanPostProcessor implements SimpleBeanPostProcessor, SimpleBeanFactoryAware {
    private SimpleBeanFactory beanFactory;
    @Override
    public Boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> clazz, String beanName) {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before --->>>"+bean.getClass()+"->CustomBeanPostProcessor order: -2");

        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("After --->>>"+bean.getClass()+"->CustomBeanPostProcessor order: -2");
        return null;
    }

    @Override
    public void postProcessProperties(Object bean, String beanName) {
        System.out.println("=====>>>"+ bean.getClass().getSimpleName());
    }


    @Override
    public void setBeanFactory(SimpleBeanFactory simpleBeanFactory) {
        this.beanFactory=simpleBeanFactory;
        System.out.println("--->"+beanFactory.getClass());

    }
}