package entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "Reviews")
@XmlAccessorType (XmlAccessType.FIELD)
public class Reviews {
	
	@XmlElement(name = "Review")
	private ArrayList<Review> Review_list;

	public ArrayList<Review> getReview_list() {
		return Review_list;
	}

	public void setReview_list(ArrayList<Review> review_list) {
		this.Review_list = review_list;
	}

}
