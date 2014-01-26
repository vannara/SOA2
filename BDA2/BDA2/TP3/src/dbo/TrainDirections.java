package dbo;

public class TrainDirections {
	private TrainStation fromStation;
	private TrainStation toStation;
	private float distance;
	private float price;
	
	public TrainDirections(TrainStation from,TrainStation to) {
		this.fromStation=from;
		this.toStation=to;
		this.distance=0;
		this.price=0;
	}
	
	public TrainStation getFromStation() {
		return fromStation;
	}
	public void setFromStation(TrainStation fromStation) {
		this.fromStation = fromStation;
	}
	public TrainStation getToStation() {
		return toStation;
	}
	public void setToStation(TrainStation toStation) {
		this.toStation = toStation;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String toString() {
		return "from: "+ fromStation.getStationName() + " to: "+ toStation + " distance: "+ distance + " price: " + price; 
	}
	
}
