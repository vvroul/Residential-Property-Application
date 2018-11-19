package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.AmenitiesRules;


public class AmenitiesRulesDAOImpl implements AmenitiesRulesDAO {
	
	private static final String SQL_INSERT_AMENITIES = "INSERT INTO AmenitiesRules VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_FIND_AMENITIES_BY_LISTING_ID = "SELECT * FROM AmenitiesRules WHERE Listing_id = ?";
	
	private static final String SQL_UPDATE_AMENITIES = "UPDATE AmenitiesRules SET Wifi = ? , Cooling = ? , Heating = ? , Kitchen = ?"
			+ " , Tv = ? , Parking_lot = ? ,  Elevator = ? , Smoking = ? , Pets = ? , Events = ? WHERE Listing_id = ?";
	
	
	private ConnectionFactory factory;
	
	
	public AmenitiesRulesDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public void insert_amenities_rules(AmenitiesRules amenities) {
		
		Object[] values = { amenities.getListing_id(), amenities.getWifi(), amenities.getCooling(), amenities.getHeating(),
				amenities.getKitchen(), amenities.getTv(), amenities.getParking_lot(), amenities.getElevator(), 
				amenities.getSmoking(), amenities.getPets(), amenities.getEvents()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_AMENITIES, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_amenities");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public AmenitiesRules find_amenities_rules(int listing_id) {
		
		AmenitiesRules amenities = null;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_AMENITIES_BY_LISTING_ID, false, listing_id);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {
				amenities = map(myRes);
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   find_amenities");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return amenities;
		
	}
	
	@Override
	public void update_amenities_rules(AmenitiesRules amenities) {
		
		Object[] values = { amenities.getWifi(), amenities.getCooling(), amenities.getHeating(), amenities.getKitchen(), 
				amenities.getTv(), amenities.getParking_lot(), amenities.getElevator(), amenities.getSmoking(), 
				amenities.getPets(), amenities.getEvents(), amenities.getListing_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_AMENITIES, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_amenities");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static AmenitiesRules map(ResultSet resultSet) throws SQLException {
		
		AmenitiesRules amenities = new AmenitiesRules();
		
		amenities.setListing_id(resultSet.getInt("Listing_id"));
		amenities.setWifi(resultSet.getString("Wifi"));
		amenities.setCooling(resultSet.getString("Cooling"));
		amenities.setHeating(resultSet.getString("Heating"));
		amenities.setKitchen(resultSet.getString("Kitchen"));
		amenities.setTv(resultSet.getString("Tv"));
		amenities.setParking_lot(resultSet.getString("Parking_lot"));
		amenities.setElevator(resultSet.getString("Elevator"));
		amenities.setSmoking(resultSet.getString("Smoking"));
		amenities.setPets(resultSet.getString("Pets"));
		amenities.setEvents(resultSet.getString("Events"));
		
        return amenities;
        
    }
	
	
}