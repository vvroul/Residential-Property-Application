package other;

import java.util.Comparator;

import entities.Chat;

public class Chat_comparator implements Comparator<Chat> {
	
	private static int User_id;
	
	public Chat_comparator(int user_id) {
		User_id = user_id;
	}
	
    @Override
    public int compare(Chat chat1, Chat chat2) {
    	//System.err.println("chat1 id = " + chat1.getChat_id() + " , seen = " + chat1.getSeen() + " , who_wrote_it = " + chat1.getWho_wrote_it());
    	//System.err.println("chat2 id = " + chat2.getChat_id() + " , seen = " + chat2.getSeen() + " , who_wrote_it = " + chat2.getWho_wrote_it());
    	//System.err.println("compare = " + Boolean.compare(chat1.getSeen(),chat2.getSeen()));
    	int ret_value = -1;
    	if (chat1.getSeen() != chat2.getSeen()) {							// diif seen , check seen
    		ret_value = Boolean.compare(chat1.getSeen(),chat2.getSeen());
    	}
    	else {																// same seen , host > other user
    		if (chat1.getWho_wrote_it() == User_id) {
    			ret_value = 1;
    		}
    		else {
    			ret_value = -1;
    		}
    	}
    	return ret_value;
    }
}
