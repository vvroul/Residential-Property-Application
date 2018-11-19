package dao;

import java.util.ArrayList;
import java.util.Date;

import entities.Listing;
import entities.AmenitiesRules;


public interface ListingDAO {
	
	public void insert_listing(Listing listing);
	
	public void insert_listing_secondary(Listing listing);
	
	public ArrayList<Listing> search_listing(Listing listing , AmenitiesRules amenities , Date date1 , Date date2 , int dates_count);
	
	public boolean new_booking_available(int accommodates , int listing_id , Date date1 , Date date2 , int dates_count);
	
	public ArrayList<Listing> listings_list();
	
	public ArrayList<Listing> get_listings_by_host(int host_id);
	
	public Listing find_listing(int listing_id);
	
	public void update_listing(Listing listing);
	
	public void update_listing_photo_medium(Listing listing);
	
	public void update_listing_photo_large(Listing listing);
	
	public void update_listing_secondary(Listing listing);
	
	public int get_new_listing_id();
	
	public void update_listing_reviews(Listing listing);
	
	public int get_sum_score_of_reviews(int listing_id);
	
	public int check_dates_exist(Listing listing , Date date1);
	
	public void new_listing_calendar(Listing listing , Date date1 , int days_count);
	
	public void book_listing_calendar(Listing listing , int user_id , Date date1 , int days_count);
	
	public ArrayList<Listing> recommendation_list(int user_id);
	
}