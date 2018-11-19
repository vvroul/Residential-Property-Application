package cluster_structures;

public class Pair {
	
	private int Item;
	private int Rating;

	
	public Pair() {
		Item = 0;
		Rating = 0;
	}
	
	public Pair(int item, int rating) {
		Item = item;
		Rating = rating;
	}

	public int getItem() {
		return Item;
	}

	public void setItem(int item) {
		Item = item;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}
}
