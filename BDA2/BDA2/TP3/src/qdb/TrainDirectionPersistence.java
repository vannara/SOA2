package qdb;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.db4o.query.QueryComparator;

import configuration.DbContext;
import dbo.City;
import dbo.TrainDirections;
import dbo.TrainStation;

public class TrainDirectionPersistence {
	private static DbContext context = DbContext.getInstance();

	/***
	 * 
	 * @param fromStationName
	 * @param toStationName
	 * @param distance
	 * @param price
	 */
	public static void createTrainDirection(String fromStationName,
			String toStationName, float distance, float price) {
		TrainDirections oldDirection = getDirectionByStationsName(
				fromStationName, toStationName);
		if (oldDirection == null) {
			TrainStation fromStation = StationPersistence
					.getStationByName(fromStationName);
			TrainStation toStation = StationPersistence
					.getStationByName(toStationName);
			TrainDirections direction = new TrainDirections(fromStation,
					toStation);
			direction.setDistance(distance);
			direction.setPrice(price);
			context.db.store(direction);
			System.out.println("Stored Direction " + direction);
		} else {
			System.out.println("Direction already exist");
		}

	}

	/***
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static TrainDirections getDirectionByStationsName(final String from,
			final String to) {
		List<TrainDirections> direction = context.db
				.query(new Predicate<TrainDirections>() {

					@Override
					public boolean match(TrainDirections direction) {
						return direction.getFromStation().getStationName()
								.equals(from)
								&& direction.getToStation().getStationName()
										.equals(to);
					}

				});
		if (direction.size() > 0)
			return direction.get(0);
		return null;
	}

	/***
	 * 
	 * @return
	 */
	public static List<TrainDirections> getAllDirections() {
		Query query = context.db.query().sortBy(
				new QueryComparator<TrainDirections>() {

					@Override
					public int compare(TrainDirections t1, TrainDirections t2) {
						return t1
								.getFromStation()
								.getStationName()
								.compareTo(t2.getFromStation().getStationName());
					}

				});
		query.constrain(TrainDirections.class);
		ObjectSet result = query.execute();

		return result;
	}

	/***
	 * 
	 * @param cityName
	 * @return
	 */
	public static List<TrainDirections> getDirectionFromCity(
			final String cityName) {
		List<TrainDirections> directions = context.db
				.query(new Predicate<TrainDirections>() {
					@Override
					public boolean match(TrainDirections direction) {
						return direction.getFromStation().getCity()
								.getCityName().equals(cityName);
					}

				});

		return directions;
	}

	/***
	 * 
	 * @param fromStationName
	 * @param toStationName
	 * @param newDistance
	 */
	public static void updateTrainDirectionDistance(String fromStationName,
			String toStationName, float newDistance) {
		TrainDirections oldDirection = getDirectionByStationsName(
				fromStationName, toStationName);
		if (oldDirection != null) {
			if (oldDirection.getDistance() != newDistance) {
				oldDirection.setDistance(newDistance);
				context.db.store(oldDirection);
				System.out.println("Updated Distance to " + newDistance);
			}
		}
	}

	/***
	 * 
	 * @param fromStationName
	 * @param toStationName
	 * @param newPrice
	 */
	public static void updateTrainDirectionPrice(String fromStationName,
			String toStationName, float newPrice) {
		TrainDirections oldDirection = getDirectionByStationsName(
				fromStationName, toStationName);
		if (oldDirection != null) {
			if (oldDirection.getPrice() != newPrice) {
				oldDirection.setPrice(newPrice);
				context.db.store(oldDirection);
				System.out.println("Updated Price to " + newPrice);
			}
		}
	}

	/***
	 * get directions from a city with less than or equal to x distance
	 * 
	 * @param cityName
	 * @param distance
	 * @return
	 */
	public static List<TrainDirections> getDestinationsFromCityWithLessDistance(
			final String cityName, final float distance) {
		List<TrainDirections> directions = context.db
				.query(new Predicate<TrainDirections>() {
					@Override
					public boolean match(TrainDirections direction) {
						return direction.getFromStation().getCity()
								.getCityName().equals(cityName)
								&& direction.getDistance() <= distance;
					}

				});

		return directions;
	}
	
	/***
	 * get the departure station that have been setup in TrainDiections
	 * @return
	 */
	public static List<TrainStation> getDepartureStations(){
		Query query = context.db.query();
		query.constrain(TrainDirections.class);
		List<TrainDirections> result = query.execute();
		List<TrainStation> stations = new ArrayList<TrainStation>();
		for(TrainDirections direction:result) {
			stations.add(direction.getFromStation());
		}
		return stations;
	}
	
	/***
	 * get the arrival station that have been setup in TrainDiections
	 * @return
	 */
	public static List<TrainStation> getArrivalStations(){
		Query query = context.db.query();
		query.constrain(TrainDirections.class);
		List<TrainDirections> result = query.execute();
		List<TrainStation> stations = new ArrayList<TrainStation>();
		for(TrainDirections direction:result) {
			stations.add(direction.getToStation());
		}
		return stations;
	}
	
	/***
	 * 
	 * @param fromStationName
	 * @return
	 */
	public static List<TrainStation> getArrivalStationFromAStation(final String fromStationName){
		List<TrainDirections> result = context.db.query(new Predicate<TrainDirections>() {

			@Override
			public boolean match(TrainDirections direction) {
				return direction.getFromStation().getStationName().equals(fromStationName);
			}
			
		});
		List<TrainStation> stations = new ArrayList<TrainStation>();
		for(TrainDirections direction:result) {
			stations.add(direction.getToStation());
		}
		return stations;
	}
}
