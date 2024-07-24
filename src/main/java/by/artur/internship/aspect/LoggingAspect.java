package by.artur.internship.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("@annotation(by.artur.internship.annotation.CustomLogger)")
    public Object logMethodCall(ProceedingJoinPoint pjp) throws Throwable {

        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        log.info("Method " + methodName + " is called with arguments: " + Arrays.toString(args));
        Object result = pjp.proceed();
        log.info("Method result: " + result);
        return result;
    }

}