package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import dao.ListingDAO;
import dao.ListingDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Booking;
import entities.Listing;
import entities.User;
import other.Date_util;

/**
 * Servlet implementation class New_booking
 */
@WebServlet("/New_booking")
public class New_booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New_booking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		User host = (User)session.getAttribute("host");
		Listing listing = (Listing)session.getAttribute("listing");
		
		String date1 = (String)session.getAttribute("date1");
		String date2 = (String)session.getAttribute("date2");
		int days_count = (int)session.getAttribute("days_count");
		int guests = (int)session.getAttribute("accommodates");
		
		
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		BookingDAO bookingdao = new BookingDAOImpl(false);
		UserDAO userdao = new UserDAOImpl(false);
		
		Booking booking = new Booking();
		booking.setListing_id(listing.getListing_id());
		booking.setUser_id(user.getUser_id());
		booking.setHost_id(host.getUser_id());
		booking.setStarting_date(Date_util.String_to_date(date1));
		booking.setFinal_date(Date_util.String_to_date(date2));
		booking.setGuests(guests);
		
		listingdao.book_listing_calendar(listing, user.getUser_id(), Date_util.String_to_date(date1), days_count);		// new booking
		bookingdao.insert_booking(booking);
		
		host.setHost_listings_count(host.getHost_listings_count()+1);
		userdao.update_host_listing_count(host.getUser_id(), host.getHost_listings_count());	// update host_listing_count
	
		String nextJSP = "/jsp_scripts/Welcome_Page.jsp";
		
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
