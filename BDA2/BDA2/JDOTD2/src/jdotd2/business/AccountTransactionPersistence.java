package jdotd2.business;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import jdotd2.dao.Account;
import jdotd2.dao.AccountTransaction;
import jdotd2.helper.MyAppPersistenceManagerFactory;

/***
 * 
 * @author vannaraloch 
 * @Description 
 * This class is used to insert every transaction of one's
 * account
 */
public class AccountTransactionPersistence {
	private static PersistenceManagerFactory pmf = MyAppPersistenceManagerFactory
			.getInstance().getPersistenceManagerFactory();
	private static PersistenceManager pm = pmf.getPersistenceManager();
	private static Transaction tx;

	/***
	 * insert a credit transaction to an account 
	 * at the same the account balance is plus (deposit) 
	 * @param accountNumber
	 * @param transactionDate
	 * @param source
	 * @param debit
	 */
	public static void insertDebitTransaction(String accountNumber,
			Date transactionDate, String source, Double debit) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm
					.newQuery("SELECT FROM jdotd2.dao.Account WHERE accountNumber== :accountNumber");
			@SuppressWarnings("unchecked")
			List<Account> account = (List<Account>) q.execute(new String(
					accountNumber));
			Iterator<Account> iter = account.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber().equals(accountNumber)) {
					// persist new record in AccountTransaction
					AccountTransaction transaction = new AccountTransaction();
					transaction.setSourceOfTransaction(source);
					transaction.setDebit(debit);
					transaction.setTransactionDate(transactionDate);
					transaction.setAccount(a);

					AccountPersistence.deposit(a, debit); // should return new
															// object of account
															// after deposit?

					transaction.setAmountAfterTransaction(a
							.getRemainingAmount());
					pm.makePersistent(transaction);

					System.out.println("[INFO] Deposit " + debit
							+ " to Account Number " + accountNumber);
					System.out.println("[INFO] Remaining credit "
							+ a.getRemainingAmount());
				}

			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}

	}

	/***
	 * insert a credit transaction to an account 
	 * at the same the account balance is minus (withdraw) 
	 * @param accountNumber
	 * @param transactionDate
	 * @param source
	 * @param credit
	 */
	public static void insertCreditTransaction(String accountNumber,
			Date transactionDate, String source, Double credit) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm
					.newQuery("SELECT FROM jdotd2.dao.Account WHERE accountNumber== :accountNumber");
			@SuppressWarnings("unchecked")
			List<Account> account = (List<Account>) q.execute(new String(
					accountNumber));
			Iterator<Account> iter = account.iterator();
			while (iter.hasNext()) {
				Account a = iter.next();
				if (a.getAccountNumber().equals(accountNumber)) {
					// persist new record in AccountTransaction
					AccountTransaction transaction = new AccountTransaction();
					transaction.setSourceOfTransaction(source);
					transaction.setCredit(credit);
					transaction.setTransactionDate(transactionDate);
					transaction.setAccount(a);

					AccountPersistence.withdraw(a, credit);

					transaction.setAmountAfterTransaction(a
							.getRemainingAmount());

					pm.makePersistent(transaction);

					System.out.println("[INFO] Withdraw " + credit
							+ " from Account Number " + accountNumber);
					System.out.println("[INFO] Remaining credit "
							+ a.getRemainingAmount());
				}

			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/***
	 * get all transaction of an account
	 * @param accountNumber
	 * @return list of transactions
	 */
	public static List<AccountTransaction> getAllTransactions(String accountNumber) {
		try {
			Query q = pm.newQuery("SELECT FROM jdotd2.dao.AccountTransaction WHERE account.accountNumber== :accountNumber ORDER BY transactionDate ASCENDING");
			
			@SuppressWarnings("unchecked")
			List<AccountTransaction> accountTrans = (List<AccountTransaction>) q.execute(new String(
					accountNumber));
			
			System.out.println("[INFO] Retrieve transaction from account "
					+ accountNumber);
			
			return accountTrans;
		} finally {

		}
	}
	
	/***
	 * get all transaction of an account between dates
	 * @param accountNumber
	 * @param from
	 * @param to
	 * @return list of transactions
	 */
	public static List<AccountTransaction> getTransactionsBetweenDates(String accountNumber, Date from, Date to) {
		try {
			Query q = pm.newQuery("SELECT FROM jdotd2.dao.AccountTransaction WHERE account.accountNumber== :accountNumber "
					+ " & (transactionDate>= :date1 && transactionDate<= :date2) ORDER BY transactionDate ASCENDING");
			
			@SuppressWarnings("unchecked")
			List<AccountTransaction> accountTrans = (List<AccountTransaction>) 
													q.execute(new String(accountNumber)
													, from
													, to
													);
			
			
			System.out.println("[INFO] Retrieve transaction from account "
					+ accountNumber + " between " + from + " and " + to);
			
			System.out.println("Found: " + accountTrans.size() + " records ");
			return accountTrans;
		} finally {

		}
	}
	
	/***
	 * get only the bank charge transactions
	 * @param accountNumber
	 * @return
	 */
	public static List<AccountTransaction> getBankChargeTransaction(String accountNumber) {
		try {
			Query q = pm.newQuery("SELECT FROM jdotd2.dao.AccountTransaction WHERE account.accountNumber== :accountNumber "
					+ " & sourceOfTransaction=='Bank_Charge' ");
		
			@SuppressWarnings("unchecked")
			List<AccountTransaction> accountTrans = (List<AccountTransaction>) 
													q.execute(new String(accountNumber));
			
			
			System.out.println("[INFO] Retrieve bank charge transaction from account "
					+ accountNumber);
			
			return accountTrans;
		} finally {

		}
	}
	
	/***
	 * get only the interest transactions
	 * @param accountNumber
	 * @return list of transactions
	 */
	public static List<AccountTransaction> getInterestTransaction(String accountNumber) {
		try {
			Query q = pm.newQuery("SELECT FROM jdotd2.dao.AccountTransaction WHERE account.accountNumber== :accountNumber "
					+ " & sourceOfTransaction=='Interest' ");
		
			@SuppressWarnings("unchecked")
			List<AccountTransaction> accountTrans = (List<AccountTransaction>) 
													q.execute(new String(accountNumber));
			
			
			System.out.println("[INFO] Retrieve Interest transaction from account "
					+ accountNumber);
			
			return accountTrans;
		} finally {

		}
	}
	
}
