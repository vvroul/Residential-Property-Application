package updater;

import java.util.ArrayList;

import cluster_structures.Assign_pair;
import cluster_structures.Triangular_matrix;

public class Lloyds_update extends Updater {

	public Lloyds_update(Triangular_matrix d_matrix, int k, int[] centers, int[] oldCenters, ArrayList<Assign_pair>[] assigned_points) {
		super(d_matrix, k, centers, oldCenters, assigned_points);
	}
	
	public Assign_pair findt(int m) {
		double min = Double.MAX_VALUE;
		Assign_pair tPair = null;

		// iterate over the cluster of "m" center
		for (Assign_pair pair : Assigned_points[m] ) {
			double temp = 0.0;

			// iterate again over the cluster of "m" center, take all possible pairs
			// compute the sum of distances between "t" and the other points
			for (Assign_pair pair2 : Assigned_points[m] ) {
				temp += D_matrix.distance(pair2.getAssigned(),pair.getAssigned());
			}

			if( temp < min ){			// if a better sum is found
				min = temp;				// update the sum
				tPair = pair;			// keep "t"
			}
		}
		return tPair;					// return "t"
	}
	
	
	@Override
	public void update(double j) {
		J = j;
		keepOldCenters();
		for(int m = 0; m < K; m++) {		// for every cluster
			Assign_pair tPair = findt(m);	// find "t"
			if(tPair != null) {
				// if a "t" is present, swap
				double J2 = PAM_swap(m,tPair.getAssigned());
				swap(m,tPair.getAssigned(),J2);
			}
		}
	}
	

}
