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

import dao.AmenitiesRulesDAO;
import dao.AmenitiesRulesDAOImpl;
import dao.ChatDAO;
import dao.ChatDAOImpl;
import dao.ListingDAO;
import dao.ListingDAOImpl;
import dao.PhotoDAO;
import dao.PhotoDAOImpl;
import dao.ReviewDAO;
import dao.ReviewDAOImpl;
import entities.AmenitiesRules;
import entities.Chat;
import entities.Listing;
import entities.Photo;
import entities.Review;
import entities.User;
import other.Chat_comparator;

/**
 * Servlet implementation class Host_listing_details
 */
@WebServlet("/Host_listing_details")
public class Host_listing_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Host_listing_details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int listing_id = Integer.parseInt(request.getParameter("Listing_id"));
		
		HttpSession session = request.getSession();
		
		User user = (User)(session.getAttribute("user"));
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		AmenitiesRulesDAO amenitiesdao = new AmenitiesRulesDAOImpl(false);
		PhotoDAO photosdao = new PhotoDAOImpl(false);
		ChatDAO chatdao = new ChatDAOImpl(false);
		
		Listing listing = listingdao.find_listing(listing_id);
		ArrayList<Review> reviews = reviewdao.find_reviews_of_a_listing(listing_id);
		AmenitiesRules amenities_rules = amenitiesdao.find_amenities_rules(listing_id);
		ArrayList<Photo> photos = photosdao.find_photos_of_a_listing(listing_id);
		ArrayList<Chat> chats = chatdao.find_chats_of_listing(listing_id);
		
		Collections.sort(chats,new Chat_comparator(user.getUser_id()));
	
		String nextJSP = "/jsp_scripts/Host_Listing_Details.jsp";
		
		session.setAttribute("listing", listing);
		session.setAttribute("reviews", reviews);
		session.setAttribute("amenities_rules", amenities_rules);
		session.setAttribute("photos", photos);
		session.setAttribute("chats", chats);
		
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
