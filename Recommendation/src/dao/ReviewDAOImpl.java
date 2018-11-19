package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Review;

public class ReviewDAOImpl implements ReviewDAO {
	
	private static final String SQL_FIND_ALL = "SELECT Review_id , Listing_id , Reviewer_id , Score FROM Reviews";
	
	private static final String SQL_FIND_REVIEWS_OF_A_USER = "SELECT Review_id , Listing_id , Reviewer_id , Score FROM Reviews "
			+ "WHERE Reviewer_id = ?";
	
	private static final String SQL_UPDATE_REVIEW = "UPDATE Reviews SET Score = ? WHERE Review_id = ?";
	
	
	
	
	private ConnectionFactory factory;
	
	
	public ReviewDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public ArrayList<Review> reviews_list() {
		
		ArrayList<Review> reviews = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	reviews.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return reviews;
		
	}
	
	
	@Override
	public ArrayList<Review> find_reviews_of_a_user(int user_id) {
		
		ArrayList<Review> reviews = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_REVIEWS_OF_A_USER, false, user_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	reviews.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return reviews;
		
	}
	
	
	@Override
	public void update_reviews_list(ArrayList<Review> reviews) {

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_REVIEW);
			
			int count = 0;
			
			for (Review review : reviews) {
				pstmt.setInt(1, review.getScore());
				pstmt.setInt(2, review.getReview_id());
				
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
	
	
	
	@Override
	public void update_review(Review review) {
		
		Object[] values = { review.getScore() , review.getReview_id() };

        try {
        	Connection con = factory.getConnection();
        	PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_REVIEW, false, values);
            pstmt.executeQuery();
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "     update_review");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
       
	}
	
	
	private static Review map(ResultSet resultSet) throws SQLException {
		
		Review review = new Review();
		
		review.setReview_id(resultSet.getInt("Review_id"));
		review.setListing_id(resultSet.getInt("Listing_id"));
		review.setReviewer_id(resultSet.getInt("Reviewer_id"));
		review.setScore(resultSet.getInt("Score"));
		
		return review;
	        
	}
	
	
}