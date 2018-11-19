package entities;

public class Recommendation {
	
	private int User_id;
	private int Listing_id;
	private int Position;
	
	public Recommendation(int user_id, int listing_id , int position) {
		User_id = user_id;
		Listing_id = listing_id;
		Position = position;
	}
	
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public int getListing_id() {
		return Listing_id;
	}
	public void setListing_id(int listing_id) {
		Listing_id = listing_id;
	}
	public int getPosition() {
		return Position;
	}
	public void setPosition(int position) {
		Position = position;
	}

}
