package jdotd2.helper;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/***
 * 
 * @author vannaraloch
 * @Description:
 * This is a singleton class that create an instance of PersistenceManagerFactory
 * and use it in the whole application
 */
public class MyAppPersistenceManagerFactory {
	private static MyAppPersistenceManagerFactory instance = new MyAppPersistenceManagerFactory();
	   
	private  PersistenceManagerFactory pmf;
	
	private MyAppPersistenceManagerFactory(){
		
	}
	
	public static MyAppPersistenceManagerFactory getInstance() {		
		if(instance==null){
			instance = new MyAppPersistenceManagerFactory();
		}
		return instance;
	}
	
	public  PersistenceManagerFactory getPersistenceManagerFactory(){
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		return pmf;
	}
	
	
}
