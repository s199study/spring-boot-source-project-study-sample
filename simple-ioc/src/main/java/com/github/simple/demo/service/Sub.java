package com.github.simple.demo.service;

import com.github.simple.core.annotation.SimpleComponent;

/**
 * @author: jianlei.shi
 * @date: 2020/12/14 4:24 下午
 * @description: Sub
 */
@SimpleComponent
public class Sub extends Parent{


    public void test2(){
        System.out.println("test2");
    }

    public Teacher teacher(){
        return new Teacher("zhangsan");
    }


}
