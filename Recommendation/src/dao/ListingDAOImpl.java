package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Listing;


public class ListingDAOImpl implements ListingDAO {
	
	private static final String SQL_FIND_ALL = "SELECT Listings.Listing_id , Name , Host_id"
			+ " FROM Listings , ListingsSecondary"
			+ " WHERE Listings.Listing_id = ListingsSecondary.Listing_id";
	
	private static final String SQL_UPDATE_LISTING = "UPDATE Listings SET Number_of_reviews = ? , Review_scores_rating = ?"
			+ "  WHERE Listing_id = ?";
	
	private ConnectionFactory factory;
	
	
	public ListingDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	@Override
	public ArrayList<Listing> listings_list() {
		
		ArrayList<Listing> listings = new ArrayList<>();
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL);
			ResultSet myRes = pstmt.executeQuery();
			while (myRes.next()) {
            	listings.add(map(myRes));
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   search_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listings;
		
	}
	
	
	
	@Override
	public void update_listings(ArrayList<Listing> listings) {
		
		try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_LISTING);
			
			int count = 0;
			
			for (Listing listing : listings) {
				
				pstmt.setInt(1, listing.getNumber_of_reviews());
				pstmt.setInt(2, listing.getReview_scores_rating());
				pstmt.setInt(3, listing.getListing_id());
				
				pstmt.addBatch();
				
				count = count + 1;
				
				if (count == 1000) {
					pstmt.executeBatch();
					count = 0;
				}
			}
			
			pstmt.executeBatch();
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "     update_reviews_list");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static Listing map(ResultSet resultSet) throws SQLException {
		
		Listing listing = new Listing();
		
		listing.setListing_id(resultSet.getInt("Listing_id"));
		listing.setName(resultSet.getString("Name"));
		listing.setHost_id(resultSet.getInt("Host_id"));
		
        return listing;
        
    }
	
}