package com.hmoro.config;

import com.hmoro.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
// 使用 @PropertySource 读取外部配置文件中的 k/v 保存到运行的环境变量中;
// 加载完外部的配置文件以后使用 ${} 取出配置文件中的值
// @PropertySources 可以配置多个 @PropertySource 注解
@PropertySource(value = {"classpath:person.properties"},encoding = "UTF-8")
@Configuration
public class MainConfigOfPropertyValue {

    @Bean
    public Person person(){
        return new Person();
    }

}
