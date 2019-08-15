package com.hmoro.config;

import com.hmoro.bean.Color;
import com.hmoro.bean.ColorFactoryBean;
import com.hmoro.bean.Person;
import com.hmoro.bean.Red;
import com.hmoro.condition.LinuxCondition;
import com.hmoro.condition.MyImportBeanDefinitionRegistrar;
import com.hmoro.condition.MyImportSelector;
import com.hmoro.condition.WindowsCondition;
import org.springframework.context.annotation.*;

// 满足当前条件，这个类中配置的所有 Bean 才会生效
//@Conditional({WindowsCondition.class})
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) // 导入组件、id默认是组件的全类名
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

    /**
     *  给容器中注册组件
     *  1、包扫描 + 组件标注注解 (@Controller、@Service、@Repository、@Component)    自己写的类
     *  2、@Bean[导入的第三方包里面的组件]
     *  3、@Import[快速给容器中导入一个组件]
     *      1)、@Import(要导入到容器中的组件); 容器会自动注册这个组件,id默认是全类名
     *      2)、ImportSelector: 返回需要导入的组件全类名的数组
     *      3)、ImportBeanDefinitionRegistrar: 手动注册 Bean 到容器中
     *  4、使用 Spring 提供的 FactoryBean(工厂 Bean)
     *      1)、默认获取到的是工厂Bean调用getObject创建的对象
     *      2)、要获取到工厂Bean本身，需要在id前面加一个"&"
     */

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
