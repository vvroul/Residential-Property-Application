package initializer;

import java.util.Random;
import cluster_structures.Triangular_matrix;

public class Random_initialize extends Initializer {
	
	private int max;		// max value for random
	
	private int min;		// min value for random
	
	public Random_initialize(int[] centers , Triangular_matrix dPtr, int k, int n) {
		super(centers,dPtr,k,n);
	}
	
	@Override
	public void initialise() {
		Random rand = new Random();
		max = N-1;
		min = 1;
		for(int k = 0; k < K; k++){
			Centers[k] = rand.nextInt((max - min) + 1) + min;
			System.out.println("Initial Center["+ k + "] = " + Centers[k]);
		}
	}

}
