package cluster_structures;

public class Triangular_matrix {

	protected double[][] Distances;		// the table (stored as pointer to rows)
	
	protected int N;					// the number of rows

	protected double Zero;

	public Triangular_matrix(int n, Point[] PointTable ) {
		N = n;
		Distances = new double[N][];
		System.out.println("Init rows");
		for(int i = 0; i < N; i++) {
			Distances[i] = new double[N];
			if (i % 500 == 0) {
				System.out.println("Init row = " + i);
			}
			for(int j = 0; j < i; j++) {
				Distances[i][j] = PointTable[i].distance( PointTable[j] );
			}
		}
		Zero = 0.0;
	}
	
	public double distance(int i, int j) {
		return Distances[i][j];
	}

	public void print() {
		for(int i = 0; i < N; i++){
			for(int j = 0; j < i; j++){
				System.out.println(Distances[i][j]);
			}
		}
	}
	
}
