package cluster_structures;

public class Assign_pair {

	private	int Assigned;					// the point that is assigned in the cluster (0,...,N-1)

	private	int Center2;					// the center of the second best cluster (0,...,N-1)

	
	public Assign_pair(int assign, int center2 ){
		Assigned = assign;
		Center2 = center2;
	}


	public int getAssigned() {
		return Assigned;
	}


	public void setAssigned(int assigned) {
		Assigned = assigned;
	}


	public int getCenter2() {
		return Center2;
	}


	public void setCenter2(int center2) {
		Center2 = center2;
	}
	
	
	public void flush() {
		Assigned = -1;
		Center2 = -1;
	}

	
}
