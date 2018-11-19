package dao;

import entities.AmenitiesRules;


public interface AmenitiesRulesDAO {
	
	public void insert_amenities_rules(AmenitiesRules amenities);
	
	public AmenitiesRules find_amenities_rules(int listing_id);
	
	public void update_amenities_rules(AmenitiesRules amenities);
	
}