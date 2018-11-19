package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entities.User;

public class UserDAOImpl implements UserDAO {
	
	private static final String SQL_FINDALL_USERS = "SELECT User_id , Username FROM Users "
			+ "WHERE User_id not in ( SELECT User_id FROM Users_with_no_reviews)";
	
	private static final String SQL_FINDALL_HOSTS = "SELECT User_id , Username FROM Users , Hosts WHERE User_id = Host_id";
	
	private static final String SQL_UPDATE_HOST = "UPDATE Hosts SET Host_listings_count = ? , Host_total_listings_count = ?"
			+ " WHERE Host_id = ?";
	
	
	private ConnectionFactory factory;
	
	
	public UserDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public ArrayList<User> users_list() {
		
		ArrayList<User> users = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
    		PreparedStatement pstmt = con.prepareStatement(SQL_FINDALL_USERS);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	users.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return users;
	}
	
	
	@Override
	public ArrayList<User> hosts_list() {
		
		ArrayList<User> hosts = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
    		PreparedStatement pstmt = con.prepareStatement(SQL_FINDALL_HOSTS);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	hosts.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "	hosts_list");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return hosts;
	}
	
	
	@Override
	public void update_hosts(ArrayList<User> hosts) {

		try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_HOST);
			
			int count = 0;
			
			for (User host : hosts) {
				pstmt.setInt(1, host.getHost_listings_count());
				pstmt.setInt(2, host.getHost_total_listings_count());
				pstmt.setInt(3, host.getUser_id());
				
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
			System.err.println(e.getMessage() + "     update_hosts");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	
	private static User map(ResultSet resultSet) throws SQLException {
		
        User user = new User();
        
        user.setUser_id(resultSet.getInt("User_id"));
        user.setUsername(resultSet.getString("Username"));
        
        return user;
        
    }
	
}

