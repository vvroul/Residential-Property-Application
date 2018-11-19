package servlets;

import java.io.IOException;
import java.util.*;

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
import dao.PhotoDAO;
import dao.PhotoDAOImpl;
import dao.ReviewDAO;
import dao.ReviewDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.AmenitiesRules;
import entities.Listing;
import entities.Photo;
import entities.Review;
import entities.User;

/**
 * Servlet implementation class Listing_details
 */
@WebServlet("/Listing_details")
public class Listing_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listing_details() {
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
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		AmenitiesRulesDAO amenitiesdao = new AmenitiesRulesDAOImpl(false);
		PhotoDAO photodao = new PhotoDAOImpl(false);
		UserDAO userdao = new UserDAOImpl(false);
		
		Listing listing = listingdao.find_listing(listing_id);
		ArrayList<Review> reviews = reviewdao.find_reviews_of_a_listing(listing_id);
		AmenitiesRules amenities_rules = amenitiesdao.find_amenities_rules(listing_id);
		ArrayList<Photo> photos = photodao.find_photos_of_a_listing(listing_id);
		User host = userdao.find_user_as_host(listing.getHost_id());
		
		String nextJSP = "/jsp_scripts/Listing_Details.jsp";
		session.setAttribute("listing", listing);
		session.setAttribute("reviews", reviews);
		session.setAttribute("amenities_rules", amenities_rules);
		session.setAttribute("photos", photos);
		session.setAttribute("host", host);
		
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
