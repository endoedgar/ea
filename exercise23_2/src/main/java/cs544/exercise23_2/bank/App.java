package cs544.exercise23_2.bank;

import java.util.Collection;

import cs544.exercise23_2.bank.domain.Account;
import cs544.exercise23_2.bank.domain.AccountEntry;
import cs544.exercise23_2.bank.domain.Customer;
import cs544.exercise23_2.bank.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"springserviceconfig.xml", "springdaoconfig.xml", "springjmsconfig.xml"});
		IAccountService accountService = context.getBean("accountService", IAccountService.class);
	}

}


