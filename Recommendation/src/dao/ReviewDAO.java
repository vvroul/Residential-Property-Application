package dao;

import java.util.ArrayList;

import entities.Review;

public interface ReviewDAO {
	
	public ArrayList<Review> reviews_list();
	
	public ArrayList<Review> find_reviews_of_a_user(int user_id);
	
	public void update_reviews_list(ArrayList<Review> reviews);
	
	public void update_review(Review review);
	
}