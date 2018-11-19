package dao;

import java.util.ArrayList;

import entities.Chat;

public interface ChatDAO {
	
	public void insert_chat_message(Chat chat);

	public void update_chat_message(Chat chat);
	
	public void update_chat_seen(Chat chat);
	
	public void delete_chat_message(Chat chat);
	
	public ArrayList<Chat> find_chat_by_users(int user_id , int user2_id);
	
	public Chat find_last_message_of_chat(int chat_id);
	
	public ArrayList<Chat> find_chats_of_user(int user_id);
	
	public ArrayList<Chat> find_chat_by_id(int chat_id);
	
	public ArrayList<Chat> find_chats_of_listing(int listing_id);
	
	public ArrayList<Chat> find_chat_of_listing_by_users(int listing_id , int user_id , int user2_id);
	
	public int get_new_chat_id();
	
}