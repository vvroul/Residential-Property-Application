package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Booking;

public class BookingDAOImpl implements BookingDAO {
	
	private static final String SQL_INSERT_BOOKING = "INSERT INTO Bookings VALUES (?,?,?,?,?,?)";
	
	private static final String SQL_FIND_BOOKINGS = "SELECT * FROM Bookings";
	
	private static final String SQL_FIND_BOOKINGS_OF_A_USER = "SELECT * FROM Bookings WHERE User_id = ?";
	
	private static final String SQL_FIND_BOOKINGS_FOR_A_HOST = "SELECT * FROM Bookings WHERE Host_id = ?";
	
	private static final String SQL_FIND_BOOKINGS_FOR_A_LISTING = "SELECT * FROM Bookings WHERE Listing_id = ?";

	
	private ConnectionFactory factory;
	
	
	public BookingDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	@Override
	public void insert_booking(Booking booking) {
		
		Object[] values = { booking.getListing_id(), booking.getUser_id(), booking.getHost_id(), 
				DAOUtil.toSqlDate(booking.getStarting_date()), DAOUtil.toSqlDate(booking.getFinal_date()),
				booking.getGuests()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_BOOKING, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_booking");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ArrayList<Booking> bookings_list() {
		
		ArrayList<Booking> bookings = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BOOKINGS);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	bookings.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "   bookings_list");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return bookings;
		
	}
	
	@Override
	public ArrayList<Booking> find_bookings_of_a_user(int user_id) {
		
		ArrayList<Booking> bookings = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_BOOKINGS_OF_A_USER, false, user_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	bookings.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "   find_bookings_of_a_user");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return bookings;
	}
	
	
	@Override
	public ArrayList<Booking> find_bookings_for_a_host(int host_id) {
		
		ArrayList<Booking> bookings = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_BOOKINGS_FOR_A_HOST, false, host_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	bookings.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "   find_bookings_for_a_host");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return bookings;
		
	}
	
	
	@Override
	public ArrayList<Booking> find_bookings_for_a_listing(int listing_id) {
		
		ArrayList<Booking> bookings = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_BOOKINGS_FOR_A_LISTING, false, listing_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	bookings.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "   find_bookings_for_a_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return bookings;
		
	}
	
	
	private static Booking map(ResultSet resultSet) throws SQLException {
		
		Booking booking = new Booking();
		
		booking.setListing_id(resultSet.getInt("Listing_id"));
		booking.setUser_id(resultSet.getInt("User_id"));
		booking.setHost_id(resultSet.getInt("Host_id"));
		booking.setStarting_date(resultSet.getDate("Starting_date"));
		booking.setFinal_date(resultSet.getDate("Final_date"));
		booking.setGuests(resultSet.getInt("Guests"));
	
		
        return booking;
        
    }
	
}
