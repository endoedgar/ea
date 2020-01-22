package cs544.exercise13_1;

import cs544.exercise13_1.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import java.util.Date;

@Aspect
public class Aspects {
    @After("execution(* cs544.exercise13_1.IEmailSender.sendEmail(..)) && args(email, message)")
    public void logAfterSendEmail(JoinPoint joinPoint, String email, String message) {
        EmailSender emailSender = (EmailSender)(joinPoint.getTarget());
        System.out.println(new Date() + " method= " + joinPoint.getSignature().getName() +
                " address=" + email +  " message= " + message);
        System.out.println("outgoing mail server = " + emailSender.getOutgoingMailServer());
    }

    @Around("execution(* cs544.exercise13_1.ICustomerDAO.save(..)) && args(customer)")
    public Object calculateDuration(ProceedingJoinPoint joinPoint, Customer customer) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(joinPoint.getSignature().getName());
        Object retVal = joinPoint.proceed();
        sw.stop();

        long totalTime = sw.getLastTaskTimeMillis();
        System.out.println("Time to execute " + joinPoint.getSignature().getName() + " = " + totalTime + " ms");

        return retVal;
    }
}
