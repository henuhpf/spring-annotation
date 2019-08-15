package com.hmoro.config;

import com.hmoro.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig2 {
    /*
     * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE prototype
     * @see ConfigurableBeanFactory#SCOPE_SINGLETON singleton
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST request
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION session
     * prototype: 多实例 ,IOC 容器启动不会调用方法创建对象，
     *              每次获取的时候才会调用方法创建对象，且每次获取都会调用一遍
     * singletion: 单实例 (默认) ,IOC 容器启动会调用方法创建对象放到 IOC 容器中
     * request: 同一次请求创建一个实例
     * session: 同一个 session 创建一个实例
     */
    @Scope("prototype")
    @Bean("person")
    public Person person(){
        System.out.println("给容器添加person");
        return new Person("张三",25);
    }
}
