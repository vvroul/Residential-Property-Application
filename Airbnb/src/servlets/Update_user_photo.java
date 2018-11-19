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

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.User;

/**
 * Servlet implementation class Update_user_photo
 */
@WebServlet("/Update_user_photo")
public class Update_user_photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_user_photo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		String old_photo_name = user.getPhoto();
		String old_photo_abs_path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "user_image" + File.separator + old_photo_name;
		
		File old_file = new File(old_photo_abs_path);
		if(old_file.exists() && !old_file.isDirectory()) { 
			old_file.delete();
		}
		
		UserDAO userdao = new UserDAOImpl(false);
		
		String error = null;
		
        try {
        	
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            for(FileItem item : multiparts) {
                if(!item.isFormField()) {
                    String name = new File(item.getName()).getName();
                    String file_name_path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "user_image" + File.separator + name;
                    item.write( new File(file_name_path));
                    user.setPhoto(name);
                    userdao.update_user_photo(user.getUser_id(),user.getPhoto());
                    error = "File Uploaded Successfully";
                }
            }
       
           
        } 
        catch (Exception ex) {
        	error = "File Upload Failed due to " + ex;
        }          
         
        session.setAttribute("upload_error", error);
		session.setAttribute("user", user);
		String nextJSP = "/jsp_scripts/Profile_Page.jsp";
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
