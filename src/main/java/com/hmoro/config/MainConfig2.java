package com.hmoro.config;

import com.hmoro.bean.Person;
import com.hmoro.condition.LinuxCondition;
import com.hmoro.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

// 满足当前条件，这个类中配置的所有 Bean 才会生效
//@Conditional({WindowsCondition.class})
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
     *
     * 懒加载:
     *      单实例 bean : 默认在容器启动的时候创建对象;
     *      懒加载 : 容器启动时不创建对象，第一次获取 Bean创建对象，并初始化;
     */
//    @Scope("prototype")
    @Bean("person")
    @Lazy
    public Person person(){
        System.out.println("给容器添加person");
        return new Person("张三",25);
    }
    /**
     * @Conditional(condition[]) : 按照一定的条件进行判断，满足条件给容器中注册 bean
     *
     * 如果系统是 Windows,给容器注册 tom
     * 如果系统是 Linux, 给容器注册 jerry
     */
    @Conditional({WindowsCondition.class})
    @Bean("tom")
    public Person person01(){
        return new Person("Tom",79);
    }
    @Conditional({LinuxCondition.class})
    @Bean("jerry")
    public Person person02(){
        return new Person("Jerry",79);
    }
}
