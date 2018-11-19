package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChatDAO;
import dao.ChatDAOImpl;
import entities.Chat;
import entities.Listing;
import entities.User;

/**
 * Servlet implementation class Chat_new_message
 */
@WebServlet("/Chat_new_message")
public class Chat_new_message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chat_new_message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		Chat chat_last_entry = (Chat)session.getAttribute("chat_last_entry");
		User user2 = (User)session.getAttribute("user2");
		Listing listing = (Listing)(session.getAttribute("listing"));
		
		ChatDAO chatdao = new ChatDAOImpl(false);
		Chat chat_new_entry = new Chat();
		
		if (chat_last_entry == null) {
			chat_new_entry.setChat_id(chatdao.get_new_chat_id());
			chat_new_entry.setMessage_counter(1);
			chat_new_entry.setUser_id(user.getUser_id());
			chat_new_entry.setHost_id(user2.getUser_id());
		}
		else {
			chat_new_entry.setChat_id(chat_last_entry.getChat_id());
			chat_new_entry.setMessage_counter(chat_last_entry.getMessage_counter() + 1);
			chat_new_entry.setUser_id(chat_last_entry.getUser_id());
			chat_new_entry.setHost_id(chat_last_entry.getHost_id());
		}
		chat_new_entry.setListing_id(listing.getListing_id());
		chat_new_entry.setWho_wrote_it(user.getUser_id());
		chat_new_entry.setMessage(request.getParameter("new_message"));
		chat_new_entry.setSeen(false);
		
		chatdao.insert_chat_message(chat_new_entry);
		
		@SuppressWarnings("unchecked")
		ArrayList<Chat> chat_list = (ArrayList<Chat>)session.getAttribute("chat_list");
		chat_list.add(chat_new_entry);
		
		String nextJSP = "/jsp_scripts/Chat_Page.jsp";
		session.setAttribute("chat_list", chat_list);
		session.setAttribute("chat_last_entry", chat_new_entry);
		
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
