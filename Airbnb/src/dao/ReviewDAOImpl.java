package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Review;

public class ReviewDAOImpl implements ReviewDAO {
	
	private static final String SQL_INSERT_REVIEW = "INSERT INTO Reviews VALUES (?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_REVIEW = "UPDATE Reviews SET Score = ? , Message = ? WHERE Review_id = ?";
	
	private static final String SQL_DELETE_REVIEW = "DELETE FROM Reviews WHERE Review_id = ?";
	
	private static final String SQL_FIND_REVIEWS = "SELECT * FROM Reviews";
	
	private static final String SQL_FIND_REVIEWS_FOR_A_HOST = "SELECT * FROM Reviews WHERE Listing_id in "
			+ "(SELECT Listing_id FROM ListingsSecondary WHERE Host_id = ?)";
	
	private static final String SQL_FIND_REVIEWS_OF_A_LISTING = "SELECT * FROM Reviews WHERE Listing_id = ?";
	
	private static final String SQL_MAX_REVIEW_ID = "SELECT MAX(Review_id) FROM Reviews";
	
	
	
	private ConnectionFactory factory;
	
	
	public ReviewDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public void insert_review(Review review) {
		
		Object[] values = { review.getReview_id(), review.getListing_id(), review.getReview_date(), review.getReviewer_id(),
				review.getReviewer_name(), review.getScore(), review.getMessage()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_REVIEW, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_review");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update_review(Review review) {
		
		Object[] values = { review.getScore(), review.getMessage(), review.getReview_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_REVIEW, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_review");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete_review(int review_id) {
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_DELETE_REVIEW, false, review_id);
			pstmt.executeUpdate();	
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   delete_review");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public ArrayList<Review> reviews_list() {
		
		ArrayList<Review> reviews = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_REVIEWS);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	reviews.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage()+"   reviews_list");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return reviews;
		
	}
	
	
	
	@Override
	public ArrayList<Review> find_reviews_for_a_host(int host_id) {
		
		ArrayList<Review> reviews = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_REVIEWS_FOR_A_HOST, false, host_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	reviews.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage()+"   find_reviews_for_a_host");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return reviews;
		
	}
	
	
	@Override
	public ArrayList<Review> find_reviews_of_a_listing(int listing_id) {
		
		ArrayList<Review> reviews = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_REVIEWS_OF_A_LISTING, false, listing_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	reviews.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage()+"   find_reviews_of_a_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return reviews;
		
	}
	
	
	@Override
	public int get_new_review_id() {
		
		int new_review_id = 0;

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_MAX_REVIEW_ID);
            ResultSet myRes = pstmt.executeQuery();
            if (myRes.next()) {
            	new_review_id = Integer.parseInt(myRes.getString(1));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return new_review_id + 1;
	}
	
	
	
	
	private static Review map(ResultSet resultSet) throws SQLException {
		
		Review review = new Review();
		
		review.setReview_id(resultSet.getInt("Review_id"));
		review.setListing_id(resultSet.getInt("Listing_id"));
		review.setReview_date(resultSet.getDate("Review_date"));
		review.setReviewer_id(resultSet.getInt("Reviewer_id"));
		review.setReviewer_name(resultSet.getString("Reviewer_name"));
		review.setScore(resultSet.getInt("Score"));
		review.setMessage(resultSet.getString("Message"));
		
		return review;
	        
	}
	
}