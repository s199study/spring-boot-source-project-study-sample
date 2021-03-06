package com.github.simple.demo.service;

import com.github.simple.core.annotation.SimpleValue;
import com.github.simple.demo.service.aop.LogService;
import com.github.simple.core.annotation.SimpleAutowired;
import com.github.simple.core.annotation.SimpleComponent;

/**
 * @author: JianLei
 * @date: 2020/12/12 4:07 下午
 * @description: 循环依赖测试bean
 */
@SimpleComponent
public class B {
    @SimpleAutowired
    private A a;
    @SimpleAutowired
    private LogService logService;

    @SimpleAutowired
    private FactoryBeanB factoryBeanB;

    @SimpleValue("${simple.address}")
    private String address;
    public String hello() {
        a.sendMsg();
        System.out.println("***************testLog output :"+logService.testLog()+"--->>address is:"+address);
        return "this B msg";
    }


}
