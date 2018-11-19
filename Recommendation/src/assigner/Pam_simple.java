package assigner;

import cluster_structures.Assign_pair;
import cluster_structures.Triangular_matrix;
import other.New_centers;

import java.util.ArrayList;

public class Pam_simple extends Assigner {
	
	public Pam_simple(int[] centers, Triangular_matrix d_matrix, int k, int n, ArrayList<Assign_pair>[] assigned_points) {
		super(centers,d_matrix,k,n,assigned_points);
	}

	@Override
	public double assign() {
		
		for (int i=0; i<K; i++) {
			System.out.println("Center["+ i + "] = " + Centers[i]);
		}
		
		flush();														// empty lists
		double J = 0.0;													// the objective function will be computed here
		for(int i = 0; i < N; i++) {									// for every point
			New_centers new_centers = assignBrute(i, -1, -1);
			check_centers_balance(i,new_centers);
			Assign_pair pair = new Assign_pair(i,Centers[new_centers.getCenter2()]);	// create a new pair (point, second best center)
			Assigned_points[new_centers.getCenter1()].add(pair);							// and insert it in the cluster
			J += D_matrix.distance(i,Centers[new_centers.getCenter1()]);				// update the objective Function
		}
		return J;
	}
	
	
	protected void check_centers_balance(int i , New_centers new_centers) {
		int center1 = new_centers.getCenter1();
		int center2 = new_centers.getCenter2();
		if (new_centers.getMin1() == new_centers.getMin2()) {
			if (Assigned_points[center1].size() > Assigned_points[center2].size() ) {
				new_centers.setCenter1(center2);
				new_centers.setCenter2(center1);
			}
		}
	}
	
}
