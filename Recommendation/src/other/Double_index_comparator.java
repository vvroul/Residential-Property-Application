package other;

import java.util.Comparator;

public class Double_index_comparator implements Comparator<Double_index> {

	@Override
    public int compare(Double_index item1, Double_index item2) {
    	return Double.compare(item1.getV(),item2.getV());
    }
	
}
