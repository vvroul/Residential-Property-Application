package servlets;

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
import entities.Photo;

/**
 * Servlet implementation class Add_listing_url_photo
 */
@WebServlet("/Add_listing_url_photo")
public class Add_listing_url_photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_listing_url_photo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		PhotoDAO photodao = new PhotoDAOImpl(false);
		Photo photo = new Photo();
		
		photo.setListing_id(Integer.parseInt(request.getParameter("Listing_id")));
		photo.setPhoto(request.getParameter("Photo"));
		
		photodao.insert_photo(photo);
		
		@SuppressWarnings("unchecked")
		ArrayList<Photo> photos = (ArrayList<Photo>)(session.getAttribute("photos"));
		photos.add(photo);
		
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
