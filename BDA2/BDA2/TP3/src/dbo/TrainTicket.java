package dbo;

import java.util.Date;

public class TrainTicket {
	private String ticketNumber;
	private String trainNumber;
	private TrainStation departureStation;
	private TrainStation arrivalStation;
	private CityDistance trip;
	private Passenger passenger;
	
	private boolean isReserved;
	private String seatNumber;
	private float reservationPrice;
	
	private Date departureDate;
	private Date arrivalDate;
	private long departureTime;
	private long arrivalTime;
	private long duration;
	
	public TrainTicket(String number) {
		this.ticketNumber=number;
	}
	
	public Date getDepartureDateTime() {
		return departureDate;
	}
	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDate = departureDateTime;
	}
	public Date getArrivalDateTime() {
		return arrivalDate;
	}
	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDate = arrivalDateTime;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	private float price;
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public TrainStation getDepartureStation() {
		return departureStation;
	}
	public void setDepartureStation(TrainStation departureStation) {
		this.departureStation = departureStation;
	}
	public TrainStation getArrivalStation() {
		return arrivalStation;
	}
	public void setArrivalStation(TrainStation arrivalStation) {
		this.arrivalStation = arrivalStation;
	}
	public CityDistance getTrip() {
		return trip;
	}
	public void setTrip(CityDistance trip) {
		this.trip = trip;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	public boolean isReserved() {
		return isReserved;
	}
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public float getReservationPrice() {
		return reservationPrice;
	}
	public void setReservationPrice(float reservationPrice) {
		this.reservationPrice = reservationPrice;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public long getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(long departureTime) {
		this.departureTime = departureTime;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public String toString() {
		return "ticket: "+ ticketNumber + " train number: " + trainNumber;
	}
}
