package com.example.vending.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogTraceAop {

    @Around("execution(* com.example.vending..*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug(joinPoint.toString());
        return joinPoint.proceed();
    }
}
