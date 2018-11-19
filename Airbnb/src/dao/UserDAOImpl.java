package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import entities.User;

public class UserDAOImpl implements UserDAO {
	
	private static final String SQL_INSERT_USER = "INSERT INTO Users VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_INSERT_USER_AS_HOST = "INSERT INTO Hosts VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_USER = "UPDATE Users SET Username = ? , Password = ? , Name = ? , Surname = ? , "
			+ " Email = ? , Contact_number = ? , Photo = ? , Admin_level = ? WHERE User_id = ?";
	
	private static final String SQL_UPDATE_USER_AS_HOST = "UPDATE Hosts SET Host_location = ? , Host_about = ? , Host_responce_time = ? , "
			+ " Host_is_superhost = ? , Host_neighbourhood = ? , Host_verifications = ? ,  Host_has_profile_pic = ? "
			+ " WHERE Host_id = ?";
	
	private static final String SQL_FIND_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM Users WHERE Username = ? and Password = ?";
	
	private static final String SQL_FIND_USER_BY_USERNAME = "SELECT * FROM Users WHERE Username = ?";
	
	private static final String SQL_FIND_USER_WITH_SAME_USERNAME ="SELECT * FROM Users WHERE Username = ? AND User_id <> ?"; 
	
	private static final String SQL_FIND_USER_BY_USERID = "SELECT * FROM Users WHERE User_id = ?";
	
	private static final String SQL_FIND_USER_AS_HOST_BY_USERID = "SELECT * FROM Users , Hosts WHERE User_id = Host_id AND User_id = ?";
	
	private static final String SQL_FINDALL_USERS = "SELECT * FROM Users";
	
	private static final String SQL_UPDATE_USER_VERIFIED = "UPDATE Users SET Verified = ? WHERE User_id = ?";
	
	private static final String SQL_UPDATE_USER_PHOTO = "UPDATE Users SET Photo = ? WHERE User_id = ?";
	
	private static final String SQL_UPDATE_USER_ADMIN_LEVEL = "UPDATE Users SET Admin_level = ? WHERE User_id = ?";
	
	private static final String SQL_UPDATE_HOST_RESPONSE_RATE = "UPDATE Hosts SET Host_responce_rate = ? WHERE Host_id = ?";
	
	private static final String SQL_UPDATE_HOST_ACCEPTANCE_RATE = "UPDATE Hosts SET Host_acceptance_rate = ? WHERE Host_id = ?";
	
	private static final String SQL_UPDATE_HOST_LISTINGS_COUNT = "UPDATE Hosts SET Host_listings_count = ? WHERE Host_id = ?";
	
	private static final String SQL_UPDATE_HOST_TOTAL_LISTINGS_COUNT = "UPDATE Hosts SET Host_total_listings_count = ? WHERE Host_id = ?";
	
	private static final String SQL_UPDATE_HOST_IDENTITY_VERIFIED = "UPDATE Hosts SET Host_identity_verified = ? WHERE Host_id = ?";
	
	private static final String SQL_MAX_USER_ID = "SELECT MAX(User_id) FROM Users";
	
	
	
	private ConnectionFactory factory;
	
	
	public UserDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public void insert_user(User user) {
		
		Object[] values = { user.getUser_id(), user.getUsername(), user.getPassword(), user.getName(),
				user.getSurname(), user.getEmail(), user.getContact_number(), user.getPhoto(), 
				user.getAdmin_level(), user.getVerified()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_USER, false, values);
			pstmt.executeUpdate();	
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void insert_user_as_host(User user) {
		
		Object[] values = { user.getUser_id(), user.getHost_since(), user.getHost_location(), user.getHost_about(),
				user.getHost_responce_time(), user.getHost_responce_rate(), user.getHost_acceptance_rate(), user.getHost_is_superhost(), 
				user.getHost_neighbourhood(), user.getHost_listings_count(), user.getHost_total_listings_count(), user.getHost_verifications(),
				user.getHost_has_profile_pic(), user.getHost_identity_verified()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_USER_AS_HOST, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_user(User user) {
		
		Object[] values = { user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), 
				user.getContact_number(), user.getPhoto(), user.getAdmin_level(), user.getUser_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_USER, false, values);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void update_user_as_host(User user) {
		
		Object[] values = { user.getHost_location(), user.getHost_about(), user.getHost_responce_time(), user.getHost_is_superhost(), 
				user.getHost_neighbourhood(), user.getHost_verifications(),	user.getHost_has_profile_pic(), user.getUser_id()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_USER_AS_HOST, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public User find_user_by_username_and_pass(String username,String pass) {
		
		User user = null;
		
		Object[] values = {username, pass};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_USER_BY_USERNAME_AND_PASSWORD, false, values);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {
				user = map_user(myRes);      
			}	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@Override
	public User find_user_by_username(String username) {
		
		User user = null;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_USER_BY_USERNAME, false, username);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {
				user = map_user(myRes);    
			}
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@Override
	public User find_user_with_same_username(String username , int user_id) {
		
		Object[] values = {username, user_id};
		
		User user = null;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_USER_WITH_SAME_USERNAME, false, values);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {
				user = map_user(myRes);    
			}
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@Override
	public User find_user(int user_id) {
		
		User user = null;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_USER_BY_USERID, false, user_id);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {
				user = map_user(myRes);   
			}
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@Override
	public User find_user_as_host(int user_id) {
		
		User user = null;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_USER_AS_HOST_BY_USERID, false, user_id);
			ResultSet myRes = pstmt.executeQuery();
			if (myRes.next()) {	
				user = map_host(myRes);   
			}
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	@Override
	public ArrayList<User> users_list() {
		
		ArrayList<User> users = new ArrayList<>();

        try {
        	Connection con = factory.getConnection();
    		PreparedStatement pstmt = con.prepareStatement(SQL_FINDALL_USERS);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	users.add(map_user(myRes));
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
	public void update_user_verified(int user_id , boolean verified) {
		
		Object[] values = {verified, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_USER_VERIFIED, false, values);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_user_photo(int user_id , String photo) {
		
		Object[] values = {photo, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_USER_PHOTO, false, values);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_user_admin_level(int user_id , int admin_level) {
		
		Object[] values = {admin_level, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_USER_ADMIN_LEVEL, false, values);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update_host_responce_rate(int user_id , float host_responce_rate) {
		
		Object[] values = {host_responce_rate, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_HOST_RESPONSE_RATE, false, values);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_host_acceptance_rate(int user_id , float host_acceptance_rate) {
		
		Object[] values = {host_acceptance_rate, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_HOST_ACCEPTANCE_RATE, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_host_listing_count(int user_id , int host_listing_count) {
		
		Object[] values = {host_listing_count, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_HOST_LISTINGS_COUNT, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update_host_total_listing_count(int user_id , int host_total_listing_count) {
		
		Object[] values = {host_total_listing_count, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_HOST_TOTAL_LISTINGS_COUNT, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void verify_host(int user_id , boolean host_identity_verified) {
		
		Object[] values = {host_identity_verified, user_id};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_HOST_IDENTITY_VERIFIED, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int get_new_user_id() {
		
		int next_user_id = 1;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_MAX_USER_ID);
			ResultSet myRes = pstmt.executeQuery();
            if (myRes.next()) {
            	next_user_id = Integer.parseInt(myRes.getString(1));
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return next_user_id + 1;
	}
	
	
	
	private static User map_user(ResultSet resultSet) throws SQLException {
		
        User user = new User();
        
        user.setUser_id(resultSet.getInt("User_id"));
        user.setUsername(resultSet.getString("Username"));
        user.setPassword(resultSet.getString("Password"));
        user.setName(resultSet.getString("Name"));
        user.setSurname(resultSet.getString("Surname"));
        user.setEmail(resultSet.getString("Email"));
        user.setContact_number(resultSet.getString("Contact_number"));
        user.setPhoto(resultSet.getString("Photo"));
        user.setAdmin_level(resultSet.getInt("Admin_level"));
        user.setVerified(resultSet.getBoolean("Verified"));
        
        return user;
        
    }
	
	
	private static User map_host(ResultSet resultSet) throws SQLException {
		
        User user = new User();
        
        user.setUser_id(resultSet.getInt("User_id"));
        user.setUsername(resultSet.getString("Username"));
        user.setPassword(resultSet.getString("Password"));
        user.setName(resultSet.getString("Name"));
        user.setSurname(resultSet.getString("Surname"));
        user.setEmail(resultSet.getString("Email"));
        user.setContact_number(resultSet.getString("Contact_number"));
        user.setPhoto(resultSet.getString("Photo"));
        user.setAdmin_level(resultSet.getInt("Admin_level"));
        user.setVerified(resultSet.getBoolean("Verified"));
        user.setHost_since(resultSet.getDate("Host_since"));
        user.setHost_location(resultSet.getString("Host_location"));
        user.setHost_about(resultSet.getString("Host_about"));
        user.setHost_responce_time(resultSet.getString("Host_responce_time"));
        user.setHost_responce_rate(resultSet.getFloat("Host_responce_rate"));
        user.setHost_acceptance_rate(resultSet.getFloat("Host_acceptance_rate"));
        user.setHost_is_superhost(resultSet.getBoolean("Host_is_superhost"));
        user.setHost_neighbourhood(resultSet.getString("Host_neighbourhood"));
        user.setHost_listings_count(resultSet.getInt("Host_listings_count"));
        user.setHost_total_listings_count(resultSet.getInt("Host_total_listings_count"));
        user.setHost_verifications(resultSet.getString("Host_verifications"));
        user.setHost_has_profile_pic(resultSet.getBoolean("Host_has_profile_pic"));
        user.setHost_identity_verified(resultSet.getBoolean("Host_identity_verified"));
        
        return user;
        
    }
	
}

