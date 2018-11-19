package other;

import java.util.Comparator;

import entities.User;

public class User_comparator implements Comparator<User> {
	
    @Override
    public int compare(User user1, User user2) {
    	return Boolean.compare(user1.getVerified(),user2.getVerified());
    }
    
}
