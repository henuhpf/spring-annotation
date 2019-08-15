package com.hmoro;

import com.hmoro.bean.Person;
import com.hmoro.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
//        Person person = (Person) applicationContext.getBean("person");
//        System.out.println(person);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String person : beanNamesForType ) {
            System.out.println(person);
        }
    }
}
