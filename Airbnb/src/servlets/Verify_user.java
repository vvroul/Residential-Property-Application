package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import entities.User;
import other.User_comparator;

/**
 * Servlet implementation class Verify_user
 */
@WebServlet("/Verify_user")
public class Verify_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verify_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_id = Integer.parseInt(request.getParameter("User_id"));
		
		UserDAO userdao = new UserDAOImpl(false);
		
		userdao.update_user_verified(user_id,true);
		
		String nextJSP = "/jsp_scripts/Administrator_Home_Page.jsp";
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<User> users_list = (ArrayList<User>)session.getAttribute("users_list");
		
		
		for (Iterator<User> it = users_list.iterator(); it.hasNext(); ) {
			User user = it.next();
			if (user.getUser_id() == user_id) {
				user.setVerified(true);
				break;
			}
		}
		
		Collections.sort(users_list , new User_comparator());
		
		session.setAttribute("users_list", users_list);
		
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
