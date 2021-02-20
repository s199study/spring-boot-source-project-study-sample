package com.github.nacos.sample.config;

import com.github.nacos.sample.config.factory.ConfigPropertySourceFactory;
import com.github.nacos.sample.config.listener.AutoUpdateConfigChangeListener;
import com.github.nacos.sample.config.service.ConfigService;
import com.github.nacos.sample.config.source.ConfigPropertySource;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import common.constants.PropertySourcesConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jianlei.shi
 * @date 2021/2/19 6:57 下午
 * @description PropertySourcesProcessor
 */
@Component
@DependsOn("utils.SpringUtils")
public class PropertySourcesProcessor implements BeanFactoryPostProcessor, EnvironmentAware, PriorityOrdered {
    private static final Multimap<Integer, String> NAMESPACE_NAMES = LinkedHashMultimap.create();

    static {
        NAMESPACE_NAMES.put(-2, "simple");
    }

    private ConfigurableEnvironment environment;

    private final ConfigPropertySourceFactory configPropertySourceFactory = new ConfigPropertySourceFactory();

    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        initializePropertySources();
        initializeAutoUpdatePropertiesFeature(beanFactory);
    }

    private void initializeAutoUpdatePropertiesFeature(ConfigurableListableBeanFactory beanFactory) {
        AutoUpdateConfigChangeListener autoUpdateConfigChangeListener = new AutoUpdateConfigChangeListener(
                environment, beanFactory);

        List<ConfigPropertySource> configPropertySources = configPropertySourceFactory.getAllConfigPropertySources();
        for (ConfigPropertySource configPropertySource : configPropertySources) {
            //添加热更新监听器
            configPropertySource.addChangeListener(autoUpdateConfigChangeListener);
        }
        if (config != null && config instanceof DefaultConfig) {
            DefaultConfig defaultConfig = (DefaultConfig) config;
            defaultConfig.sync();
        }
    }

    private void initializePropertySources() {

        if (environment.getPropertySources().contains(PropertySourcesConstants.SIMPLE_PROPERTY_SOURCE_NAME)) {
            //already initialized
            return;
        }
        CompositePropertySource composite = new CompositePropertySource(PropertySourcesConstants.SIMPLE_PROPERTY_SOURCE_NAME);

        //sort by order asc

        Config cfig = ConfigService.getConfig(null);
        this.setConfig(cfig);
        composite.addPropertySource(configPropertySourceFactory.getConfigPropertySource("simple-config", config));


        environment.getPropertySources().addFirst(composite);
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
