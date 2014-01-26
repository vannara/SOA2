package dbo;

public class TrainStation {
	private String stationName;
	private City city;
	
	public TrainStation() {
		
	}
	
	public TrainStation(String stationName) {
		this.stationName=stationName;
	}
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	public String toString() {
		return "station " + stationName + " in " + city.getCityName();
	}
}
