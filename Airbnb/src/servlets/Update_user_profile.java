package servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.User;

/**
 * Servlet implementation class Update_user_profile
 */
@WebServlet("/Update_user_profile")
public class Update_user_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_user_profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		user.setUsername(request.getParameter("Username"));
		user.setName(request.getParameter("Name"));
		user.setSurname(request.getParameter("Surname"));
		user.setEmail(request.getParameter("Email"));
		user.setContact_number(request.getParameter("Contact_number"));
		
		if ( (request.getParameter("Photo") != null) && (!request.getParameter("Photo").equals(user.getPhoto())) ) {
			String old_photo_name = user.getPhoto();
			String old_photo_abs_path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "user_image" + File.separator + old_photo_name;
			
			File old_file = new File(old_photo_abs_path);
			if(old_file.exists() && !old_file.isDirectory()) { 
				old_file.delete();
			}
		}
		user.setPhoto(request.getParameter("Photo"));
		
		if ( (user.getAdmin_level() == 2) || (user.getAdmin_level() == 3) ) {
			user.setHost_location(request.getParameter("Host_location"));
			user.setHost_about(request.getParameter("Host_about"));
			user.setHost_responce_time(request.getParameter("Host_responce_time"));
			user.setHost_is_superhost(Boolean.parseBoolean(request.getParameter("Host_is_superhost")));
			user.setHost_neighbourhood(request.getParameter("Host_neighbourhood"));
			user.setHost_verifications(request.getParameter("Host_verifications"));
			if ( (user.getPhoto() == null) || (user.getPhoto() == "") ) {
				user.setHost_has_profile_pic(false);
			}
			else {
				user.setHost_has_profile_pic(true);
			}
		}
		
		String nextJSP = null;
		
		UserDAO userdao = new UserDAOImpl(false);
		User user2 = userdao.find_user_with_same_username(user.getUsername(),user.getUser_id());
		
		if (user2 != null) {
			nextJSP = "/jsp_scripts/Profile_Page.jsp";
			nextJSP = "/Ivalid_User.jsp";
			request.setAttribute("error_message", "username allready taken");
		}
		else {
			
			userdao.update_user(user);
			
			if ( (user.getAdmin_level() == 2) || (user.getAdmin_level() == 3) ) {
				userdao.update_user_as_host(user);
			}
			
			nextJSP = "/jsp_scripts/Profile_Page.jsp";
			session.setAttribute("user", user);
				
			
		}
	
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
