package qdb;

import java.util.Date;
import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

import configuration.DbContext;
import dbo.City;
import dbo.CityDistance;
import dbo.TrainDirections;
import dbo.TrainTicket;

public class TicketPersistence {
private static DbContext context =DbContext.getInstance();
	
	public static void createATicket(String fromStationName, String toStationName,String ticketNumber, String trainNumber,
			Date departureDate, Date arrivalDate, long departureTime,long arrivalTime){
		TrainDirections direction=TrainDirectionPersistence.getDirectionByStationsName(fromStationName, toStationName);
		
		if(direction!=null) {
			TrainTicket ticket=new TrainTicket(ticketNumber);
			ticket.setTrainNumber(trainNumber);
			ticket.setDepartureStation(direction.getFromStation());
			ticket.setArrivalStation(direction.getToStation());
			ticket.setDepartureDate(departureDate);
			ticket.setArrivalDate(arrivalDate);
			ticket.setDepartureTime(departureTime);
			ticket.setArrivalTime(arrivalTime);
			
			long duration = arrivalTime- departureTime; 
			ticket.setDuration(duration);
			
			context.db.store(ticket);
			System.out.println("Stored " + ticket);	
		}
	}
	
	/***
	 * 
	 * @return
	 */
	public static List<TrainTicket> getAllTickets(){
		Query query = context.db.query();
		query.constrain(TrainTicket.class);
		ObjectSet result = query.execute();

		return result;
	}
}
