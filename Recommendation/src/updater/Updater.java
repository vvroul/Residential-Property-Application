package updater;

import java.util.ArrayList;

import cluster_structures.Assign_pair;
import cluster_structures.Triangular_matrix;

public abstract class Updater {
	
	protected Triangular_matrix D_matrix;				// the distances between all points
	
	protected static double J;							// the current value fo the objectiev function

	protected int[] Centers;							// the new centroids
	
	protected int[] OldCenters;							// the old centroids
	
	protected int K;									// the numbers of the above centers

	protected ArrayList<Assign_pair>[] Assigned_points;	// the clusters
	
	protected int TimesSwapped;							// number of swaps so far
	
	protected double MinJ;								// minimum value of J so far
	
	protected boolean EndOfDay;							// if false, no swapping occurs
	
	
	
	abstract public void update(double j); 
	
	
	public Updater(Triangular_matrix d_matrix, int k, int[] centers, int[] oldCenters, ArrayList<Assign_pair>[] assigned_points) {
		D_matrix = d_matrix;
		J = 0;
		K = k;
		Centers = centers;
		OldCenters = oldCenters;
		Assigned_points = assigned_points;
		TimesSwapped = 0;
		EndOfDay = false;
		MinJ = Double.MAX_VALUE;
	}
	
	public double getJ() {
		return J;
	}
	
	public void setJ(double j) {
		J = j;
	}
	
	
	protected void keepOldCenters() {
		for(int k = 0; k < K; k++) {
			OldCenters[k] = Centers[k];
		}
	}
	
	protected double PAM_swap(int m, int t) {
		// iterate over every point
		// do it through the lists, need to know the cluster that a point belongs to

		// we don't use computeDJ() as the slides suggest
		// after a swap, the 2nd best center may change, so the algorithm does not work for CLARANS
		// also, the distances are pre-computed form the Initializer, so no time is spent in computations
		double J2 = 0.0;
		for( int cluster = 0; cluster < K; cluster++ ) {
			for (Assign_pair pair : Assigned_points[cluster]) {
				if( m == cluster ){
					J2 += D_matrix.distance(pair.getAssigned(),t);
				}
				else{
					J2 += D_matrix.distance(pair.getAssigned(),Centers[cluster]);
				}
			}
		}

		return J2;
	}
	
	protected double computeDJ(int cluster, int m, int t, Assign_pair iPair ) {
		int i = iPair.getAssigned();
		int c2 = iPair.getCenter2();

		double dist_it = D_matrix.distance(i,t);
		double dist_ic	= D_matrix.distance(i,Centers[cluster]);
		double dist_ic2 = D_matrix.distance(i,c2);

		if ( cluster == m ) {
			if( dist_it < dist_ic2 ){
				return dist_it - dist_ic;
			}
			else{
				return dist_ic2 - dist_ic;
			}
		}
		else {
			if( dist_it < dist_ic ){
				return dist_it - dist_ic;
			}
			else{
				return 0.0;
			}
		}
	}
	
	protected void swap(int m, int t, double J2) {
		if( EndOfDay ){		// if indicated to stop swapping
			return;			// return immediately
		}

		// no, swapping may still occur

		if( J2 < J ){			// if a better configuration is found
			Centers[m] = t;		// swap the center with "t"
			J = J2;				// update the objective function
			TimesSwapped++;		// another swap occured
		}

		// after "20*K" swaps occur, start checking if the algorithm is just looping endlessly

		if( TimesSwapped > 20*K && J2 == MinJ ){	// if the minimum value is found (for 2nd time!)
			EndOfDay = true;						// mark that no swap will be made, it's pointless!
		}
		else if( TimesSwapped > 20*K && J2 < MinJ ){// else, if a better configuration is still found
			MinJ = J2;								// update the "minJ"
		}
	}
	

}
