package dbo;

public class CityDistance {
	private City fromCity;
	private City toCity;
	private float distance;
	/**
	 * @return the distance
	 */
	public float getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}
	/**
	 * @return the toCity
	 */
	public City getToCity() {
		return toCity;
	}
	/**
	 * @param toCity the toCity to set
	 */
	public void setToCity(City toCity) {
		this.toCity = toCity;
	}
	/**
	 * @return the fromCity
	 */
	public City getFromCity() {
		return fromCity;
	}
	/**
	 * @param fromCity the fromCity to set
	 */
	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}
	
	public CityDistance(City from, City to, float distance) {
		this.fromCity=from;
		this.toCity=to;
		this.distance=distance;
	}
	
	public CityDistance(City from, City to) {
		this.fromCity=from;
		this.toCity=to;
		this.distance=0;
	}
	
	public CityDistance() {
		
	}
	
	public String toString() {
		return "from " + fromCity + " to " +  toCity +" distance " + distance + "km";
	}
}
