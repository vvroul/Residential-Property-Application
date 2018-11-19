package entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "Booking")
@XmlAccessorType (XmlAccessType.FIELD)
public class Booking {

	private int Listing_id;
	private int User_id;
	private int Host_id;
	private Date Starting_date;
	private Date Final_date;
	private int Guests;
	
	public int getListing_id() {
		return Listing_id;
	}
	
	public void setListing_id(int listing_id) {
		Listing_id = listing_id;
	}
	
	public int getUser_id() {
		return User_id;
	}
	
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	
	public int getHost_id() {
		return Host_id;
	}
	
	public void setHost_id(int host_id) {
		Host_id = host_id;
	}
	
	public Date getStarting_date() {
		return Starting_date;
	}
	
	public void setStarting_date(Date starting_date) {
		Starting_date = starting_date;
	}
	
	public Date getFinal_date() {
		return Final_date;
	}
	
	public void setFinal_date(Date final_date) {
		Final_date = final_date;
	}

	public int getGuests() {
		return Guests;
	}

	public void setGuests(int guests) {
		Guests = guests;
	}
	
	
}
