package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListingDAO;
import dao.ListingDAOImpl;
import entities.Listing;
import entities.User;

/**
 * Servlet implementation class Add_listing_url_large_photo
 */
@WebServlet("/Add_listing_url_large_photo")
public class Add_listing_url_large_photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_listing_url_large_photo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		Listing listing = (Listing)session.getAttribute("listing");
		User user = (User)session.getAttribute("user");
		
		
		String old_photo_name = listing.getPhoto_large();
		String old_photo_abs_path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images" + File.separator + listing.getListing_id() + File.separator + "listing_large_image" + File.separator + old_photo_name;
		
		File old_file = new File(old_photo_abs_path);
		if(old_file.exists() && !old_file.isDirectory()) { 
			old_file.delete();
		}
		
		listing.setPhoto_large(request.getParameter("Photo"));
		listingdao.update_listing_photo_large(listing);
		
		String nextJSP = "/jsp_scripts/Host_Listing_Details.jsp";
		
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
