package entities;

import java.util.Date;

public class Review {
	
	private int Review_id;
	private int Listing_id;
	private Date Review_date;
	private int Reviewer_id;
	private String Reviewer_name;
	private int Score;
	private String Message;
	
	public int getReview_id() {
		return Review_id;
	}
	public void setReview_id(int review_id) {
		Review_id = review_id;
	}
	public int getListing_id() {
		return Listing_id;
	}
	public void setListing_id(int listing_id) {
		Listing_id = listing_id;
	}
	public Date getReview_date() {
		return Review_date;
	}
	public void setReview_date(Date review_date) {
		Review_date = review_date;
	}
	public int getReviewer_id() {
		return Reviewer_id;
	}
	public void setReviewer_id(int reviewer_id) {
		Reviewer_id = reviewer_id;
	}
	public String getReviewer_name() {
		return Reviewer_name;
	}
	public void setReviewer_name(String reviewer_name) {
		Reviewer_name = reviewer_name;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
	
}

