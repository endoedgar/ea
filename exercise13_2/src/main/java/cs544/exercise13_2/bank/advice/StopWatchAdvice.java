package cs544.exercise13_2.bank.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class StopWatchAdvice {
    @Around("execution(* cs544.exercise13_2.bank.service.*.*(..))")
    public Object methodTimer(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(proceedingJoinPoint.getSignature().getName());
        Object retVal = proceedingJoinPoint.proceed();
        stopWatch.stop();
        long time = stopWatch.getLastTaskTimeMillis();

        // I'm using the console this time as given by the exercise
        System.out.println(proceedingJoinPoint.getTarget().getClass().getName() + " method " + proceedingJoinPoint.getSignature().getName() + " took " + time + " ms");

        return retVal;
    }
}
