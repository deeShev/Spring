package com.shevelev.spring.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods(){ }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println(joinPoint.getTarget().getClass().getName());
    }
}
