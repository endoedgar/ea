package cs544.exercise15_2.bank.advice;

import cs544.exercise15_2.bank.domain.Account;
import cs544.exercise15_2.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DAOLogAdvice {
    private ILogger logger;

    public DAOLogAdvice(ILogger logger) {
        this.logger = logger;
    }
    @Before("execution(* cs544.exercise15_2.bank.dao.IAccountDAO.*(cs544.exercise15_2.bank.domain.Account)) && args(account)")
    public void logDAOCallAccount(JoinPoint joinPoint, Account account) {
        logger.log("IAccountDAO: executing " + joinPoint.getSignature().getName() + " with accountnr ="+account.getAccountnumber());
    }

    @Before("execution(* cs544.exercise15_2.bank.dao.IAccountDAO.*(long)) && args(accountnumber)")
    public void logDAOCallLong(JoinPoint joinPoint, long accountnumber) {
        logger.log("IAccountDAO: executing " + joinPoint.getSignature().getName() + " with accountnr ="+accountnumber);
    }
}
