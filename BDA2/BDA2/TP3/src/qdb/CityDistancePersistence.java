package qdb;

import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

import configuration.DbContext;
import dbo.City;
import dbo.CityDistance;

public class CityDistancePersistence {
	private static DbContext context =DbContext.getInstance();
	
	public static void createCityDistance(String from, String to, float distance){
		CityDistance travel=new CityDistance();
		City fromCity=CityPersistence.getCityByName(from);
		City toCity=CityPersistence.getCityByName(to);
		
		travel.setFromCity(fromCity);
		travel.setToCity(toCity);
		travel.setDistance(distance);
		
		context.db.store(travel);
		System.out.println("Stored city distance: " + travel);
	}
	
	public static void updateCityDistance(String from, String to, float newDistance){
		CityDistance travel= getCityDistance(from,to);
		if(travel!=null) {
			travel.setDistance(newDistance);
			context.db.store(travel);
			System.out.println("Update city distance: " + travel);	
		}	
		else {
			System.out.println("not found");
		}
	}
	
	public static CityDistance getCityDistance(String from,String to) {
		City fromCity = CityPersistence.getCityByName(from);
		City toCity= CityPersistence.getCityByName(to);
		ObjectSet result = context.db.queryByExample(new CityDistance(fromCity,toCity));
		CityDistance found=null;
		if (result.hasNext()) {
			found=(CityDistance) result.next();
		}
		else {
			 result = context.db.queryByExample(new CityDistance(toCity,fromCity));
			 if (result.hasNext()) {
				 found=(CityDistance) result.next();
			 }
		}
		return found;
	}
	
	public static void deleteCityDistance(String from, String to) {
		CityDistance result = getCityDistance(from,to);
		if (result!=null) {
			context.db.delete(result);
			System.out.println("Delete " + result);
		}		
	}
	
	public static List<CityDistance> getAllCityDistance() {
		Query query = context.db.query();
		query.constrain(CityDistance.class);
		ObjectSet result = query.execute();

		return result;
	}	
	
}

