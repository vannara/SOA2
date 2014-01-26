package dbo;

public class City {
	private String cityName;

	public City(String name) {
		this.cityName=name;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String toString() {
		return cityName;
	}
}
