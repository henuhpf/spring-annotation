package com.hmoro.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {
    public Car() {
        System.out.println("car constructor...");
    }
    public void init(){
        System.out.println("cat::init");
    }
    public void destroy(){
        System.out.println("cat::destroy");
    }
}
