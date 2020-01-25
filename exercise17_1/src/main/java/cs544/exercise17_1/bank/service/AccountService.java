package cs544.exercise17_1.bank.service;

import java.util.Collection;

import cs544.exercise17_1.bank.dao.IAccountDAO;
import cs544.exercise17_1.bank.domain.Account;
import cs544.exercise17_1.bank.domain.Customer;
import cs544.exercise17_1.bank.jms.IJMSSender;
import cs544.exercise17_1.bank.logging.ILogger;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;


public class AccountService implements IAccountService {
	private IAccountDAO accountDAO;
	private ICurrencyConverter currencyConverter;
	private IJMSSender jmsSender;
	private ILogger logger;
	
	public AccountService(IAccountDAO accountDAO, ICurrencyConverter currencyConverter, IJMSSender jmsSender, ILogger logger){
		this.accountDAO = accountDAO;
		this.currencyConverter = currencyConverter;
		this.jmsSender = jmsSender;
		this.logger = logger;
	}

	@Transactional
	public Account createAccount(long accountNumber, String customerName) throws RuntimeException {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);

		accountDAO.saveAccount(account);

		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return account;
	}

	@Transactional
	public void deposit(long accountNumber, double amount) throws RuntimeException{
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);

		accountDAO.updateAccount(account);

		logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	@Transactional
	public Account getAccount(long accountNumber) throws RuntimeException {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	@Transactional
	public Collection<Account> getAllAccounts() throws RuntimeException  {
		Collection<Account> accounts = accountDAO.getAccounts();
		for(Account account : accounts) {
			Hibernate.initialize(account.getCustomer());
			Hibernate.initialize(account.getEntryList());
		}

		return accounts;
	}

	@Transactional
	public void withdraw(long accountNumber, double amount)  throws RuntimeException  {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);

		logger.log("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	@Transactional
	public void depositEuros(long accountNumber, double amount)  throws RuntimeException {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);

		logger.log("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	@Transactional
	public void withdrawEuros(long accountNumber, double amount) throws RuntimeException {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
		logger.log("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
	}

	@Transactional
	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) throws RuntimeException {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		logger.log("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}
}
