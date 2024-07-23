package by.artur.internship.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* by.artur.internship.service.*(..))")
    public void servicePointcut() {
    }

    @Around("@annotation(CustomLogger)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Method " + methodName + " is called with arguments: " + Arrays.toString(args));

        Object result = joinPoint.proceed();
        logger.info("Method result: " + result);

        return result;
    }

    //TODO check how its working, mb try{} catch{}?
    @AfterThrowing(
            pointcut = "servicePointcut()",
            throwing = "e"
    )
    public Object aroundAdviceException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable e) {
            logger.severe(e.getMessage());
            logger.info("Retrying operation");
            return pjp.proceed(pjp.getArgs());
        }
    }

}
