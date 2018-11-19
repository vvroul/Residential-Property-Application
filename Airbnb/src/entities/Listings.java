package entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "Listings")
@XmlAccessorType (XmlAccessType.FIELD)
public class Listings {
	
	@XmlElement(name = "Listing")
	private ArrayList<Listing> Listing_list;

	public ArrayList<Listing> getListing_list() {
		return Listing_list;
	}

	public void setListing_list(ArrayList<Listing> listing_list) {
		this.Listing_list = listing_list;
	}

}
