package qdb;

import configuration.DbContext;
import dbo.Passenger;


public class PassengerPersistence {
private static DbContext context =DbContext.getInstance();
	
	public PassengerPersistence(String firstName, String lastName, String tel){
		Passenger p =new Passenger();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setTel(tel);
		
		context.db.store(p);
		System.out.println("Stored " + p);
	}
}
