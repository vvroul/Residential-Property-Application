package servlets;

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
import other.Date_util;

/**
 * Servlet implementation class Host_update_listing
 */
@WebServlet("/Host_update_listing")
public class Host_update_listing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Host_update_listing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int listing_id = Integer.parseInt(request.getParameter("Listing_id"));
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		
		Listing listing = listingdao.find_listing(listing_id);
		
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
		
		AmenitiesRules amenities_rules = amenitiesdao.find_amenities_rules(listing_id);
		
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
		
		
		if ( (request.getParameter("date1") != "") && (request.getParameter("date2") != "") ) {
			String date1 = request.getParameter("date1");
			String date2 = request.getParameter("date2");
			int days_count = Date_util.days_diff_by_dates(date1, date2);
			
			if ( listingdao.check_dates_exist(listing, Date_util.String_to_date(date1)) == -1) {	// check if dates already exist
				if ( listingdao.check_dates_exist(listing, Date_util.String_to_date(date2)) == -1) {
					listingdao.new_listing_calendar(listing, Date_util.String_to_date(date1), days_count);		// new listing calendar
					session.setAttribute("date_error", "dates added successfully");
				}
				else {
					session.setAttribute("date_error", "dates already in use");
				}
			}
		}
		
		
		listingdao.update_listing(listing);
		listingdao.update_listing_secondary(listing);
		amenitiesdao.update_amenities_rules(amenities_rules);
	
		String nextJSP = "/jsp_scripts/Host_Listing_Details.jsp";
		
		session.setAttribute("listing", listing);
		session.setAttribute("amenities_rules", amenities_rules);
		
		response.sendRedirect(request.getContextPath() + nextJSP);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
