package dao;

import java.util.ArrayList;

import entities.User;

public interface UserDAO {
	
	public ArrayList<User> users_list();
	
	public ArrayList<User> hosts_list();
	
	public void update_hosts(ArrayList<User> hosts);
	
}

