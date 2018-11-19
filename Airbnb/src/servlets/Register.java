package servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.User;
import other.Security;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String UPLOAD_DIRECTORY = "c:\\Users\\Geo\\workspace\\Airbnb\\WebContent\\images\\users";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		user.setUsername(request.getParameter("Username"));
		user.setPassword(request.getParameter("Password"));
		String confirm_password = request.getParameter("Confirm_password");
		user.setName(request.getParameter("Name"));
		user.setSurname(request.getParameter("Surname"));
		user.setEmail(request.getParameter("Email"));
		user.setContact_number(request.getParameter("Contact_number"));
		user.setAdmin_level(Integer.parseInt(request.getParameter("Admin_level")));
		
		if ( (user.getAdmin_level() == 2) || (user.getAdmin_level() == 3) ) {
			user.setHost_since(java.sql.Date.valueOf(LocalDate.now()));
			user.setHost_location(request.getParameter("Host_location"));
			user.setHost_about(request.getParameter("Host_about"));
			user.setHost_responce_time(request.getParameter("Host_responce_time"));
			user.setHost_responce_rate(0);
			user.setHost_acceptance_rate(0);
			user.setHost_is_superhost(Boolean.parseBoolean(request.getParameter("Host_is_superhost")));
			user.setHost_neighbourhood(request.getParameter("Host_neighbourhood"));
			user.setHost_listings_count(0);
			user.setHost_total_listings_count(0);
			user.setHost_verifications(request.getParameter("Host_verifications"));
			user.setHost_has_profile_pic(false);
			user.setHost_identity_verified(false);
			
		}
		
		String nextJSP = null;
		
		HttpSession session = request.getSession();
		
		
		if(!(user.getPassword()).equals(confirm_password)) {
			nextJSP = "/jsp_scripts/Register_Page.jsp";
			session.setAttribute("register_err_message", "not same password");
		}
		else {
			
			UserDAO userdao = new UserDAOImpl(false);
			User user2 = userdao.find_user_by_username(user.getUsername());
			
			if (user2 != null) {
				nextJSP = "/jsp_scripts/Register_Page.jsp";
				session.setAttribute("register_err_message", "username allready taken");
			}
			else {
				
				user.setUser_id(userdao.get_new_user_id());
				user.setPassword(Security.encryptPassword(user.getPassword()));
				
				if (user.getAdmin_level() == 1) {				// only tenant
					user.setVerified(true);
					userdao.insert_user(user);
					nextJSP = "/jsp_scripts/Welcome_Page.jsp";
					session.setAttribute("user", user);
				}
				else if ( (user.getAdmin_level() == 2) || (user.getAdmin_level() == 3) ) {	// 2 = only host , 3 = both tenant and host
					user.setVerified(false);													
					userdao.insert_user(user);
					userdao.insert_user_as_host(user);
					if (user.getAdmin_level() == 2) {
						nextJSP = "/jsp_scripts/Register_Authentication.jsp";
					}
					else {
						nextJSP = "/jsp_scripts/Welcome_Page.jsp";
					}
					session.setAttribute("user", user);
				}
			}
			
		}
		
		create_directories(user);
		response.sendRedirect(request.getContextPath() + nextJSP);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void create_directories(User user) throws SecurityException {
		
		String user_path = UPLOAD_DIRECTORY + File.separator + user.getUser_id();
		String user_user_images_path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "user_image";
		String user_listing_images_path = UPLOAD_DIRECTORY + File.separator + user.getUser_id() + File.separator + "listing_images";
		
		File user_Dir = new File(user_path);
		File user_user_images_Dir = new File(user_user_images_path);
		File user_listing_images_Dir = new File(user_listing_images_path);
		
		if (!user_Dir.exists()) {
		    System.err.println("creating directory: " + user_Dir.getName());
		    boolean result = false;

		    try{
		    	user_Dir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.err.println("DIR created");  
		    }
		}
		
		if (!user_user_images_Dir.exists()) {
		    System.err.println("creating directory: " + user_user_images_Dir.getName());
		    boolean result = false;

		    try{
		    	user_user_images_Dir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.err.println("DIR created");  
		    }
		}
		
		if (!user_listing_images_Dir.exists()) {
		    System.err.println("creating directory: " + user_listing_images_Dir.getName());
		    boolean result = false;

		    try{
		    	user_listing_images_Dir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.err.println("DIR created");  
		    }
		}
		
	}


}
