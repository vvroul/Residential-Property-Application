package cluster_structures;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import assigner.Assigner;
import assigner.Pam_simple;
import initializer.Initializer;
import initializer.Park_Jun;
import updater.Lloyds_update;
import updater.Updater;

public class Cluster_algorithm {

	protected Triangular_matrix D_matrix;				// the matrix of distances between points
	protected Point[] PointTable;						// the "global" table of points
	protected int N;									// the total number of points
	protected int[] Centers;							// the newly computed centers
	protected int[] OldCenters;							// the previously computed centers, used to check if changes occur
	protected int K;									// the number of centers
	protected double J;									// the value of the objective function
	protected ArrayList<Assign_pair>[] AssignedPoints;	// "K" lists that represent the clusters, 1 for each center
	protected Initializer Init;							// Initializer object
	protected Assigner Assign;							// Assigner object
	protected Updater Update;							// Updater object
	

	public Triangular_matrix getD_matrix() {
		return D_matrix;
	}
	public void setD_matrix(Triangular_matrix d_matrix) {
		D_matrix = d_matrix;
	}
	public Point[] getPointTable() {
		return PointTable;
	}
	public void setPointTable(Point[] pointTable) {
		PointTable = pointTable;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	public int[] getCenters() {
		return Centers;
	}
	public void setCenters(int[] centers) {
		Centers = centers;
	}
	public int[] getOldCenters() {
		return OldCenters;
	}
	public void setOldCenters(int[] oldCenters) {
		OldCenters = oldCenters;
	}
	public int getK() {
		return K;
	}
	public void setK(int k) {
		K = k;
	}
	public ArrayList<Assign_pair>[] getAssignedPoints() {
		return AssignedPoints;
	}
	public void setAssignedPoints(ArrayList<Assign_pair>[] assignedPoints) {
		AssignedPoints = assignedPoints;
	}
	public Initializer getInit() {
		return Init;
	}
	public void setInit(Initializer init) {
		Init = init;
	}
	public Assigner getAssign() {
		return Assign;
	}
	public void setAssign(Assigner assign) {
		Assign = assign;
	}
	public Updater getUpdate() {
		return Update;
	}
	public void setUpdate(Updater update) {
		Update = update;
	}
	public void setJ(double j) {
		J = j;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Cluster_algorithm(Point[] pointTable, Triangular_matrix d_matrix, int n, int k) {
		
		N = n;															// keep the number of points
		PointTable = pointTable;										// the table is filled by the caller, just keep a reference to it
		D_matrix = d_matrix;											// the distances are also computed by the caller
		K = k;															// keep the number of clusters
		Centers = new int[K];											// allocate space for the "K" centers
		OldCenters = new int[K];										// also for the previous centers
		AssignedPoints = (ArrayList<Assign_pair>[])new ArrayList[K];	// "K" clusters in form of lists
		
		for (int i=0; i<K; i++) {
			AssignedPoints[i] = new ArrayList<Assign_pair>();			// each cluster holds its current assigned pairs
		}
		for(int i=0; i<K; i++) {
			OldCenters[i] = -1;						// initially, no old centers are present
		}

		// pick an initializer
		//Init = new Random_initialize(Centers, D_matrix, K, N);
		Init = new Park_Jun(Centers, D_matrix, K, N);

		// pick an assigner
		Assign = new Pam_simple(Centers, D_matrix, K, N, AssignedPoints);
		
		// pick an updater
		Update = new Lloyds_update(D_matrix, K, Centers, OldCenters, AssignedPoints);
		
	}
	
	
	public void run() {					// run the algorithm
		Init.initialise();				// initialise the clusters
		do {							// repeat
			System.out.println("loop");
			J = Assign.assign();		// assign the points to clusters
			Update.update(J);			// update the centers
		} while( changes() );			// while changes occur in the centers
	}
	
	
	public boolean changes() {					// check if changes occures after the update step
		
		for(int k = 0; k < K; k++){						// for every new center

			boolean found = false;							// initially, not found
			for(int oldk = 0; oldk < K; oldk++){			// search the old clusters
				if( OldCenters[oldk] == Centers[k] ){		// if found
					found = true;							// keep it
				}
			}

			if(!found){										// if ont found
				return true;								// changes occured
			}

		}
		return false;
		
	}
	
	
	
	

	public void printCenters() {		// print the centroids
		for(int k=0; k<K; k++) {
			String message = new String();
			message = "CLUSTER-" + (k) + " , Value-" + Centers[k] + " , items-" + AssignedPoints[k].size() + " { ";
			int lenght = AssignedPoints[k].size();
			int count = 0;
			for (Assign_pair pair : AssignedPoints[k]) {
				message += PointTable[pair.getAssigned()].getName();
				if( count < lenght ){
					message += ", ";
				}
			}
			message += " }" + '\n';
			System.out.println(message);
		}
		
	}
	
	public void printClusters(String filename) {	// print the whole cluster
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    for(int k = 0; k < K; k++) {
		    	int lenght = AssignedPoints[k].size();
				int count = 0;
				writer.print("CLUSTER-" + (k) + " , Value-" + Centers[k] + " , items-" + AssignedPoints[k].size() + " { ");
				for (Assign_pair pair : AssignedPoints[k]) {
					writer.print(PointTable[pair.getAssigned()].getName());
					if( count < lenght ){
						writer.print(", ");
					}
				}
				writer.print(" }\n");
			}
		    writer.close();
		} catch (IOException e) {
		   System.err.println("IOEcxeption - printClusters");
		}
		
	}

	public ArrayList<Assign_pair> getCluster(int k) {
		return AssignedPoints[k];
	}
	
	public double avgDistFromCluster(int i , int center) {	// used in Silhouette computation
		int k = -1;
		// find the index of "center" int the "Centers" table
		for(k = 0; k < K; k++){
			if( Centers[k] == center ) break;
		}

		double sum = 0.0;
		// iterate over the aboce cluster, compute the sum
		for (Assign_pair pair : AssignedPoints[k]) {
			sum += D_matrix.distance(i,pair.getAssigned());
		}

		return sum / AssignedPoints[k].size();	// return mean value
	}
	
}
