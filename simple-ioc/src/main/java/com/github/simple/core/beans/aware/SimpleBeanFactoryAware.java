package com.github.simple.core.beans.aware;

import com.github.simple.core.beans.factory.SimpleBeanFactory;

/**
 * @author: JianLei
 * @date: 2020/12/12 3:02 下午
 * @description: SimpleBeanFactoryAware
 */

public interface SimpleBeanFactoryAware {


    void setBeanFactory(SimpleBeanFactory simpleBeanFactory);
}
