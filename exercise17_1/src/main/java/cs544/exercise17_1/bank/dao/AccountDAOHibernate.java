package cs544.exercise17_1.bank.dao;

import java.util.*;

import cs544.exercise17_1.bank.domain.Account;
import org.hibernate.SessionFactory;

public class AccountDAOHibernate implements IAccountDAO {
	private SessionFactory sf;
	public AccountDAOHibernate(SessionFactory sf) {
		this.sf = sf;
	}

	public void saveAccount(Account account) {
		// System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
		//accountlist.add(account); // add the new
		sf.getCurrentSession().persist(account);
	}

	public void updateAccount(Account account) {
		// System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
		/*Account accountexist = loadAccount(account.getAccountnumber());
		if (accountexist != null) {
			accountlist.remove(accountexist); // remove the old
			accountlist.add(account); // add the new
		}*/
		sf.getCurrentSession().update(account);
	}

	public Account loadAccount(long accountnumber) {
		// System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
		/*for (Account account : accountlist) {
			if (account.getAccountnumber() == accountnumber) {
				return account;
			}
		}
		return null;*/
		return sf.getCurrentSession().get(Account.class, accountnumber);
	}

	public Collection<Account> getAccounts() {
		//return accountlist;
		return sf.getCurrentSession().createQuery("from Account").list();
	}

}
