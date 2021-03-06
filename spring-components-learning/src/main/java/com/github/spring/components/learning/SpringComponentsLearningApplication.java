package com.github.spring.components.learning;

import com.github.spring.components.learning.lighthttp.annotation.LightHttpScan;
import common.annotation.EnableAutoConfigThreadPool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@MapperScan("com.github.spring.components.learning.transaction.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)  //exposeProxy=true  即可以通过AopContext.currentProxy() 获取当前代理类
@Order
@EnableRetry
@LightHttpScan(basesPackages = "com.github.spring.components.learning.lighthttp.service")
@EnableAutoConfigThreadPool
public class SpringComponentsLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringComponentsLearningApplication.class, args);
    }

}
