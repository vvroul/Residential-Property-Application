package dao;

import java.util.ArrayList;
import entities.Listing;


public interface ListingDAO {
	
	public ArrayList<Listing> listings_list();
	
	public void update_listings(ArrayList<Listing> listings);
	
}