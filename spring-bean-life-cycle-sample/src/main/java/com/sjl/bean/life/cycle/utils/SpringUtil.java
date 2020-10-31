package com.sjl.bean.life.cycle.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @author: JianLei
 * @date: 2020/10/20 4:45 下午
 * @description: SpringUtil
 */
@Component("mySpringUtil")
public class SpringUtil implements ApplicationContextAware, PriorityOrdered {
    private static  ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("-----------------------beanFactory start!----------------------------");
        SpringUtil.applicationContext=applicationContext;
        System.out.println("-----------------------beanFactory init end!----------------------------");
    }


    public static <T> T getBeanForType(Class<?> clazz){
        System.out.println("开始获取bean for type");
        return (T) applicationContext.getBeansOfType(clazz);

    }
    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }
}
