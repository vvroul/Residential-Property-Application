package servlets;

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
import other.Security;

/**
 * Servlet implementation class Update_user_password
 */
@WebServlet("/Update_user_password")
public class Update_user_password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_user_password() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		String old_password = request.getParameter("Old_password");
		String new_password = request.getParameter("New_password");
		String confirm_new_password = request.getParameter("Confirm_new_password");
		
		user.setUsername(request.getParameter("Username"));
		user.setPassword(request.getParameter("Password"));
		
		String nextJSP = null;
		
		if (!Security.verifyPassword(old_password, user.getPassword())) {
			nextJSP = "/Update_user_password.jsp";
			request.setAttribute("error_message", "wrong password");
		}
		else if (!new_password.equals(confirm_new_password)) {
			nextJSP = "/Update_user_password.jsp";
			request.setAttribute("error_message", "not same password");
		}
		else {
			
			UserDAO userdao = new UserDAOImpl(false);
			
			user.setPassword(Security.encryptPassword(new_password));
			
			userdao.update_user(user);
				
			nextJSP = "/Profile_Page.jsp";
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
