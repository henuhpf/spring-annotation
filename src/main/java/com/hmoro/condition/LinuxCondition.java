package com.hmoro.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

// 判断是否是 Linux 系统
public class LinuxCondition implements Condition {
    /**
     *
     * @param context 判断条件能使用的上下文 (环境)
     * @param metadata 注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 能获取到 IOC 使用的 beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取当前环境信息
        Environment environment = context.getEnvironment();
        // 获取 bean 定义的注册
        BeanDefinitionRegistry registry = context.getRegistry();

        String property = environment.getProperty("os.name");
        // 可以判断容器中 bean 的注册情况, 也可以给容器注册 bean
        boolean person = registry.containsBeanDefinition("person");
        if(property.contains("Linux")){
            return true;
        }
        return false;
    }
}
