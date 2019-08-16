package com.hmoro.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {
//    private static final String name = "cat";
//    private int age;
//    private static String addr;
    public Cat() {
        System.out.println("cat::constructor");
//        System.out.println(name + ":" + age);
    }
//    static {
//        System.out.println(name + addr);
//    }
//    {
//        age = 10;
//        System.out.println(name);
//    }
    @Override
    public void destroy() throws Exception {
        System.out.println("cat::destroy");
//        System.out.println(name + ":" + age);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat::afterPropertiesSet");
//        System.out.println(name + ":" + age);
    }
}
