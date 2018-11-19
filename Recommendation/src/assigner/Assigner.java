package assigner;

import java.util.ArrayList;

import cluster_structures.Assign_pair;
import cluster_structures.Triangular_matrix;
import other.New_centers;

public abstract class Assigner {

	protected Triangular_matrix D_matrix;				// the distances between all points

	protected int[] Centers;							// the Centers that I will pick (0,...,N-1)
	
	protected int K;									// the numbers of the above centers
	
	protected int N;									// the number of points to be clustered

	protected ArrayList<Assign_pair>[] Assigned_points;	// "K" lists that represent the clusters, 1 for each center

	
	
	abstract public double assign();

	public Assigner(int[] centers, Triangular_matrix d_matrix, int k, int n, ArrayList<Assign_pair>[] assignedPoints) {
		Centers = centers;
		D_matrix = d_matrix;
		K = k;
		N = n;
		Assigned_points = assignedPoints;
	}
	
	public New_centers assignBrute(int i, int center, int center2) {
		double min  = 0.0;												// just an initial value
		double min2 = 0.0;												// just an initial value
		New_centers new_centers = new New_centers(center,center2);
		for(int k = 0; k < K; k++) {									// iterate over centers to find the min distance from the point
			double temp = D_matrix.distance(i,Centers[k]);				// get the distance
			if( new_centers.getCenter1() == -1 || temp < min ) {		// if it is smaller than the so-far-best
				min = temp;												// update the distance
				new_centers.setCenter2(new_centers.getCenter1());		// the best
				new_centers.setCenter1(k);								// and the second best
			}
			else if( new_centers.getCenter2() == -1 || temp < min2 ) {
				min2 = temp;
				new_centers.setCenter2(k);
			}
		}
		new_centers.setMin1(min);
		new_centers.setMin2(min2);
		//System.out.println("Center1 = " + new_centers.getCenter1() + " - dist = " + min + " , Center2 = " + new_centers.getCenter2() + " - dist2 = " + min2);
		return new_centers;
	}
	
	
	public void flush() {
		for(int k = 0; k < K; k++) {						// before each assignment
			Assigned_points[k].clear();
		}
	}
	
}
