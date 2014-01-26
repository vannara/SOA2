package jdotd2.business;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import jdotd2.dao.AccountType;
import jdotd2.helper.MyAppPersistenceManagerFactory;

public class AccountTypePersistence {
	private static PersistenceManagerFactory pmf = MyAppPersistenceManagerFactory
			.getInstance().getPersistenceManagerFactory();
	private static PersistenceManager pm = pmf.getPersistenceManager();
	private static Transaction tx;

	public static void insertAccountType(String type, Double firstBankCharge,
			Double secondBankCharge, Double interest) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("[INFO] Persisting Account Type");
		
			AccountType accountType = new AccountType(type);
			accountType.setBankCharge(firstBankCharge);
			accountType.setSecondBankCharge(secondBankCharge);
			accountType.setInterest(interest);
			pm.makePersistent(accountType);

			tx.commit();
			System.out.println("[INFO] Account Type have been persisted");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			
		}
	}

	public static void updateAccountTypeBankCharge(int accTypeId,Double firstBankCharge) {
		try {
			AccountType accType =(AccountType) pm.getObjectById(AccountType.class,accTypeId);
			accType.setBankCharge(firstBankCharge);
			System.out.println("[INFO] Updated Bank Charge");
		
		} finally {
			
		}
		
	}
	
	public static void updateAccountTypeSecondBankCharge(int accTypeId,Double secondBankCharge) {
		try {
			
			AccountType accType =(AccountType) pm.getObjectById(AccountType.class,accTypeId);
			accType.setSecondBankCharge(secondBankCharge);
			System.out.println("[INFO] Updated second bank charge");
			
		} finally {
			
		}
		
	}
	
	public static void updateAccountTypeInterest(int accTypeId,Double interest) {
		try {
			AccountType accType =(AccountType) pm.getObjectById(AccountType.class,accTypeId);
			accType.setInterest(interest);
			System.out.println("[INFO] Updated interest");
			
		} finally {
			
		}
		
	}
	public static void updateAccountType(int accTypeId,String type) {
	
		try {
			
			AccountType accType =(AccountType) pm.getObjectById(AccountType.class,accTypeId);
			accType.setAccountType(type);
			System.out.println("[INFO] Updated account type");
		} finally {
		
		}
		
	}
	
	public static void deleteAllAccountTypes() {
		tx = pm.currentTransaction();
		try {
			tx.begin();

			System.out
					.println("[INFO] Deleting all account types");
			Query q = pm.newQuery(AccountType.class);
			long numberInstancesDeleted = q.deletePersistentAll();
			System.out.println("[INFO] Deleted " + numberInstancesDeleted
					+ " account types");

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}
	
	public static void deleteAccountType(int id) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			
			AccountType a = (AccountType) pm.getObjectById(AccountType.class, id);
			System.out.println("[INFO] Delete account type " + a.getAccountType());
			pm.deletePersistent(a);
			tx.commit();
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			
		}
	}
	
	public static List<AccountType> getAllAccountTypes(){
		try {
			System.out.println("[INFO] Retrieve  all account types");
			Query q = pm.newQuery(AccountType.class);
			@SuppressWarnings("unchecked")
			List<AccountType> accTypes = (List<AccountType>)q.execute();
			
			return accTypes;
		 
		} finally {
			
		}		
	}
	
	public static void closeConnection(){
		pm.close();
	}
}
