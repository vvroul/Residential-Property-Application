package dao;

import entities.Recommendation;

public interface RecommendDAO {

	public void insert_listings(Recommendation[] recommendations);
	
	public void delete_all();
	
}
