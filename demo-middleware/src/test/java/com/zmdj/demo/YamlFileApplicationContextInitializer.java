package com.zmdj.demo;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * @author zhangyunyun create on 2019/2/25
 */
public class YamlFileApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        try {
            Resource resource = configurableApplicationContext.getResource("classpath:application-test.yml");
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            List<PropertySource<?>> yamlTestProperties = sourceLoader.load("yamlTestProperties", resource);
            configurableApplicationContext.getEnvironment().getPropertySources().addFirst(yamlTestProperties.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
