package servlets;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Listing;
import entities.User;
import other.Security;
import other.User_comparator;
import dao.ListingDAO;
import dao.ListingDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		
		UserDAO userdao = new UserDAOImpl(false);
		
		User user = userdao.find_user_by_username(username);
		
		String nextJSP = null;
		
		HttpSession session = request.getSession();
		
		String current_page = (String)session.getAttribute("current_page");
		
		
		if ( (user == null) || (!Security.verifyPassword(password, user.getPassword())) ) {
			nextJSP = current_page;
			session.setAttribute("login_err_message","Invalid User or Incorrect Password");
		}
		else {
			
			if (user.getAdmin_level() == 4) {
				if (current_page == "/jsp_scripts/Welcome_Page.jsp") {
					nextJSP = "/jsp_scripts/Administrator_Home_Page.jsp";
				}
				else {
					nextJSP = current_page;
				}
				ArrayList<User> users_list = userdao.users_list();
				Collections.sort(users_list , new User_comparator());
				session.setAttribute("users_list", users_list);
			}
			else {
				
				ListingDAO listingdao = new ListingDAOImpl(false);
				ArrayList<Listing> recommendation_list = listingdao.recommendation_list(user.getUser_id());
				session.setAttribute("recommendation_list", recommendation_list);
				
				nextJSP = current_page;
			}
			
			if ( (user.getAdmin_level() == 2) || (user.getAdmin_level() == 3) ) {
				user = userdao.find_user_as_host(user.getUser_id());
			}
			
			
			
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
