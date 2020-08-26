package com.sjl.enable.scan;

import com.sjl.enable.annotation.SjlService;
import com.sjl.enable.filter.SjlTypeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author: jianlei
 * @date: 2020/8/25
 * @description: SjlBtaisScanner
 */
@Slf4j
public class SjlBtaisScanner extends ClassPathBeanDefinitionScanner {
    private String basePackage;
    private String beanName;
    private Class<? extends Annotation> annotationClass;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public SjlBtaisScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int scan(String... basePackages) {
        final int scan = super.scan(basePackages);
        log.info("==========SjlBtaisScanner beagin scanner count is:{}================",scan);
        return super.scan(basePackages);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }

    public void registerFilters() {
        super.addIncludeFilter(new AnnotationTypeFilter(SjlService.class));
        // exclude package-info.java

    }

    @Override
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        return super.findCandidateComponents(basePackage);
    }
}
