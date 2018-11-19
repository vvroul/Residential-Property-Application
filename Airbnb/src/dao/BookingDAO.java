package dao;

import java.util.ArrayList;

import entities.Booking;

public interface BookingDAO {

	public void insert_booking(Booking booking);
	
	public ArrayList<Booking> bookings_list();
	
	public ArrayList<Booking> find_bookings_of_a_user(int user_id);
	
	public ArrayList<Booking> find_bookings_for_a_host(int host_id);
	
	public ArrayList<Booking> find_bookings_for_a_listing(int listing_id);
	
}
