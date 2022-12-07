package com.load.food.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Around("execution(* com..service.*Service.find*(..))")
    public Object findLogging(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        log.info("==> [LogAspect] findLogging Root : {}", pjp.getSignature().getDeclaringTypeName());
        log.info("==> [LogAspect] findLogging Method : {}", pjp.getSignature().getName());
        return result;
    }
}
