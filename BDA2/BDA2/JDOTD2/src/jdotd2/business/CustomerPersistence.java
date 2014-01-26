package jdotd2.business;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import jdotd2.dao.Client;
import jdotd2.dao.ClientType;
import jdotd2.helper.MyAppPersistenceManagerFactory;

public class CustomerPersistence {
	private static PersistenceManagerFactory pmf = MyAppPersistenceManagerFactory
			.getInstance().getPersistenceManagerFactory();
	private static PersistenceManager pm = pmf.getPersistenceManager();
	private static Transaction tx;

	public static void insertCustomer(String name, int clientTypeId, String address, String phone, String email) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			Client client = new Client();
			client.setClientName(name);
			ClientType clientType =pm.getObjectById(ClientType.class,clientTypeId);
			client.setClientType(clientType);
			client.setAddress(address);
			client.setPhone(phone);
			client.setEmail(email);
			
			pm.makePersistent(client);

			tx.commit();
			System.out.println("[INFO] Customer have been persisted");
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			
		}
	}

	public static void deleteCustomer(int id) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			
			Client cust = (Client) pm.getObjectById(Client.class, id);
			System.out.println("[INFO] Delete Customer " + cust.getClientName());
			pm.deletePersistent(cust);
			tx.commit();
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			
		}
	}
	
	public static List<Client> getAllCustomers(){
		try {
			System.out.println("[INFO] Retrieve  all customers");
			Query q = pm.newQuery(Client.class);
			@SuppressWarnings("unchecked")
			List<Client> clients = (List<Client>)q.execute();
			
			return clients;
		 
		} finally {
			
		}		
	}
	
	
}
