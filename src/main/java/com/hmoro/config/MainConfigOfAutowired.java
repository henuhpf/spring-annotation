package com.hmoro.config;

import com.hmoro.bean.Car;
import com.hmoro.bean.Color;
import com.hmoro.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配
 *      Spring 利用依赖注入(DI),完成对IOC 容器中各个组件的依赖关系赋值
 *
 * 1、@Autowired 自动注入
 *      1)、默认有限按照类型区容器中找对应的组件 : applicationContext.getBean(BookDao.class);
 *      2)、 如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *      3)、@Qualifier("bookDao"):  @Qualifier指定需要装配的组件id，而不是使用属性名
 *      4)、自动装配默认一定要将属性赋值好，没有就会报错
 *              可以使用@Autowired(required = false);
 *      5)、@Primary : 让Spring 进行自动装配的时候默认使用首选的bean
 *                      也可以继续使用@Qualifier指定需要装配的组件id
 *          BookService{
 *              @Autowired
 *              BookDao bookDao;
 *          }
 * 2、Spring还支持使用 @Resource 和 @Inject [java规范的注解]
 *      @Resource:
 *              可以和 @Autowired 一样实现自动装配功能,默认按照组件名称装配,
 *              不支持 @Primary 和 @Autowired(required = false)
 *      @Inject:
 *              需要导入 javax.inject包，和 Autowired 功能一样，但是Inject没有(required = false)功能
 * @Autowired 是Spring定义的; @Resource 和 @Inject都是java规范
 *
 * 3、@Autowired : 构造器、参数、方法、属性   bean.Boss
 *      1)、标注在方法位置
 *      2)、标注在构造器上, 如果组件只有一个有参构造器，这个有参构造器的 @Autowired 可以省略，
 *                          参数位置的组件还是可以自动从容器中获取
 *      3)、放在参数位置
 */
@Configuration
@ComponentScan({"com.hmoro.controller","com.hmoro.service",
        "com.hmoro.dao","com.hmoro.bean"})
public class MainConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    // @Bean 标注的方法创建对象的时候，方法参数的值从容器中获取
    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
