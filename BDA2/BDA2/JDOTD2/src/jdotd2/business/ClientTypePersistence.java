package jdotd2.business;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import jdotd2.dao.AccountType;
import jdotd2.dao.ClientType;
import jdotd2.helper.MyAppPersistenceManagerFactory;

public class ClientTypePersistence {
	private static PersistenceManagerFactory pmf = MyAppPersistenceManagerFactory
			.getInstance().getPersistenceManagerFactory();
	private static PersistenceManager pm = pmf.getPersistenceManager();
	private static Transaction tx;

	public static void insertClientType(String type){
		tx=pm.currentTransaction();
	     try
	        {
	            tx.begin();
	            System.out.println("[INFO] Persisting Client Type");
	            ClientType clientType = new ClientType(type);
	            pm.makePersistent(clientType);
	            tx.commit();
	            System.out.println("[INFO] Client Type have been persisted");
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	           
	        }
	}
	
	public static void updateClientType(int id,String type) {
		
		try {
			
			ClientType clientType =(ClientType) pm.getObjectById(ClientType.class,id);
			clientType.setType(type);
			System.out.println("[INFO] Updated client type");
		} finally {
		
		}
		
	}
	public static void deleteClientType(int id) {
		tx = pm.currentTransaction();
		try {
			tx.begin();
			
			ClientType client = (ClientType) pm.getObjectById(ClientType.class, id);
			System.out.println("[INFO] Delete client type " + client.getType());
			pm.deletePersistent(client);
			tx.commit();
			
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			
		}
	}
	
	public static void deleteAllClientTypes(){
	  tx=pm.currentTransaction();
      try
      {
          tx.begin();

          System.out.println("[INFO] Deleting all client types from persistence");
          Query q = pm.newQuery(ClientType.class);
          long numberInstancesDeleted = q.deletePersistentAll();
          System.out.println("[INFO] Deleted " + numberInstancesDeleted + " client types");

          tx.commit();
      }
      finally
      {
          if (tx.isActive())
          {
              tx.rollback();
          }
        
     }
	}
	
	public static List<ClientType> getAllClientTypes(){
		try {
			System.out.println("[INFO] Retrieve  all client types");
			Query q = pm.newQuery(ClientType.class);
			@SuppressWarnings("unchecked")
			List<ClientType> clientTypes = (List<ClientType>)q.execute();
			
			return clientTypes;
		 
		} finally {
			
		}		
	}
	public static void closeConnection(){
		pm.close();
	}
}
