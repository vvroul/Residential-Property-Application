package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.ListingDAO;
import dao.ListingDAOImpl;
import dao.ReviewDAO;
import dao.ReviewDAOImpl;
import entities.Review;
import entities.User;
import entities.Listing;

/**
 * Servlet implementation class New_review
 */
@WebServlet("/New_review")
public class New_review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New_review() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		Listing listing = (Listing)session.getAttribute("listing");
		
		@SuppressWarnings("unchecked")
		ArrayList<Review> reviews = (ArrayList<Review>)session.getAttribute("reviews");
		
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		Review review = new Review();
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		
		review.setReview_id(reviewdao.get_new_review_id());
		review.setListing_id(listing.getListing_id());
		review.setReview_date(java.sql.Date.valueOf(LocalDate.now()));
		review.setReviewer_id(user.getUser_id());
		review.setReviewer_name(user.getUsername());
		review.setScore(Integer.parseInt(request.getParameter("score")));
		review.setMessage(request.getParameter("new_message"));
		
		reviewdao.insert_review(review);
		
		listing.setNumber_of_reviews(listing.getNumber_of_reviews()+1);
		int sum = listingdao.get_sum_score_of_reviews(listing.getListing_id());
		listing.setReview_scores_rating(sum/listing.getNumber_of_reviews());
		
		listingdao.update_listing_reviews(listing);
		
		reviews.add(review);
		
		String nextJSP = "/jsp_scripts/Listing_Details.jsp";
		session.setAttribute("listing", listing);
		session.setAttribute("reviews", reviews);
		
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
