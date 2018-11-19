package entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "Bookings")
@XmlAccessorType (XmlAccessType.FIELD)
public class Bookings {
	
	@XmlElement(name = "Booking")
	private ArrayList<Booking> Booking_list;

	public ArrayList<Booking> getBooking_list() {
		return Booking_list;
	}

	public void setBooking_list(ArrayList<Booking> booking_list) {
		this.Booking_list = booking_list;
	}

}
