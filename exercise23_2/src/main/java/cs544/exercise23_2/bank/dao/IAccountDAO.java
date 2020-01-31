package cs544.exercise23_2.bank.dao;

import java.util.Collection;

import cs544.exercise23_2.bank.domain.Account;



public interface IAccountDAO {
	public void saveAccount(Account account);
	public void updateAccount(Account account);
	public Account loadAccount(long accountnumber);
	public Collection<Account> getAccounts();
}
