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

import dao.ListingDAO;
import dao.ListingDAOImpl;
import entities.Listing;
import entities.Listings;

/**
 * Servlet implementation class Xml_listings
 */
@WebServlet("/Xml_listings")
public class Xml_listings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String XML_PATH = "C:\\Users\\Geo\\Desktop\\";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Xml_listings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ListingDAO listingdao = new ListingDAOImpl(false);
		ArrayList<Listing> listings = listingdao.listings_list();
		
		Listings listing_list = new Listings();
		listing_list.setListing_list(listings);
	
		try {
		
			File file = new File(XML_PATH + "listings.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Listings.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
			jaxbMarshaller.marshal(listing_list, file);
			
			
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
