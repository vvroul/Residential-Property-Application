package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChatDAO;
import dao.ChatDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Chat;
import entities.User;
import other.Chat_comparator;

/**
 * Servlet implementation class Find_chat
 */
@WebServlet("/Find_chat")
public class Find_chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Find_chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		int user2_id = Integer.parseInt(request.getParameter("User2_id"));
		int listing_id = Integer.parseInt(request.getParameter("Listing_id"));
		String chat_previous_page = request.getParameter("Chat_previous_page");
		
		ChatDAO chatdao = new ChatDAOImpl(false);
		ArrayList<Chat> chat_list = chatdao.find_chat_of_listing_by_users(listing_id , user.getUser_id(), user2_id);
		
		int chat_id = -1;
		for (Chat chat : chat_list) {
			chat_id = chat.getChat_id();
			break;
		}
		Chat chat_last_entry = chatdao.find_last_message_of_chat(chat_id);
		
		
		// check the unseen messages , the other user , sent to you
		for (Chat chat : chat_list) {
			if ( (!chat.getSeen()) && (chat.getWho_wrote_it() == user2_id) ) {
				chat.setSeen(true);
				chatdao.update_chat_seen(chat);
				ArrayList<Chat> chats = chatdao.find_chats_of_listing(listing_id);
				Collections.sort(chats,new Chat_comparator(user.getUser_id()));
				session.setAttribute("chats",chats);
			}
		}
		
		
		UserDAO userdao = new UserDAOImpl(false);
		User user2 = userdao.find_user(user2_id);
		
		String nextJSP = "/jsp_scripts/Chat_Page.jsp";
		session.setAttribute("chat_list", chat_list);
		session.setAttribute("chat_last_entry", chat_last_entry);
		session.setAttribute("user2", user2);
		session.setAttribute("chat_previous_page", chat_previous_page);
		
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
