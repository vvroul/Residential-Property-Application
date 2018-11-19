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

/**
 * Servlet implementation class Remove_chat_message
 */
@WebServlet("/Remove_chat_message")
public class Remove_chat_message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remove_chat_message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ChatDAO chatdao = new ChatDAOImpl(false);
		Chat chat = new Chat();
		
		chat.setChat_id(Integer.parseInt(request.getParameter("Chat_id")));
		chat.setMessage_counter(Integer.parseInt(request.getParameter("Message_counter")));
		
		chatdao.delete_chat_message(chat);
		
		ArrayList<Chat> chat_list = chatdao.find_chat_by_id(chat.getChat_id());
		Chat chat_last_entry = chatdao.find_last_message_of_chat(chat.getChat_id());
		
		String nextJSP = "/jsp_scripts/Chat_Page.jsp";
		session.setAttribute("chat_list", chat_list);
		session.setAttribute("chat_last_entry", chat_last_entry);
		
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
