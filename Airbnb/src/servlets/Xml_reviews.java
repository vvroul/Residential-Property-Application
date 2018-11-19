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
import entities.Reviews;

/**
 * Servlet implementation class Xml_reviews
 */
@WebServlet("/Xml_reviews")
public class Xml_reviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String XML_PATH = "C:\\Users\\Geo\\Desktop\\";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xml_reviews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		
		ArrayList<Review> reviews = reviewdao.reviews_list();
		
		Reviews review_list = new Reviews();
		review_list.setReview_list(reviews);
	
		try {
		
			File file = new File(XML_PATH + "reviews.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Reviews.class);
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
