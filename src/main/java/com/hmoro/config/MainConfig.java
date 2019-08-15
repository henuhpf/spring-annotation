package com.hmoro.config;

import com.hmoro.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // 告诉Spring 这是一个配置类
@ComponentScan(value = "com.hmoro",includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
},excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Service.class})
},useDefaultFilters = false)
// @ComponentScan value:指定扫描的包
// excludeFilters = ComponentScan.Filter[],扫描的时候按照什么规则排除哪些组件
// includeFilters = ComponentScan.Filter[],扫描的时候只包含哪些组件
// FilterType.ANNOTATION: 按照注解
// FilterType.ASSIGNABLE_TYPE: 按照给定的类型
// FilterType.ASPECTJ: 使用 ASPECTJ 表达式
// FilterType.REGEX: 使用正则表达式
// FilterType.CUSTOM: 使用自定义规则
public class MainConfig {
    // 给容器注册一个 Bean, 类型为返回值的类型，id默认是用方法名作为id
    @Bean
    public Person person(){
        return new Person("lisi", 20);
    }
}

