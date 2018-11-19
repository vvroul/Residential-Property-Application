package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dao.ReviewDAO;
import dao.ReviewDAOImpl;
import entities.Review;
import entities.Reviews_for_host;

/**
 * Servlet implementation class Xml_reviews_for_a_host
 */
@WebServlet("/Xml_reviews_for_a_host")
public class Xml_reviews_for_a_host extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String XML_PATH = "C:\\Users\\Geo\\Desktop\\";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xml_reviews_for_a_host() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		
		int host_id = (Integer.parseInt(request.getParameter("Host_id")));
		
		ArrayList<Review> reviews = reviewdao.find_reviews_for_a_host(host_id);
		
		Reviews_for_host review_list = new Reviews_for_host();
		review_list.setReview_list(reviews);
		review_list.setHost_id(host_id);
	
		try {
		
			File file = new File(XML_PATH + "reviews_for_" + host_id + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Reviews_for_host.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
			jaxbMarshaller.marshal(review_list, file);
			
			
			String nextJSP = "/jsp_scripts/Administrator_Home_Page.jsp";
			response.sendRedirect(request.getContextPath() + nextJSP);
		
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
