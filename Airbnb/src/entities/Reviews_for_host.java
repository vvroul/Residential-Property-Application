package entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Reviews")
@XmlAccessorType (XmlAccessType.FIELD)
public class Reviews_for_host {
	
	@XmlAttribute(name = "Host_id")
	private int Host_id;
	
	@XmlElement(name = "Review")
	private ArrayList<Review> Review_list;
	
	public int getHost_id() {
		return Host_id;
	}
	
	public void setHost_id(int host_id) {
		this.Host_id = host_id;
	}

	public ArrayList<Review> getReview_list() {
		return Review_list;
	}

	public void setReview_list(ArrayList<Review> review_list) {
		this.Review_list = review_list;
	}

}
