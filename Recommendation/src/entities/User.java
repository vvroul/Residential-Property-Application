package entities;

import java.util.Date;

public class User {
	
	private int User_id;
	private String Username;
	private String Password;
	private String Name;
	private String Surname;
	private String Email;
	private String Contact_number;
	private String Photo;
	private int Admin_level;
	private Boolean Verified;
	
	private Date Host_since;
	private String Host_location;
	private String Host_about;
	private String Host_responce_time;
	private float Host_responce_rate;
	private float Host_acceptance_rate;
	private boolean Host_is_superhost;
	private String Host_neighbourhood;
	private int Host_listings_count;
	private int Host_total_listings_count;
	private String Host_verifications;
	private boolean Host_has_profile_pic;
	private boolean Host_identity_verified;
	
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getContact_number() {
		return Contact_number;
	}
	public void setContact_number(String contact_number) {
		Contact_number = contact_number;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public int getAdmin_level() {
		return Admin_level;
	}
	public void setAdmin_level(int admin_level) {
		Admin_level = admin_level;
	}
	public Boolean getVerified() {
		return Verified;
	}
	public void setVerified(Boolean verified) {
		Verified = verified;
	}
	public Date getHost_since() {
		return Host_since;
	}
	public void setHost_since(Date host_since) {
		Host_since = host_since;
	}
	public String getHost_location() {
		return Host_location;
	}
	public void setHost_location(String host_location) {
		Host_location = host_location;
	}
	public String getHost_about() {
		return Host_about;
	}
	public void setHost_about(String host_about) {
		Host_about = host_about;
	}
	public String getHost_responce_time() {
		return Host_responce_time;
	}
	public void setHost_responce_time(String host_responce_time) {
		Host_responce_time = host_responce_time;
	}
	public float getHost_responce_rate() {
		return Host_responce_rate;
	}
	public void setHost_responce_rate(float host_responce_rate) {
		Host_responce_rate = host_responce_rate;
	}
	public float getHost_acceptance_rate() {
		return Host_acceptance_rate;
	}
	public void setHost_acceptance_rate(float host_acceptance_rate) {
		Host_acceptance_rate = host_acceptance_rate;
	}
	public boolean getHost_is_superhost() {
		return Host_is_superhost;
	}
	public void setHost_is_superhost(boolean host_is_superhost) {
		Host_is_superhost = host_is_superhost;
	}
	public String getHost_neighbourhood() {
		return Host_neighbourhood;
	}
	public void setHost_neighbourhood(String host_neighbourhood) {
		Host_neighbourhood = host_neighbourhood;
	}
	public int getHost_listings_count() {
		return Host_listings_count;
	}
	public void setHost_listings_count(int host_listings_count) {
		Host_listings_count = host_listings_count;
	}
	public int getHost_total_listings_count() {
		return Host_total_listings_count;
	}
	public void setHost_total_listings_count(int host_total_listings_count) {
		Host_total_listings_count = host_total_listings_count;
	}
	public String getHost_verifications() {
		return Host_verifications;
	}
	public void setHost_verifications(String host_verifications) {
		Host_verifications = host_verifications;
	}
	public boolean getHost_has_profile_pic() {
		return Host_has_profile_pic;
	}
	public void setHost_has_profile_pic(boolean host_has_profile_pic) {
		Host_has_profile_pic = host_has_profile_pic;
	}
	public boolean getHost_identity_verified() {
		return Host_identity_verified;
	}
	public void setHost_identity_verified(boolean host_identity_verified) {
		Host_identity_verified = host_identity_verified;
	}
	
	
	
	
}

