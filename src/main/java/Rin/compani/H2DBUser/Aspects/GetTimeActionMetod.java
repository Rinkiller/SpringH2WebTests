package Rin.compani.H2DBUser.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class GetTimeActionMetod {
    @Around("execution(* Rin.compani.H2DBUser.Service.UserService.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        System.out.println("Method name " + joinPoint.getSignature() + " ,method execution time " + (endTime - startTime) + " millisecond.");
        return result;
    }
}
