package dao;

import java.util.ArrayList;

import entities.Review;

public interface ReviewDAO {
	
	public void insert_review(Review review);

	public void update_review(Review review);
	
	public void delete_review(int review_id);
	
	public ArrayList<Review> reviews_list();
	
	public ArrayList<Review> find_reviews_for_a_host(int host_id);
	
	public ArrayList<Review> find_reviews_of_a_listing(int listing_id);
	
	public int get_new_review_id();
	
}