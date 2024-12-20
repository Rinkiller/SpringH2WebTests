package Rin.compani.H2DBUser.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggerServise {

    @Before("execution(* Rin.compani.H2DBUser.Service.UserService.*(..))")
    public void logBeforeMethodCall(JoinPoint joinPoint) {
        Object [] args = joinPoint.getArgs();
        System.out.println("Metod " + joinPoint.getSignature().getName() +" was called with parameters: " + args);}
}
