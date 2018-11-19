package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entities.Listing;
import other.Date_util;
import entities.AmenitiesRules;


public class ListingDAOImpl implements ListingDAO {
	
	private static final String SQL_INSERT_LISTING = "INSERT INTO Listings VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_INSERT_LISTING_SECONDARY = "INSERT INTO ListingsSecondary VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_SEARCH_LISTING = "SELECT * FROM Listings , ListingsSecondary "
			+ " WHERE ( Location LIKE ? ) "
			+ " AND ( ? <= Accommodates ) AND (Room_type = ? OR ? = '')  AND ( ? <= Price OR ? = 0 ) "
			+ " AND Listings.Listing_id in "
			+ 					" ( SELECT Listing_id FROM ListingsCalendar WHERE Listing_date >= ? AND Listing_date <= ? AND Available = 1 "
			+ 					" GROUP BY Listing_id HAVING COUNT(*) = ? )"
			+ " AND Listings.Listing_id in "
			+ 					" ( SELECT Listing_id FROM AmenitiesRules WHERE (Wifi = ? OR ? = '') AND (Cooling = ? OR ? = '') "
			+ 					" AND (Heating = ? OR ? = '') AND (Kitchen = ? OR ? = '') AND (Tv = ? OR ? = '') "
			+ 					" AND (Parking_lot = ? OR ? = '') AND (Elevator = ? OR ? = '') )"
			+ " AND Listings.Listing_id = ListingsSecondary.Listing_id";
	
	
	private static final String SQL_CHECK_LISTING_AVAILABLE = "SELECT * FROM Listings , ListingsSecondary "
			+ " WHERE ( ? <= Accommodates ) "
			+ " AND Listings.Listing_id in "
			+ 					" ( SELECT Listing_id FROM ListingsCalendar WHERE Listing_date >= ? AND Listing_date <= ? AND Available = 1 "
			+ 					" GROUP BY Listing_id HAVING COUNT(*) = ? )"
			+ " AND Listings.Listing_id = ListingsSecondary.Listing_id "
			+ " AND Listings.Listing_id = ?";
	
	
	private static final String SQL_FIND_LISTINGS = "SELECT * FROM Listings , ListingsSecondary "
			+ " WHERE Listings.Listing_id = ListingsSecondary.Listing_id";
	
	private static final String SQL_FIND_LISTINGS_BY_HOST = "SELECT * FROM Listings , ListingsSecondary "
			+ " WHERE ListingsSecondary.Host_id = ? AND Listings.Listing_id = ListingsSecondary.Listing_id";
	
	private static final String SQL_FIND_LISTING_BY_ID = "SELECT * FROM Listings , ListingsSecondary "
			+ " WHERE Listings.Listing_id = ListingsSecondary.Listing_id AND Listings.Listing_id = ?";
	
	private static final String SQL_UPDATE_LISTING = "UPDATE Listings SET Name = ? , Location = ? , Latitude = ? , Longitude = ? , "
			+ " Accommodates = ? , Photo_medium = ? , Photo_large = ? ,  Price = ? , Cleaning_fee = ? , "
			+ " Property_type = ? , Room_type = ? , Beds = ? WHERE Listing_id = ?";
	
	private static final String SQL_UPDATE_LISTING_SECONDARY = "UPDATE ListingsSecondary SET Bathrooms = ? , Bedrooms = ? , Bed_type = ? , "
			+ " Square_feet = ? , Description = ? , Minimum_nights = ? , Maximum_nights = ? , Transit = ? , Guest_included = ? , "
			+ " Extra_people = ? WHERE Listing_id = ?";
	
	private static final String SQL_UPDATE_LISTING_PHOTO_MEDIUM = "UPDATE Listings SET Photo_medium = ? WHERE Listing_id = ?";
	
	private static final String SQL_UPDATE_LISTING_PHOTO_LARGE = "UPDATE Listings SET Photo_large = ? WHERE Listing_id = ?";
	
	private static final String SQL_MAX_LISTING_ID = "SELECT MAX(Listing_id) FROM Listings";
	
	private static final String SQL_UPDATE_LISTING_NUMBER_OF_REVIEWS = "UPDATE Listings SET Number_of_reviews = ? , "
			+ " Review_scores_rating = ? WHERE Listing_id = ?";
	
	private static final String SQL_LISTING_SUM_SCORE_RATING = "SELECT SUM(Score) FROM Reviews WHERE Listing_id = ?";
	
	private static final String SQL_CHECK_LISTING_CALENDAR = "SELECT Listing_id FROM ListingsCalendar "
			+ "WHERE Listing_id = ? AND Listing_date = ?";
	
	private static final String SQL_NEW_LISTING_CALENDAR = "INSERT INTO ListingsCalendar VALUES (?,?,?,?,?)";
	
	private static final String SQL_BOOK_LISTING_CALENDAR = "UPDATE ListingsCalendar SET Available = ? , User_id = ? "
			+ " WHERE Listing_id = ? AND Listing_date = ?";
	
	
	private static final String SQL_FIND_RECOMMENDATIONS_FOR_USER = "SELECT * FROM Listings , ListingsSecondary "
			+ " WHERE Listings.Listing_id = ListingsSecondary.Listing_id "
			+ " AND Listings.Listing_id in ( SELECT Listing_id FROM Recommend WHERE User_id = ?)";
	
	
	private ConnectionFactory factory;
	
	
	public ListingDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public void insert_listing(Listing listing) {
		
		Object[] values = { listing.getListing_id(), listing.getName(), listing.getLocation(), listing.getLatitude(),
				listing.getLongitude(), listing.getAccommodates(), listing.getPhoto_medium(), listing.getPhoto_large(), 
				listing.getPrice(), listing.getCleaning_fee(), listing.getProperty_type(), listing.getRoom_type(), 
				listing.getBeds() , listing.getNumber_of_reviews(), listing.getReview_scores_rating()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_LISTING, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void insert_listing_secondary(Listing listing) {
		
		Object[] values = { listing.getListing_id(), listing.getHost_id(), listing.getBathrooms(), listing.getBedrooms(),
				listing.getBed_type(), listing.getSquare_feet(), listing.getDescription(), listing.getMinimum_nights(), 
				listing.getMaximum_nights(), listing.getTransit(), listing.getGuest_included(), listing.getExtra_people()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_LISTING_SECONDARY, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_listing_secondary");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ArrayList<Listing> search_listing(Listing listing , AmenitiesRules amenities , Date date1 , Date date2 , int dates_count) {
		
		ArrayList<Listing> listings = new ArrayList<>();
		
		Object[] values = { "%"+listing.getLocation()+"%", listing.getAccommodates(), listing.getRoom_type(), listing.getRoom_type(), 
				listing.getPrice(), listing.getPrice(), DAOUtil.toSqlDate(date1), DAOUtil.toSqlDate(date2), dates_count, amenities.getWifi(), 
				amenities.getWifi(), amenities.getCooling(), amenities.getCooling(), amenities.getHeating(), 
				amenities.getHeating(), amenities.getKitchen(), amenities.getKitchen(), amenities.getTv(), 
				amenities.getTv(), amenities.getParking_lot(), amenities.getParking_lot(), amenities.getElevator(), 
				amenities.getElevator()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_SEARCH_LISTING, false, values);
			ResultSet myRes = pstmt.executeQuery();
			while (myRes.next()) {
            	listings.add(map_all(myRes));
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
	public boolean new_booking_available(int accommodates , int listing_id , Date date1 , Date date2 , int dates_count) {
		
		boolean available = false;
		
		Object[] values = { accommodates , DAOUtil.toSqlDate(date1), DAOUtil.toSqlDate(date2), dates_count , listing_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_CHECK_LISTING_AVAILABLE, false, values);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {
				available = true;
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   new_booking_available");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return available;
		
	}
	
	@Override
	public ArrayList<Listing> listings_list() {
		
		ArrayList<Listing> listings = new ArrayList<>();
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_LISTINGS);
			ResultSet myRes = pstmt.executeQuery();
			while (myRes.next()) {
            	listings.add(map_all(myRes));
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   listings_list");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listings;
		
	}
	
	
	@Override
	public ArrayList<Listing> get_listings_by_host(int host_id) {
		
		ArrayList<Listing> listings = new ArrayList<>();
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_LISTINGS_BY_HOST, false, host_id);
			ResultSet myRes = pstmt.executeQuery();
			while (myRes.next()) {
            	listings.add(map_all(myRes));
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   get_listings_by_host");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listings;
		
	}
	
	
	@Override
	public Listing find_listing(int listing_id) {
		
		Listing listing = null;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_LISTING_BY_ID, false, listing_id);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {
            	listing = map_all(myRes);
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   find_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listing;
		
	}
	
	
	@Override
	public void update_listing(Listing listing) {
		
		Object[] values = { listing.getName(), listing.getLocation(), listing.getLatitude(),listing.getLongitude(), 
				listing.getAccommodates(), listing.getPhoto_medium(), listing.getPhoto_large(),	listing.getPrice(),
				listing.getCleaning_fee(), listing.getProperty_type(), listing.getRoom_type(), listing.getBeds(), 
				listing.getListing_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_LISTING, false, values);
			pstmt.executeUpdate();	
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_listing_photo_medium(Listing listing) {
		
		Object[] values = { listing.getPhoto_medium(), listing.getListing_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_LISTING_PHOTO_MEDIUM, false, values);
			pstmt.executeUpdate();	
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_listing_photo_medium");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_listing_photo_large(Listing listing) {
		
		Object[] values = { listing.getPhoto_large(), listing.getListing_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_LISTING_PHOTO_LARGE, false, values);
			pstmt.executeUpdate();	
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_listing_photo_large");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_listing_secondary(Listing listing) {
		
		Object[] values = { listing.getBathrooms(), listing.getBedrooms(), listing.getBed_type(), listing.getSquare_feet(), 
				listing.getDescription(), listing.getMinimum_nights(), listing.getMaximum_nights(), listing.getTransit(), 
				listing.getGuest_included(), listing.getExtra_people(), listing.getListing_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_LISTING_SECONDARY, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_listing_secondary");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int get_new_listing_id() {
		
		int next_listing_id = 0;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_MAX_LISTING_ID);
			ResultSet myRes = pstmt.executeQuery();
            if (myRes.next()) {
            	next_listing_id = Integer.parseInt(myRes.getString(1));
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage() + "    get_new_listing_id");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return next_listing_id + 1;
		
	}
	
	
	@Override
	public void update_listing_reviews(Listing listing) {
		
		Object[] values = { listing.getNumber_of_reviews(), listing.getReview_scores_rating(), listing.getListing_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_LISTING_NUMBER_OF_REVIEWS, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "    update_listing_reviews");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public int get_sum_score_of_reviews(int listing_id) {
		
		int sum = 0;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_LISTING_SUM_SCORE_RATING, false, listing_id);
			ResultSet myRes = pstmt.executeQuery();
            if (myRes.next()) {
            	sum = Integer.parseInt(myRes.getString(1));
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage() + "    get_sum_score_of_reviews");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return sum;
		
	}
	
	
	@Override
	public int check_dates_exist(Listing listing , Date date1) {
		
		int listing_id = -1;
		
		Object[] values = { listing.getListing_id(), DAOUtil.toSqlDate(date1)};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_CHECK_LISTING_CALENDAR, false, values);
			ResultSet myRes = pstmt.executeQuery();
            if (myRes.next()) {
            	listing_id = Integer.parseInt(myRes.getString(1));
            }	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "    check_dates_exist");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listing_id;
		
	}
	
	
	@Override
	public void new_listing_calendar(Listing listing , Date date , int days_count) {
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_NEW_LISTING_CALENDAR);
			
			int listing_id = listing.getListing_id();
			
			for (int day=0; day < days_count; day ++) {
				
				pstmt.setInt(1, listing_id);
				pstmt.setDate(2, DAOUtil.toSqlDate(Date_util.addDays(date,day)));
				pstmt.setBoolean(3, true);
				pstmt.setFloat(4, listing.getPrice());
				pstmt.setInt(5, 0);
				
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage() + "      new_listing_calendar");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void book_listing_calendar(Listing listing , int user_id , Date date , int days_count) {
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_BOOK_LISTING_CALENDAR);
			
			for (int day=0; day < days_count; day ++) {
				
				pstmt.setBoolean(1, false);
				pstmt.setInt(2, user_id);
				pstmt.setInt(3, listing.getListing_id());
				pstmt.setDate(4, DAOUtil.toSqlDate(Date_util.addDays(date,day)));
				
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   book_listing_calendar");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public ArrayList<Listing> recommendation_list(int user_id) {
		
		ArrayList<Listing> listings = new ArrayList<>();
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_RECOMMENDATIONS_FOR_USER, false, user_id);
			ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	listings.add(map_all(myRes));
            }	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "    recommendation_list");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listings;
		
	}
	

	
	private static Listing map_all(ResultSet resultSet) throws SQLException {
		
		Listing listing = new Listing();
		
		listing.setListing_id(resultSet.getInt("Listing_id"));
		
		listing.setName(resultSet.getString("Name"));
		listing.setLocation(resultSet.getString("Location"));
		listing.setLatitude(resultSet.getFloat("Latitude"));
		listing.setLongitude(resultSet.getFloat("Longitude"));
		listing.setAccommodates(resultSet.getInt("Accommodates"));
		listing.setPhoto_medium(resultSet.getString("Photo_medium"));
		listing.setPhoto_large(resultSet.getString("Photo_large"));
		listing.setPrice(resultSet.getInt("Price"));
		listing.setCleaning_fee(resultSet.getInt("Cleaning_fee"));
		listing.setProperty_type(resultSet.getString("Property_type"));
		listing.setRoom_type(resultSet.getString("Room_type"));
		listing.setBeds(resultSet.getInt("Beds"));
		listing.setNumber_of_reviews(resultSet.getInt("Number_of_reviews"));
		listing.setReview_scores_rating(resultSet.getInt("Review_scores_rating"));
		
		listing.setHost_id(resultSet.getInt("Host_id"));
		listing.setBathrooms(resultSet.getInt("Bathrooms"));
		listing.setBedrooms(resultSet.getInt("Bedrooms"));
		listing.setBed_type(resultSet.getString("Bed_type"));
		listing.setSquare_feet(resultSet.getInt("Square_feet"));
		listing.setDescription(resultSet.getString("Description"));
		listing.setMinimum_nights(resultSet.getInt("Minimum_nights"));
		listing.setMaximum_nights(resultSet.getInt("Maximum_nights"));
		listing.setTransit(resultSet.getString("Transit"));
		listing.setGuest_included(resultSet.getInt("Guest_included"));
		listing.setExtra_people(resultSet.getFloat("Extra_people"));
		
        return listing;
        
    }

	
}