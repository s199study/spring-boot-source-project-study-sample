package com.sjl.custom.aop.factory;

import com.sjl.custom.aop.interceptor.CglibMethodInterceptor;
import com.sjl.custom.aop.proxy.CustomProxy;
import com.sjl.custom.aop.proxy.JdkInvocation;
import com.sjl.custom.aop.support.AdvisedSupport;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: JianLei
 * @date: 2020/9/22 3:56 下午
 * @description:
 */
public class ProxyFactory implements CustomProxy {
  private  AdvisedSupport support;
  private static final AtomicReference<ProxyFactory> factory = new AtomicReference<>();
  private static final Enhancer en = new Enhancer();

  public AdvisedSupport getSupport() {
    return support;
  }

  public void setSupport(AdvisedSupport support) {
    this.support = support;
  }

  public ProxyFactory(AdvisedSupport support) {
    this.support = support;
  }

  public static ProxyFactory getProxyFactory(AdvisedSupport support) {
    for (; ; ) {
      ProxyFactory proxyFactory = factory.get();
      if (proxyFactory != null) {
        proxyFactory.setSupport(support);
        return proxyFactory;
      }
      proxyFactory = new ProxyFactory(support);
      if (factory.compareAndSet(null, proxyFactory)) {
        return proxyFactory;
      }
    }
  }

  @Override
  public <T> T createJDKProxy(Class<?> clazz) {
    return (T)
        Proxy.newProxyInstance(
            this.support.getTarget().getClass().getClassLoader(),
            new Class[] {this.support.getTargetClass()},
            new JdkInvocation(this.support));
  }

  public static <T> T getJDKProxy(AdvisedSupport support) {
    return Objects.requireNonNull(getProxyFactory(support)).createJDKProxy(null);
  }

  @Override
  public <T> T createCGLIBProxy(Class<?> clazz) {
    en.setSuperclass(clazz);
    en.setCallback(new CglibMethodInterceptor());
    return (T) en.create();
  }

  public static <T> T getCGLIBProxy(Class<?> clazz) {
    return Objects.requireNonNull(getProxyFactory(null)).createCGLIBProxy(clazz);
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10000; i++) {
      executorService.execute(
          () -> {
            System.out.println(ProxyFactory.getProxyFactory(null));
          });
    }
    executorService.shutdown();
  }
}
