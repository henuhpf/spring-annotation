package com.hmoro.config;

import com.hmoro.aop.LogAspects;
import com.hmoro.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP : [动态代理]
 *      指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 * 1、导包spring-aspects.jar
 * 2、定义一个业务逻辑类 MathCalculator,在业务逻辑运行的时候将日志进行打印(方法之前、方法运行结束、方法出现异常等)
 * 3、定义一个日志切面类 LogAspects, 切面类里面的方法需要动态感知 MathCalculator.div 运行到哪里然后执行
 *      通知方法:
 *          前置通知(@Before): logStart      在目标方法(div)运行之前运行
 *          后置通知(@After): logEnd        在目标方法(div)运行结束之后运行，无论方法正常结束或异常结束
 *          返回通知(@AfterReturning): logReturn     在目标方法(div)正常返回之后运行
 *          异常通知(@AfterThrowing): logException  在目标方法(div)出现异常后运行
 *          环绕通知(@Around): 动态代理，手动推进目标方法运行 (joinPoint.procced())
 * 4、给切面类的目标方法标准何时何地运行(通知注解)
 * 5、将切面类和业务逻辑类(目标方法所在类)都加入到容器中
 * 6、必须告诉Spring 哪个类是切面类 给切面类加一个注解 @Aspect
 * 7、给配置类加上@EnableAspectJAutoProxy，开启给予注解的aop 模式
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAop {
    // 业务逻辑类加入容器中
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    // 切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
