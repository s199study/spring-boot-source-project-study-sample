package com.sjl.spring.components.transaction.custom.annotation;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * @author: JianLei
 * @date: 2020/11/8 11:10 上午
 * @description: EasyTransaction
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EasyTransactional {


    int propagate() default TransactionDefinition.PROPAGATION_REQUIRED;


    Class<? extends Throwable>[] rollbackFor() default RuntimeException.class;
}
