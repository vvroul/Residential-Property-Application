package servlets;

import java.io.File;
import java.io.IOException;
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

import dao.ListingDAO;
import dao.ListingDAOImpl;
import entities.Listing;
import entities.User;

/**
 * Servlet implementation class Upload_listing_medium_photo
 */
@WebServlet("/Upload_listing_medium_photo")
public class Upload_listing_medium_photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload_listing_medium_photo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		
		User user = (User)session.getAttribute("user");
		Listing listing = (Listing)session.getAttribute("listing");
		
		String old_photo_name = listing.getPhoto_medium();
		String path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images" + File.separator + listing.getListing_id() + File.separator + "listing_medium_image";
		String old_photo_abs_path = path + File.separator + old_photo_name;
		
		File old_file = new File(old_photo_abs_path);
		if(old_file.exists() && !old_file.isDirectory()) { 
			old_file.delete();
		}
		
		String error = null;
		
		
		
        try {
        	
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            for(FileItem item : multiparts) {
                if(!item.isFormField()) {
                    String name = new File(item.getName()).getName();
                    String file_name_path = path + File.separator + name;
                    item.write( new File(file_name_path));
                    listing.setPhoto_medium(name);
                    listingdao.update_listing_photo_medium(listing);
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
