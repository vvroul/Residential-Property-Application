package servlets;

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

/**
 * Servlet implementation class Register_host
 */
@WebServlet("/Register_host")
public class Register_host extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register_host() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
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
		
		UserDAO userdao = new UserDAOImpl(false);
	
		userdao.insert_user_as_host(user);
		
		userdao.update_user_admin_level(user.getUser_id(),3);
		
		userdao.update_user_verified(user.getUser_id(),false);
		
		String nextJSP = "/jsp_scripts/Register_Authentication.jsp";
		
		session.setAttribute("user", user);
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
