package com.hmoro.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

// 告诉Spring 当前类是一个切面类
@Aspect
public class LogAspects {
    // 抽取公共的切入点表达式
    @Pointcut("execution(public int com.hmoro.aop.MathCalculator.div(int,int))")
    public void pointCut(){};

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "运行。。。@Before参数列表是:{" + Arrays.asList(joinPoint.getArgs()) + "}");
    }
    // 如果是外部类，可以根据全类名引用-
    @After("com.hmoro.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "除法结束。。。@After");
    }

    // JoinPoint 参数一定要出现在参数表的第一位
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result){
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "正常返回。。。@AfterReturning 运行结果是:{" + result + "}");
    }
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "异常。。。@AfterThrowing异常信息:{" + exception + "}");
    }
}
