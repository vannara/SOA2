package jdotd2.business;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import java.util.Date;

import jdotd2.dao.Account;
import jdotd2.dao.AccountType;
import jdotd2.dao.Client;
import jdotd2.helper.AccountTypeConstant;
import jdotd2.helper.MyAppPersistenceManagerFactory;


/***
 * @author vannaraloch
 * @description This class is used to create client's account and movement of
 *              amount in the account
 */

public class AccountPersistence {
	private static PersistenceManagerFactory pmf = MyAppPersistenceManagerFactory
			.getInstance().getPersistenceManagerFactory();
	private static PersistenceManager pm = pmf.getPersistenceManager();
	private static Transaction tx;

	@SuppressWarnings("unused")
	public static void createAccount(String accountNumber, long clientId,
			int accountTypeId, Double initialAmount) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			Account account = new Account(accountNumber);
			Client client = pm.getObjectById(Client.class, clientId);
			account.setClient(client);
			AccountType accountType = pm.getObjectById(AccountType.class,
					accountTypeId);
			account.setAccountType(accountType);

			Calendar cal = Calendar.getInstance();
			int cal_for_month = cal.get(Calendar.MONTH);
			int cal_for_day = cal.get(Calendar.DAY_OF_MONTH);
			Date date = (Date) cal.getTime();
			@SuppressWarnings("deprecation")
			Date creationDate = new Date(date.getYear(), cal_for_month,
					cal_for_day);
			account.setCreationDate(creationDate);
			account.setRemainingAmount(initialAmount);

			pm.makePersistent(account);

			tx.commit();
			System.out.println("Account have been created");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/***
	 * delete a given account
	 * 
	 * @param accountNumber
	 * @return
	 */
	public static boolean deleteAccount(String accountNumber) {
		boolean isDeleted = false;
		try {
			Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
					+ " WHERE accountNumber== :accNumber");
			List<Account> account = (List<Account>) q.execute(accountNumber);
			Iterator<Account> iter = account.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber() == accountNumber) {
					pm.deletePersistent(a);
					isDeleted = true;
				}
			}

		} finally {

		}
		return isDeleted;
	}

	/***
	 * update account with new remaining amount (add up remaining amount)
	 * 
	 * @param account
	 * @param depositAmount
	 */
	public static void deposit(Account account, Double depositAmount) {
		try {
			account.setRemainingAmount(account.getRemainingAmount()
					+ depositAmount);

		} finally {

		}

	}

	/***
	 * update account with new remaining amount (substract remaining amount)
	 * 
	 * @param account
	 * @param widthrawalAmount
	 */
	public static void withdraw(Account account, Double widthrawalAmount) {
		try {
			account.setRemainingAmount(account.getRemainingAmount()
					- widthrawalAmount);
		} finally {

		}

	}

	/***
	 * get all accounts
	 * 
	 * @return list of account
	 */
	public static List<Account> getAllAccounts() {
		try {
			System.out.println("[INFO] Retrieve  all accounts");
			Query q = pm.newQuery(Account.class);
			@SuppressWarnings("unchecked")
			List<Account> accounts = (List<Account>) q.execute();

			return accounts;
		} finally {

		}

	}

	/***
	 * get all accounts whose balance is less than 0
	 * 
	 * @return list of account
	 */
	public static List<Account> getAllDebtorAccounts() {
		try {
			System.out.println("[INFO] Retrieve  all debtor accounts");
			Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
					+ " WHERE remainingAmount<0");
			@SuppressWarnings("unchecked")
			List<Account> accounts = (List<Account>) q.execute();

			return accounts;
		} finally {

		}

	}

	/***
	 * get account balance return account balance
	 */
	public static Double getAccountBalance(String accountNumber) {
		Double balance = 0.0;
		try {
			System.out.println("[INFO] Retrieve account balance");
			Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
					+ " WHERE accountNumber== :accountNumber");
			@SuppressWarnings("unchecked")
			List<Account> accounts = (List<Account>) q.execute(new String(
					accountNumber));
			Iterator<Account> iter = accounts.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber() == accountNumber) {
					balance = a.getRemainingAmount();
					break;
				}
			}
			return balance;
		} finally {

		}
	}

	/***
	 * get account type return account type
	 */
	public static String getAccountType(String accountNumber) {
		String type = "";
		try {
			System.out.println("[INFO] Retrieve account type");
			Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
					+ " WHERE accountNumber== :accountNumber");
			@SuppressWarnings("unchecked")
			List<Account> accounts = (List<Account>) q.execute(new String(
					accountNumber));
			Iterator<Account> iter = accounts.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber() == accountNumber) {
					type = a.getAccountType().getAccountType();
					break;
				}
			}
			return type;
		} finally {

		}
	}

	/***
	 * get first bank charge return fist bank charge
	 */
	public static Double getBankChargeRate(String accountNumber) {
		Double chargeRate = 0.0;
		try {
			System.out.println("[INFO] Retrieve bank charge rate");
			Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
					+ " WHERE accountNumber== :accountNumber");
			@SuppressWarnings("unchecked")
			List<Account> accounts = (List<Account>) q.execute(new String(
					accountNumber));
			Iterator<Account> iter = accounts.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber() == accountNumber) {
					chargeRate = a.getAccountType().getBankCharge();
					break;
				}
			}
			return chargeRate;
		} finally {

		}
	}

	/***
	 * get second bank charge return second bank charge
	 */
	public static Double getSecondBankChargeRate(String accountNumber) {
		Double chargeRate = 0.0;
		try {
			System.out.println("[INFO] Retrieve bank charge rate");
			Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
					+ " WHERE accountNumber== :accountNumber");
			@SuppressWarnings("unchecked")
			List<Account> accounts = (List<Account>) q.execute(new String(
					accountNumber));
			Iterator<Account> iter = accounts.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber() == accountNumber) {
					chargeRate = a.getAccountType().getSecondBankCharge();
					break;
				}
			}
			return chargeRate;
		} finally {

		}
	}

	/***
	 * get creation date of an account
	 * 
	 * @param accountNumber
	 * @return
	 */
	public static Date getAccountCreationDate(String accountNumber) {
		Date date = null;
		System.out.println("[INFO] Retrieve bank charge rate");
		Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
				+ " WHERE accountNumber== :accountNumber");
		@SuppressWarnings("unchecked")
		List<Account> accounts = (List<Account>) q.execute(new String(
				accountNumber));
		Iterator<Account> iter = accounts.iterator();
		while (iter.hasNext()) {
			Account a = iter.next();
			if (a.getAccountNumber() == accountNumber) {
				date = a.getCreationDate();
				break;
			}
		}
		return date;
	}

	/***
	 * get interest rate return interest rate
	 */
	public static Double getInterestRate(String accountNumber) {
		Double interestRate = 0.0;
		try {
			System.out.println("[INFO] Retrieve bank charge rate");
			Query q = pm.newQuery("SELECT FROM " + Account.class.getName()
					+ " WHERE accountNumber== :accountNumber");
			@SuppressWarnings("unchecked")
			List<Account> accounts = (List<Account>) q.execute(accountNumber);
			Iterator<Account> iter = accounts.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber() == accountNumber) {
					interestRate = a.getAccountType().getInterest();
					break;
				}
			}
			return interestRate;
		} finally {

		}
	}

	/***
	 * This function supposed to be called in a Thread which will execute every day at 0:00
	 * but in this application I used manual action
	 * calculate bank charge return charge amount to be substract from the
	 * balance
	 */
	public static Double calculateChargeAmount(String accountNumber) {
		Double chargeAmt = 0.0;
		Double balance = getAccountBalance(accountNumber);
		Double firstChargeRate = getBankChargeRate(accountNumber);
		Double secondChargeRate = getSecondBankChargeRate(accountNumber);
		String accountType = getAccountType(accountNumber);

		try {
			if (!accountType.toUpperCase()
					.equals(AccountTypeConstant.PRIVILEGE)) {
				chargeAmt = balance * ((firstChargeRate / 100) / 365);
			} else { // privilege account
				long durationInDays;
				Date creationDate = getAccountCreationDate(accountNumber);
				Date calculationDate = new Date();
				long diff = calculationDate.getTime() - creationDate.getTime();
		    	durationInDays = diff / (60 * 60 * 1000 * 24);
		    	
		    	if(durationInDays>10) {
		    		/**
		    		 * calculate bank charge
		    		 */
		    		if(balance<0) {
		    			balance = Math.abs(balance);
		    			
		    			if(balance>5000) { //apply the first bank charge
		    				//example if balance =-6000
			    			Double balancePart= balance - 5000; // -> 1000
			    			chargeAmt = 5000 * ((firstChargeRate/100)/365); // apply first bank charge to first 5000
			    			chargeAmt += balancePart * ((secondChargeRate/100)/365);  // apply second bank charge to the 1000
			    		}
			    		else if(balance<5000) {
			    			chargeAmt = Math.abs(balance) * ((firstChargeRate/100)/365);
			    		}
		    		}
		    	
		    	}
			    
			}

			return Math.abs(chargeAmt);
		} finally {

		}
	}

	/***
	 * calculate interest return interest amount to be added to the balance
	 */
	public static Double calculateInterest(String accountNumber) {
		Double interest = 0.0;
		Double balance = getAccountBalance(accountNumber);
		Double interestRate = getInterestRate(accountNumber);
		String accountType = getAccountType(accountNumber);

		try {
			if (accountType.toUpperCase().equals(AccountTypeConstant.REWARDING)) {
				if (balance >= 10000) {
					interest = balance * ((interestRate / 100) / 365);
				}
			}

			return interest;
		} finally {

		}
	}
}
