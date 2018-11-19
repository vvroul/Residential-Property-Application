package entities;

public class Chat {
	
	private int Chat_id;
	private int Message_counter;
	private int Listing_id;
	private int User_id;
	private int Host_id;
	private int Who_wrote_it;
	private String Message;
	private boolean Seen;
	
	public int getChat_id() {
		return Chat_id;
	}
	public void setChat_id(int chat_id) {
		Chat_id = chat_id;
	}
	public int getMessage_counter() {
		return Message_counter;
	}
	public void setMessage_counter(int message_counter) {
		Message_counter = message_counter;
	}
	public int getListing_id() {
		return Listing_id;
	}
	public void setListing_id(int listing_id) {
		Listing_id = listing_id;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public int getHost_id() {
		return Host_id;
	}
	public void setHost_id(int host_id) {
		Host_id = host_id;
	}
	public int getWho_wrote_it() {
		return Who_wrote_it;
	}
	public void setWho_wrote_it(int who_wrote_it) {
		Who_wrote_it = who_wrote_it;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public boolean getSeen() {
		return Seen;
	}
	public void setSeen(boolean seen) {
		Seen = seen;
	}
	
}

