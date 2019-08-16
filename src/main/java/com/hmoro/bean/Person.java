package com.hmoro.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Person {
    /**
     * 使用 @Value 赋值
     *      1、基本数值
     *      2、SpEL: #{}
     *      3、${}，取出配置文件(properties)中的值(在运行的环境变量中的值)
     */

    @Value("张")
    private String name;
    @Value("#{20-2}")
    private int age;
    @Value("${person.nickName}")
    private String nickName;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
