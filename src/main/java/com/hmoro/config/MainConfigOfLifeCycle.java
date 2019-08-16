package com.hmoro.config;

import com.hmoro.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean 的生命周期
 *      bean创建 -- 初始化 -- 销毁
 *  容器管理bean的生命周期;
 *  可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候来调用自定义的初始化和销毁方法
 *  执行顺序:
 *  - 构造方法(对象创建)
 *         单实例 : 在容器启动时创建对象
 *         多实例 : 在每次获取的时候创建对象
 *  - BeanPostProcessor.postProcessBeforeInitialization
 *  - 初始化:
 *          对象创建完成，并赋值之后，调用初始化
 *  - BeanPostProcessor.postProcessAfterInitialization
 *  - 销毁 :
 *          单实例 : 容器关闭的时候进行销毁
 *          多实例 : 容器不会管理这个 Bean，即容器不会调用销毁方法;
 *
 *
 *  BeanPostProcessor
 *  populateBean(beanName, mbd, instanceWrapper); 给bean进行属性赋值
 *  initializeBean {
 *      applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName){
 *          遍历的到容器中所有的 BeanPostProcessor, 挨个执行 postProcessBeforeInitialization,
 *          如果返回null，跳出 for 循环，不会执行后面的 PostProcessorsAfterInitialization
 *      }
 *      invokeInitMethods(beanName, wrappedBean, mbd); 执行自定义初始化方法
 *      applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *  }
 *
 *
 *  1、指定初始化和销毁方法 : Car
 *          指定 init-method 和 destroy-method
 *  2、通过让 Bean 实现 InitializingBean (定义初始化逻辑) Cat
 *                实现 Disposable (定义销毁逻辑)
 *  3、可以使用 @PostConstruct: 在bean创建完成，且属性赋值完成，执行初始化方法 Dao
 *             @PreDestroy: 在容器销毁 bean 之前，通知进行清理工作
 *  4、BeanPostProcessor[interface] : bean的后置处理器 MyBeanPostProcessor
 *          在bean 初始化前后进行一些处理工作
 *          postProcessBeforeInitialization : 在初始化之前调用
 *          postProcessAfterInitialization : 在初始化之后调用
 *
 *
 */
// Car 、Cat、Dog、MyBeanPostProcessor
@ComponentScan("com.hmoro.bean")
@Configuration
public class MainConfigOfLifeCycle {

//    @Scope("prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
