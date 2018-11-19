package entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlRootElement(name = "Listing")
@XmlAccessorType (XmlAccessType.FIELD)
public class Listing {
	
	@XmlAttribute(name = "Listing_id")
	private int Listing_id;
	
	private String Name;
	private String Location;
	private float Latitude;
	private float Longitude;
	private int Accommodates;
	private String Photo_medium;
	private String Photo_large;
	private int Price;
	private int Cleaning_fee;
	private String Property_type;
	private String Room_type;
	private int Beds;
	private int Number_of_reviews;
	private int Review_scores_rating;
	
	private int Host_id;
	private int Bathrooms;
	private int Bedrooms;
	private String Bed_type;
	private int Square_feet;
	private String Description;
	private int Minimum_nights;
	private int Maximum_nights;
	private String Transit;
	private int Guest_included;
	private float Extra_people;
	
	
	public Listing() {
		
		Listing_id = 0;
		Name = "";
		Location = "";
		Latitude = 0;
		Longitude = 0;
		Accommodates = 0;
		Photo_medium = "";
		Photo_large = "";
		Price = 0;
		Cleaning_fee = 0;
		Property_type = "";
		Room_type = "";
		Beds = 0;
		Number_of_reviews = 0;
		Review_scores_rating = 0;
		Host_id = 0;
		Bathrooms = 0;
		Bedrooms = 0;
		Bed_type = "";
		Square_feet = 0;
		Description = "";
		Minimum_nights = 0;
		Maximum_nights = 0;
		Transit = "";
		Guest_included = 0;
		Extra_people = 0;
	}
	
	public int getListing_id() {
		return Listing_id;
	}
	public void setListing_id(int listing_id) {
		Listing_id = listing_id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public float getLatitude() {
		return Latitude;
	}
	public void setLatitude(float latitude) {
		Latitude = latitude;
	}
	public float getLongitude() {
		return Longitude;
	}
	public void setLongitude(float longitude) {
		Longitude = longitude;
	}
	public int getAccommodates() {
		return Accommodates;
	}
	public void setAccommodates(int accommodates) {
		Accommodates = accommodates;
	}
	public String getPhoto_medium() {
		return Photo_medium;
	}
	public void setPhoto_medium(String photo_medium) {
		Photo_medium = photo_medium;
	}
	public String getPhoto_large() {
		return Photo_large;
	}
	public void setPhoto_large(String photo_large) {
		Photo_large = photo_large;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public int getCleaning_fee() {
		return Cleaning_fee;
	}
	public void setCleaning_fee(int cleaning_fee) {
		Cleaning_fee = cleaning_fee;
	}
	public String getProperty_type() {
		return Property_type;
	}
	public void setProperty_type(String property_type) {
		Property_type = property_type;
	}
	public String getRoom_type() {
		return Room_type;
	}
	public void setRoom_type(String room_type) {
		Room_type = room_type;
	}
	public int getBeds() {
		return Beds;
	}
	public void setBeds(int beds) {
		Beds = beds;
	}
	public int getNumber_of_reviews() {
		return Number_of_reviews;
	}
	public void setNumber_of_reviews(int number_of_reviews) {
		Number_of_reviews = number_of_reviews;
	}
	public int getReview_scores_rating() {
		return Review_scores_rating;
	}
	public void setReview_scores_rating(int review_scores_rating) {
		Review_scores_rating = review_scores_rating;
	}
	public int getHost_id() {
		return Host_id;
	}
	public void setHost_id(int host_id) {
		Host_id = host_id;
	}
	public int getBathrooms() {
		return Bathrooms;
	}
	public void setBathrooms(int bathrooms) {
		Bathrooms = bathrooms;
	}
	public int getBedrooms() {
		return Bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		Bedrooms = bedrooms;
	}
	public String getBed_type() {
		return Bed_type;
	}
	public void setBed_type(String bed_type) {
		Bed_type = bed_type;
	}
	public int getSquare_feet() {
		return Square_feet;
	}
	public void setSquare_feet(int square_feet) {
		Square_feet = square_feet;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getMinimum_nights() {
		return Minimum_nights;
	}
	public void setMinimum_nights(int minimum_nights) {
		Minimum_nights = minimum_nights;
	}
	public int getMaximum_nights() {
		return Maximum_nights;
	}
	public void setMaximum_nights(int maximum_nights) {
		Maximum_nights = maximum_nights;
	}
	public String getTransit() {
		return Transit;
	}
	public void setTransit(String transit) {
		Transit = transit;
	}
	public int getGuest_included() {
		return Guest_included;
	}
	public void setGuest_included(int guest_included) {
		Guest_included = guest_included;
	}
	public float getExtra_people() {
		return Extra_people;
	}
	public void setExtra_people(float extra_people) {
		Extra_people = extra_people;
	}
	
}

