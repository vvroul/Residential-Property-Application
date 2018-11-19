package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Photo;

public class PhotoDAOImpl implements PhotoDAO {
	
	
	private static final String SQL_INSERT_PHOTO = "INSERT INTO Photos VALUES (?,?)";
	
	private static final String SQL_DELETE_PHOTO = "DELETE FROM Photos WHERE Listing_id = ? AND Photo = ?";
	
	private static final String SQL_FIND_PHOTOS_OF_A_LISTING = "SELECT * FROM Photos WHERE Listing_id = ?";
	
	
	
	private ConnectionFactory factory;
	
	
	public PhotoDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public void insert_photo(Photo photo) {
		
		Object[] values = { photo.getListing_id(), photo.getPhoto() };
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_PHOTO, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_photo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete_photo(Photo photo) {
		
		Object[] values = { photo.getListing_id(), photo.getPhoto() };
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_DELETE_PHOTO, false, values);
			pstmt.executeUpdate();	
		} 
		catch (SQLException e) {
			System.err.println(photo.getPhoto());
			System.err.println(e.getMessage()+"   delete_photo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ArrayList<Photo> find_photos_of_a_listing(int listing_id) {
		
		ArrayList<Photo> photos = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_PHOTOS_OF_A_LISTING, false, listing_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	photos.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "   find_photos_of_a_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return photos;
		
	}
	
	
	private static Photo map(ResultSet resultSet) throws SQLException {
		
		Photo photo = new Photo();
		
		photo.setListing_id(resultSet.getInt("Listing_id"));
		photo.setPhoto(resultSet.getString("Photo"));
		
		return photo;
	        
	}
	
}