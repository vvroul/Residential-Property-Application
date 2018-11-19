package dao;

import java.util.ArrayList;

import entities.User;

public interface UserDAO {
	
	public void insert_user(User user);
	
	public void insert_user_as_host(User user);
	
	public void update_user(User user);
	
	public void update_user_as_host(User user);
	
	public User find_user_by_username_and_pass(String username,String pass);
	
	public User find_user_by_username(String username);
	
	public User find_user_with_same_username(String username , int user_id);
	
	public User find_user(int user_id);
	
	public User find_user_as_host(int user_id);
	
	public ArrayList<User> users_list();
	
	public void update_user_verified(int user_id , boolean verified);
	
	public void update_user_photo(int user_id , String photo);
	
	public void update_user_admin_level(int user_id , int admin_level);
	
	public void update_host_responce_rate(int user_id , float host_responce_rate);
	
	public void update_host_acceptance_rate(int user_id , float host_acceptance_rate);
	
	public void update_host_listing_count(int user_id , int host_listing_count);
	
	public void update_host_total_listing_count(int user_id , int host_total_listing_count);
	
	public void verify_host(int user_id , boolean host_identity_verified);
	
	public int get_new_user_id();
	
}

