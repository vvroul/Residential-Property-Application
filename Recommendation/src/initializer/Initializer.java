package initializer;

import cluster_structures.Triangular_matrix;

public abstract class Initializer {

	protected Triangular_matrix D_matrix;		// the distances between all points

	protected int[] Centers;					// the Centers that I will pick (0,...,N-1)
	
	protected int K;							// the numbers of the above centers
	
	protected int N;							// the number of points to be clustered
	
	
	
	abstract public void initialise();
	
	public Initializer(int[] centers, Triangular_matrix d_matrix, int k, int n) {
		Centers = centers;
		D_matrix = d_matrix;
		K = k;
		N = n;
	}
	
}
