package other;

import java.util.Comparator;

import entities.Listing;

public class Listing_comparator implements Comparator<Listing> {
	
    @Override
    public int compare(Listing listing1, Listing listing2) {
    	return Integer.compare(listing1.getPrice(),listing2.getPrice());
    }
}
