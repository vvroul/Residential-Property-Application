package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.PhotoDAO;
import dao.PhotoDAOImpl;
import entities.Listing;
import entities.Photo;
import entities.User;

/**
 * Servlet implementation class Upload_listing_photo
 */
@WebServlet("/Upload_listing_photo")
public class Upload_listing_photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload_listing_photo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		PhotoDAO photodao = new PhotoDAOImpl(false);
		
		User user = (User)session.getAttribute("user");
		Listing listing = (Listing)session.getAttribute("listing");
		@SuppressWarnings("unchecked")
		ArrayList<Photo> photos = (ArrayList<Photo>)session.getAttribute("photos");
		Photo photo = new Photo();
		photo.setListing_id(listing.getListing_id());
		
		String error = null;
		
		String path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images" + File.separator + listing.getListing_id();
		
        try {
        	
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            for(FileItem item : multiparts) {
                if(!item.isFormField()) {
                    String name = new File(item.getName()).getName();
                    String file_name_path = path + File.separator + name;
                    item.write( new File(file_name_path));
                    photo.setPhoto(name);
                    photodao.insert_photo(photo);
                    photos.add(photo);
                    error = "File Uploaded Successfully";
                }
            }
       
           
        } 
        catch (Exception ex) {
        	error = "File Upload Failed due to " + ex;
        }          
         
        session.setAttribute("upload_error", error);
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
