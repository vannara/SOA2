package qdb;

import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.db4o.query.QueryComparator;

import configuration.DbContext;
import dbo.City;
import dbo.TrainStation;

public class StationPersistence {
	
	private static DbContext context =DbContext.getInstance();
	
	public static void createStation(String cityName, String stationName){
		TrainStation oldStation=getStationByName(stationName);
		if(oldStation==null) {
			TrainStation station = new TrainStation();
			City city=CityPersistence.getCityByName(cityName);
		
			station.setCity(city);
			station.setStationName(stationName);
			
			context.db.store(station);
			System.out.println("Stored station: " + station);	
		}
		else {
			System.out.println("station is already exist");
		}
		
	}
	
	public static void deleteStation(String stationName) {
		TrainStation result = getStationByName(stationName);
		if (result!=null) {
			context.db.delete(result);
			System.out.println("Delete " + result);
		}		
	}
	
	public static void updateStationName(String oldName, String newName) {
		TrainStation result = getStationByName(oldName);
		if (result!=null) {
			result.setStationName(newName);
			context.db.store(result);
			System.out.println("Update station: " + oldName + " to "+ newName);
		}	
	}
	
	public static List<TrainStation> getAllTrainStations() {
		Query query = context.db.query().sortBy(new QueryComparator<TrainStation>() {

			@Override
			public int compare(TrainStation t1, TrainStation t2) {
				return t1.getCity().getCityName().compareTo(t2.getCity().getCityName());
			}
			
		});
		query.constrain(TrainStation.class);
		ObjectSet result = query.execute();
		
		return result;
	}

	public static List<TrainStation> getAllStationsExcept(final String stationName) {

		List<TrainStation> result = context.db.query(new Predicate<TrainStation>() {
			@Override
			public boolean match(TrainStation station) {
				return !station.getStationName().equals(stationName);
			}
		});

		return result;
	}
	
	public static String getCityByStationName(final String stationName) {
		List<TrainStation> result =context.db.query(new Predicate<TrainStation>() {
			@Override
			public boolean match(TrainStation station) {
				return station.getStationName().equals(stationName);
			}
		});

		return result.get(0).getCity().getCityName();
	}
	
	public static TrainStation getStationByName(String name) {
		ObjectSet result = context.db.queryByExample(new TrainStation(name));
		TrainStation found=null;
		if (result.hasNext()) {
			found=(TrainStation) result.next();
			
		}	
		return found;
	}
	
	public static List<TrainStation> getStationsInACity(final String cityName){
		List<TrainStation> result= context.db.query(new Predicate<TrainStation>() {

			@Override
			public boolean match(TrainStation station) {
				return station.getCity().getCityName().equals(cityName);
			}
			
		});
		return result;
	}
	
}

