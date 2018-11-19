package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dao.BookingDAO;
import dao.BookingDAOImpl;
import entities.Booking;
import entities.Bookings;

/**
 * Servlet implementation class Xml_bookings
 */
@WebServlet("/Xml_bookings")
public class Xml_bookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String XML_PATH = "C:\\Users\\Geo\\Desktop\\";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xml_bookings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookingDAO bookingdao = new BookingDAOImpl(false);
		ArrayList<Booking> bookings = bookingdao.bookings_list();
		
		Bookings booking_list = new Bookings();
		booking_list.setBooking_list(bookings);
	
		try {
		
			File file = new File(XML_PATH + "bookings.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Bookings.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
			jaxbMarshaller.marshal(booking_list, file);
			
			
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
