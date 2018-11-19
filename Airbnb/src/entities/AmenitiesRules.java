package entities;

public class AmenitiesRules {
	
	private int Listing_id;
	private String Wifi;
	private String Cooling;
	private String Heating;
	private String Kitchen;
	private String Tv;
	private String Parking_lot;
	private String Elevator;
	private String Smoking;
	private String Pets;
	private String Events;
	
	
	public AmenitiesRules() {
		Listing_id = 0;
		Wifi = "";
		Cooling = "";
		Heating = "";
		Kitchen = "";
		Tv = "";
		Parking_lot = "";
		Elevator = "";
		setSmoking("");
		setPets("");
		setEvents("");
	}
	
	public int getListing_id() {
		return Listing_id;
	}
	public void setListing_id(int listing_id) {
		Listing_id = listing_id;
	}
	public String getWifi() {
		return Wifi;
	}
	public void setWifi(String wifi) {
		Wifi = wifi;
	}
	public String getCooling() {
		return Cooling;
	}
	public void setCooling(String cooling) {
		Cooling = cooling;
	}
	public String getHeating() {
		return Heating;
	}
	public void setHeating(String heating) {
		Heating = heating;
	}
	public String getKitchen() {
		return Kitchen;
	}
	public void setKitchen(String kitchen) {
		Kitchen = kitchen;
	}
	public String getTv() {
		return Tv;
	}
	public void setTv(String tv) {
		Tv = tv;
	}
	public String getParking_lot() {
		return Parking_lot;
	}
	public void setParking_lot(String parking_lot) {
		Parking_lot = parking_lot;
	}
	public String getElevator() {
		return Elevator;
	}
	public void setElevator(String elevator) {
		Elevator = elevator;
	}
	public String getSmoking() {
		return Smoking;
	}
	public void setSmoking(String smoking) {
		Smoking = smoking;
	}
	public String getPets() {
		return Pets;
	}
	public void setPets(String pets) {
		Pets = pets;
	}
	public String getEvents() {
		return Events;
	}
	public void setEvents(String events) {
		Events = events;
	}
	
}