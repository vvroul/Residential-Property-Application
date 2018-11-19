package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PhotoDAO;
import dao.PhotoDAOImpl;
import entities.Listing;
import entities.Photo;
import entities.User;

/**
 * Servlet implementation class Remove_listing_photo
 */
@WebServlet("/Remove_listing_photo")
public class Remove_listing_photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remove_listing_photo() {
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
		
		PhotoDAO photodao = new PhotoDAOImpl(false);
		Photo photo = new Photo();
		
		photo.setListing_id(listing.getListing_id());
		photo.setPhoto(request.getParameter("Photo"));
		
		
		String old_photo_name = photo.getPhoto();
		String path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images" + File.separator + listing.getListing_id();
		String old_photo_abs_path = path + File.separator + old_photo_name;
		
		File old_file = new File(old_photo_abs_path);
		if(old_file.exists() && !old_file.isDirectory()) { 
			old_file.delete();
		}
	
		photodao.delete_photo(photo);
		
		ArrayList<Photo> photos = photodao.find_photos_of_a_listing(listing.getListing_id());
		
		String nextJSP = "/jsp_scripts/Host_Listing_Details.jsp";
		session.setAttribute("photos", photos);
		
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
