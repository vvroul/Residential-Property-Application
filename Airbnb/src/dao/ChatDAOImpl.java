package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Chat;

public class ChatDAOImpl implements ChatDAO {
	
	private static final String SQL_INSERT_CHAT_MESSAGE = "INSERT INTO Chat VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_CHAT_MESSAGE = "UPDATE Chat SET Message = ? WHERE Chat_id = ? AND Message_counter = ?";
	
	private static final String SQL_UPDATE_CHAT_SEEN = "UPDATE Chat SET Seen = ? WHERE Chat_id = ? AND Message_counter = ?";
	
	private static final String SQL_DELETE_CHAT_MESSAGE = "DELETE FROM Chat WHERE Chat_id = ? AND Message_counter = ?";
	
	private static final String SQL_FIND_CHAT_BY_USERS = "SELECT * FROM Chat WHERE (User_id = ? AND Host_id = ?) OR "
			+ "(Host_id = ? AND User_id = ?)";
	
	private static final String SQL_FIND_CHAT_OF_LISTING_BY_USERS = "SELECT * FROM Chat WHERE Listing_id = ? "
			+ " AND ( (User_id = ? AND Host_id = ?) OR (Host_id = ? AND User_id = ?) )";
	
	private static final String SQL_FIND_CHAT_BY_ID = "SELECT * FROM Chat WHERE Chat_id = ?";
	
	private static final String SQL_FIND_CHAT_LAST_MESSAGE = "SELECT * FROM Chat WHERE Chat_id = ? AND Message_counter in "
			+ " (SELECT MAX(Message_counter) FROM Chat WHERE Chat_id = ?)";
	
	private static final String SQL_FIND_CHATS_OF_USER = "SELECT DISTINCT Chat_id, Message_counter, c.User_id, Host_id, Who_wrote_it, u.Username, Seen"
			+ " FROM Chat as c , Users as u "
			+ " WHERE ( (c.User_id = ? AND c.Host_id = u.User_id) OR (Host_id = ? AND c.User_id = u.User_id) ) "
			+ " AND Message_counter in "
			+ 		" (SELECT MAX(Message_counter) FROM Chat as i WHERE c.Chat_id = i.Chat_id)";
	
	private static final String SQL_FIND_CHATS_OF_LISTING = "SELECT DISTINCT Chat_id, Message_counter, Listing_id, c.User_id, Host_id, Who_wrote_it, u.Username, Seen"
			+ " FROM Chat as c , Users as u "
			+ " WHERE Listing_id = ? AND c.User_id = u.User_id "
			+ " AND Message_counter in "
			+ 		" (SELECT MAX(Message_counter) FROM Chat as i WHERE c.Chat_id = i.Chat_id)";
	
	private static final String SQL_MAX_CHAT_ID = "SELECT MAX(Chat_id) FROM Chat";
	
	
	
	private ConnectionFactory factory;
	
	
	public ChatDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	
	@Override
	public void insert_chat_message(Chat chat) {
		
		Object[] values = { chat.getChat_id(), chat.getMessage_counter(), chat.getListing_id(), chat.getUser_id(), 
							chat.getHost_id(), chat.getWho_wrote_it(), chat.getMessage(), chat.getSeen()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_INSERT_CHAT_MESSAGE, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_chat_message");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void update_chat_message(Chat chat) {
		
		Object[] values = { chat.getMessage(), chat.getChat_id(), chat.getMessage_counter()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_CHAT_MESSAGE, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_chat_message");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void update_chat_seen(Chat chat) {
		
		Object[] values = { chat.getSeen(), chat.getChat_id(), chat.getMessage_counter()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_UPDATE_CHAT_SEEN, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   update_chat_seen");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete_chat_message(Chat chat) {
		
		Object[] values = { chat.getChat_id(), chat.getMessage_counter()};
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_DELETE_CHAT_MESSAGE, false, values);
			pstmt.executeUpdate();	
		}
		catch (SQLException e) {
			System.err.println(e.getMessage()+"   delete_chat_message");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ArrayList<Chat> find_chat_by_users(int user_id , int user2_id) {
		
		ArrayList<Chat> chat = new ArrayList<>();
		
		Object[] values = { user_id, user2_id, user_id, user2_id};
		
        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_CHAT_BY_USERS, false, values);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	chat.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "    find_chat_by_users");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return chat;
	}
	
	
	
	@Override
	public ArrayList<Chat> find_chat_by_id(int chat_id) {
		
		ArrayList<Chat> chat = new ArrayList<>();
		
        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_CHAT_BY_ID, false, chat_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	chat.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "    find_chat_by_id");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return chat;
	}
	
	
	@Override
	public Chat find_last_message_of_chat(int chat_id) {
		
		Chat chat = null;
		
		Object[] values = { chat_id, chat_id};

        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_CHAT_LAST_MESSAGE, false, values);
            ResultSet myRes = pstmt.executeQuery();
            if (myRes.next()) {
            	chat = map(myRes);
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "    find_last_message_of_chat");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return chat;
	}
	
	
	
	@Override
	public ArrayList<Chat> find_chats_of_user(int user_id) {
		
		ArrayList<Chat> chat = new ArrayList<>();
		
		Object[] values = { user_id, user_id};
		
        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_CHATS_OF_USER, false, values);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	chat.add(map_chats_of_user(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "    find_chats_of_user");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return chat;
	}
	
	
	@Override
	public ArrayList<Chat> find_chats_of_listing(int listing_id) {
		
		ArrayList<Chat> chat = new ArrayList<>();
		
        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_CHATS_OF_LISTING, false, listing_id);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	chat.add(map_chats_of_listing(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "    find_chats_of_listing");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return chat;
	}
	
	
	
	@Override
	public ArrayList<Chat> find_chat_of_listing_by_users(int listing_id , int user_id , int user2_id) {
		
		ArrayList<Chat> chat = new ArrayList<>();
		
		Object[] values = { listing_id, user_id, user2_id, user_id, user2_id };
		
        try {
        	Connection con = factory.getConnection();
			PreparedStatement pstmt = DAOUtil.prepareStatement(con,SQL_FIND_CHAT_OF_LISTING_BY_USERS, false, values);
            ResultSet myRes = pstmt.executeQuery();
            while (myRes.next()) {
            	chat.add(map(myRes));
            }
        } 
        catch (SQLException e) {
			System.err.println(e.getMessage() + "    find_chat_of_listing_by_users");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        
		return chat;
	}
	
	
	@Override
	public int get_new_chat_id() {
		
		int next_chat_id = 1;
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_MAX_CHAT_ID);
			ResultSet myRes = pstmt.executeQuery();
            if (myRes.next()) {
            	next_chat_id = Integer.parseInt(myRes.getString(1));
            }
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage() + "    get_new_chat_id");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return next_chat_id + 1;
	}
	
	
	private static Chat map(ResultSet resultSet) throws SQLException {
		
		Chat chat = new Chat();
		
		chat.setChat_id(resultSet.getInt("Chat_id"));
		chat.setMessage_counter(resultSet.getInt("Message_counter"));
		chat.setListing_id(resultSet.getInt("Listing_id"));
		chat.setUser_id(resultSet.getInt("User_id"));
		chat.setHost_id(resultSet.getInt("Host_id"));
		chat.setWho_wrote_it(resultSet.getInt("Who_wrote_it"));
		chat.setMessage(resultSet.getString("Message"));
		chat.setSeen(resultSet.getBoolean("Seen"));
		
		return chat;
	        
	}
	
	
	private static Chat map_chats_of_user(ResultSet resultSet) throws SQLException {
		
		Chat chat = new Chat();
		
		chat.setChat_id(resultSet.getInt("Chat_id"));
		chat.setMessage_counter(resultSet.getInt("Message_counter"));
		chat.setUser_id(resultSet.getInt("User_id"));
		chat.setHost_id(resultSet.getInt("Host_id"));
		chat.setWho_wrote_it(resultSet.getInt("Who_wrote_it"));
		chat.setMessage(resultSet.getString("Name"));			// here we store the name of the other user , for printing purpose , we wont need message anyway for now
		chat.setSeen(resultSet.getBoolean("Seen"));
		
		return chat;
	        
	}
	
	
	private static Chat map_chats_of_listing(ResultSet resultSet) throws SQLException {
		
		Chat chat = new Chat();
		
		chat.setChat_id(resultSet.getInt("Chat_id"));
		chat.setMessage_counter(resultSet.getInt("Message_counter"));
		chat.setListing_id(resultSet.getInt("Listing_id"));
		chat.setUser_id(resultSet.getInt("User_id"));
		chat.setHost_id(resultSet.getInt("Host_id"));
		chat.setWho_wrote_it(resultSet.getInt("Who_wrote_it"));
		chat.setMessage(resultSet.getString("Username"));			// here we store the name of the other user , for printing purpose , we wont need message anyway for now
		chat.setSeen(resultSet.getBoolean("Seen"));
		
		return chat;
	        
	}
	
	
	
}