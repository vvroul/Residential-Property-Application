package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListingDAO;
import dao.ListingDAOImpl;
import entities.AmenitiesRules;
import entities.Listing;
import other.Date_util;
import other.Listing_comparator;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		
		Listing listing = new Listing();
		
		AmenitiesRules amenities = new AmenitiesRules();
		
		listing.setLocation(request.getParameter("Location"));
		listing.setAccommodates(Integer.parseInt(request.getParameter("Accommodates")));
		
		String filters = (request.getParameter("filters"));
		
		if (filters.equals("true")) {
			
			if ( (request.getParameter("Price") != null) && (request.getParameter("Price") != "" ) ) {
				listing.setPrice(Integer.parseInt(request.getParameter("Price")));
			}
			else {
				listing.setPrice(0);
			}
			
			listing.setRoom_type(request.getParameter("Room_type"));
			
			amenities.setWifi(request.getParameter("Wifi"));
			amenities.setCooling(request.getParameter("Cooling"));
			amenities.setHeating(request.getParameter("Heating"));
			amenities.setKitchen(request.getParameter("Kitchen"));
			amenities.setTv(request.getParameter("Tv"));
			amenities.setParking_lot(request.getParameter("Parking_lot"));
			amenities.setElevator(request.getParameter("Elevator"));
			
		}
		
		String date1 = request.getParameter("Date1");
		String date2 = request.getParameter("Date2");
		
		int days_count = Date_util.days_diff_by_dates(date1,date2);
		
		ArrayList<Listing> listings = listingdao.search_listing(listing, amenities, Date_util.String_to_date(date1), Date_util.String_to_date(date2), days_count);
	
		Collections.sort(listings , new Listing_comparator());
	
		String nextJSP = "/jsp_scripts/View_Rents.jsp";
		HttpSession session = request.getSession();
		session.setAttribute("listings", listings);
		session.setAttribute("date1", date1);
		session.setAttribute("date2", date2);
		session.setAttribute("days_count", days_count);
		session.setAttribute("accommodates", listing.getAccommodates());
		session.setAttribute("location", listing.getLocation());
		
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
