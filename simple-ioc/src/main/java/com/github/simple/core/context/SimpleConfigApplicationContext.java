package com.github.simple.core.context;

import com.github.simple.core.beans.factory.SimpleBeanFactory;
import com.github.simple.core.beans.factory.SimpleConfigBeanFactory;

/**
 * 简单的配置应用程序上下文
 *
 * @author: JianLei
 * @date: 2020/12/21 3:04 下午
 * @description: SimpleApplicationContext
 * @date
 */
public interface SimpleConfigApplicationContext extends SimpleConfigBeanFactory  {


    SimpleBeanFactory getBeanFactory();


    void refresh();

    void addApplicationListener(SimpleApplicationListener<?> listener);



}
