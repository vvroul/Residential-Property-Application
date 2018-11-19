package servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AmenitiesRulesDAO;
import dao.AmenitiesRulesDAOImpl;
import dao.ListingDAO;
import dao.ListingDAOImpl;
import entities.AmenitiesRules;
import entities.Listing;
import entities.User;
import other.Date_util;

/**
 * Servlet implementation class Host_new_listing
 */
@WebServlet("/Host_new_listing")
public class Host_new_listing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Host_new_listing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		Listing listing = new Listing();
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		
		listing.setListing_id(listingdao.get_new_listing_id());
		
		listing.setName(request.getParameter("Name"));
		listing.setLocation(request.getParameter("Location"));
		listing.setLatitude(Float.parseFloat(request.getParameter("Latitude")));
		listing.setLongitude(Float.parseFloat(request.getParameter("Longitude")));
		listing.setAccommodates(Integer.parseInt(request.getParameter("Accommodates")));
		listing.setPhoto_medium(request.getParameter("Photo_medium"));
		listing.setPhoto_large(request.getParameter("Photo_large"));
		listing.setPrice(Integer.parseInt(request.getParameter("Price")));
		listing.setCleaning_fee(Integer.parseInt(request.getParameter("Cleaning_fee")));
		listing.setProperty_type(request.getParameter("Property_type"));
		listing.setRoom_type(request.getParameter("Room_type"));
		listing.setBeds(Integer.parseInt(request.getParameter("Beds")));
		listing.setNumber_of_reviews(0);
		listing.setReview_scores_rating(0);
		
		listing.setHost_id(user.getUser_id());
		listing.setBathrooms(Integer.parseInt(request.getParameter("Bathrooms")));
		listing.setBedrooms(Integer.parseInt(request.getParameter("Bedrooms")));
		listing.setBed_type(request.getParameter("Bed_type"));
		listing.setSquare_feet(Integer.parseInt(request.getParameter("Square_feet")));
		listing.setDescription(request.getParameter("Description"));
		listing.setMinimum_nights(Integer.parseInt(request.getParameter("Minimum_nights")));
		listing.setMaximum_nights(Integer.parseInt(request.getParameter("Maximum_nights")));
		listing.setTransit(request.getParameter("Transit"));
		listing.setGuest_included(Integer.parseInt(request.getParameter("Guest_included")));
		listing.setExtra_people(Float.parseFloat(request.getParameter("Extra_people")));
		
		
		AmenitiesRulesDAO amenitiesdao = new AmenitiesRulesDAOImpl(false);
		
		AmenitiesRules amenities_rules = new AmenitiesRules();
		
		amenities_rules.setListing_id(listing.getListing_id());
		amenities_rules.setWifi(request.getParameter("Wifi"));
		amenities_rules.setCooling(request.getParameter("Cooling"));
		amenities_rules.setHeating(request.getParameter("Heating"));
		amenities_rules.setKitchen(request.getParameter("Kitchen"));
		amenities_rules.setTv(request.getParameter("Tv"));
		amenities_rules.setParking_lot(request.getParameter("Parking_lot"));
		amenities_rules.setElevator(request.getParameter("Elevator"));
		amenities_rules.setSmoking(request.getParameter("Smoking"));
		amenities_rules.setPets(request.getParameter("Pets"));
		amenities_rules.setEvents(request.getParameter("Events"));
		
		
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		int days_count = Date_util.days_diff_by_dates(date1, date2);
	
		
		listingdao.insert_listing(listing);					// new listing
		listingdao.insert_listing_secondary(listing);
		amenitiesdao.insert_amenities_rules(amenities_rules);			
		listingdao.new_listing_calendar(listing, Date_util.String_to_date(date1), days_count);		// new listing calendar
	
		
		String nextJSP = "/jsp_scripts/Host_Listing_Details.jsp";
		
		session.setAttribute("listing", listing);
		session.setAttribute("reviews", null);
		session.setAttribute("amenities_rules", amenities_rules);
		
		create_directories(user,listing);
		response.sendRedirect(request.getContextPath() + nextJSP);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void create_directories(User user , Listing listing) throws SecurityException {
		
		String path_photos = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images" + File.separator + listing.getListing_id();
		String path_medium_photo = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images" + File.separator + listing.getListing_id() + File.separator + "listing_medium_image";
		String path_large_photo = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images" + File.separator + listing.getListing_id() + File.separator + "listing_large_image";

		
		
		File Dir_photos = new File(path_photos);
		File Dir_medium_photo = new File(path_medium_photo);
		File Dir_large_photo = new File(path_large_photo);
		
		if (!Dir_photos.exists()) {
		    System.err.println("creating directory: " + Dir_photos.getName());
		    boolean result = false;

		    try{
		    	Dir_photos.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.err.println("DIR created");  
		    }
		}
		
		if (!Dir_medium_photo.exists()) {
		    System.err.println("creating directory: " + Dir_medium_photo.getName());
		    boolean result = false;

		    try{
		    	Dir_medium_photo.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.err.println("DIR created");  
		    }
		}
		
		if (!Dir_large_photo.exists()) {
		    System.err.println("creating directory: " + Dir_large_photo.getName());
		    boolean result = false;

		    try{
		    	Dir_large_photo.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.err.println("DIR created");  
		    }
		}
		
	}

}
