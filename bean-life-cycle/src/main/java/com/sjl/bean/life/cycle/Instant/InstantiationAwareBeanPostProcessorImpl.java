package com.sjl.bean.life.cycle.Instant;

import com.sjl.bean.life.cycle.bean.Cat;
import com.sjl.bean.life.cycle.constants.Constant;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * @author: JianLei
 * @date: 2020/9/8 1:40 下午
 * @description:
 */
@Component
public class InstantiationAwareBeanPostProcessorImpl
    implements InstantiationAwareBeanPostProcessor {
  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
      throws BeansException {
    System.out.println(
            Constant.count.incrementAndGet()+"、->*************[InstantiationAwareDemo  postProcessBeforeInstantiation 执行! ]**************");
    return null;
  }

  @Override
  public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
    System.out.println(
            Constant.count.incrementAndGet()+"、->---------[InstantiationAwareDemo  postProcessAfterInstantiation 执行! ]--------");
    //postProcessProperties 方法执行依赖于postProcessAfterInstantiation返回 true
    return true;
  }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {

    System.out.println(
        Constant.count.incrementAndGet()
            + "、->============[InstantiationAwareDemo  postProcessPropertyValues 执行! ]======================"
            + Arrays.toString(pvs.getPropertyValues())+"bean: "+bean.getClass().getSimpleName()+"->beanName: "+beanName);
        return null;
    }
}
