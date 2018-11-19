package dao;

import java.util.ArrayList;

import entities.Photo;

public interface PhotoDAO {

	public void insert_photo(Photo photo);
	
	public void delete_photo(Photo photo);
	
	public ArrayList<Photo> find_photos_of_a_listing(int listing_id);
	
}
