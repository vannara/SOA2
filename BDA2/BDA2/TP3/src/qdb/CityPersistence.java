package qdb;

import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

import configuration.DbContext;
import dbo.City;

public class CityPersistence {

	private static DbContext context = DbContext.getInstance();

	public static void createCity(String cityName) {
		City oldcity = getCityByName(cityName);
		if (oldcity == null) {
			City city = new City(cityName);
			context.db.store(city);
			System.out.println("Stored City " + city);
		} else {
			System.out.println("City " + oldcity + " already exist");
		}
	}

	public static void updateCity(String cityName, String newName) {

		City city = getCityByName(cityName);
		city.setCityName(newName);
		context.db.store(city);
		System.out.println("Updated City " + city);
	}

	public static void deleteCity(String cityName) {
		City city = getCityByName(cityName);
		context.db.delete(city);
		System.out.println("Delete City " + city);
	}

	public static List<City> getAllCities() {

		Query query = context.db.query();
		query.constrain(City.class);
		ObjectSet result = query.execute();

		return result;
	}

	
	public static List<City> getAllCitiesExcept(final String cityName) {
		
		List<City> result = context.db.query(new Predicate<City>() {
			@Override
			public boolean match(City city) {
				return !city.getCityName().equals(cityName);
			}

		});

		return result;
	}

	public static City getCityByName(String cityName) {
		ObjectSet result = context.db.queryByExample(new City(cityName));
		if (result.hasNext()) {
			City found = (City) result.next();
			return found;
		}

		return null;
	}
	


}
