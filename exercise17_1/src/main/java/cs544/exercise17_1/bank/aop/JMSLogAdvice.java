package cs544.exercise17_1.bank.aop;

import cs544.exercise17_1.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class JMSLogAdvice {
    private ILogger logger;

    public JMSLogAdvice(ILogger logger) {
        this.logger = logger;
    }

    @Before("execution(* cs544.exercise17_1.bank.jms.IJMSSender.sendJMSMessage(String)) && args(text)")
    public void logJMSMessage(JoinPoint joinPoint, String text) {
        logger.log("IJMSSender: executing " + joinPoint.getSignature().getName() + " with text ="+text);
    }
}
