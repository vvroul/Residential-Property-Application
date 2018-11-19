package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListingDAO;
import dao.ListingDAOImpl;
import dao.ReviewDAO;
import dao.ReviewDAOImpl;
import entities.Listing;
import entities.Review;

/**
 * Servlet implementation class Update_review
 */
@WebServlet("/Update_review")
public class Update_review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_review() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Listing listing = (Listing)request.getAttribute("listing");
		Review review = (Review)request.getAttribute("review");
		
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		
		review.setScore(Integer.parseInt(request.getParameter("score")));
		review.setMessage(request.getParameter("new_message"));
		
		reviewdao.update_review(review);
		
		int sum = listingdao.get_sum_score_of_reviews(listing.getListing_id());
		listing.setReview_scores_rating(sum/listing.getNumber_of_reviews());
		
		listingdao.update_listing_reviews(listing);
		
		ArrayList<Review> reviews = new ArrayList<>();
		reviews = reviewdao.find_reviews_of_a_listing(listing.getListing_id());
		
		String nextJSP = "/Room.jsp";
		HttpSession session = request.getSession();
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
