package com.hmoro.condition;

import com.hmoro.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     *  importingClassMetadata: 当前类的注解信息
     *  registry: BeanDefinition 注册类, 把所有需要添加到容器中的 bean ,BeanDefinitionRegistry.registerBeanDefinition自定义注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.hmoro.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.hmoro.bean.Blue");
        if(red && blue) {
            // 指定Bean定义信息(Bean 的类型，作用域等)
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            // 注册一个 Bean,指定 bean 名
            registry.registerBeanDefinition("rainBow",beanDefinition);
        }
    }
}
