package initializer;

import java.util.Arrays;

import cluster_structures.Triangular_matrix;
import other.Double_index;
import other.Double_index_comparator;

public class Park_Jun extends Initializer {

	public Park_Jun( int[] centers , Triangular_matrix dPtr, int k, int n ) {
		super(centers,dPtr,k,n);
	}
	
	
	@Override
	public void initialise() {
		
		double[] v = new double[N];

		double sumT = 0.0;
		for(int j = 0; j < N; j++){
			for(int t = 0; t < N; t++){
				sumT += D_matrix.distance(j,t);
			}
		}

		for(int i = 0; i < N; i++){
			v[i] = 0.0;
			for(int j = 0; j < N; j++){
				v[i] += D_matrix.distance(i,j) / sumT;
			}
		}

		// next, we sort v[] table and pick the 4 points that correspond to the 4 smallest values
		// in order to maintain the matching between "point i" and "v[i]", a table of indexes keeps track
		// of the changes that occur during the sort
		int[] indexes = new int[N];
		for(int i = 0; i < N; i++){
			indexes[i] = i;				// initially, "point i" matches the value "v[i]"
		}
		
		Double_index[] d_index_array = new Double_index[N];
		for (int i=0; i < N; i++) {
			d_index_array[i] = new Double_index(v[i],indexes[i]);
		}
				
		Arrays.sort(d_index_array, new Double_index_comparator());

		for(int i = 0; i < K; i++){
			Centers[i] = d_index_array[i].getIndex();	// store the indexes of the 4 points in the centers table
			System.out.println("Initial Center["+ i + "] = " + Centers[i]);
		}
		
	}
	
}


