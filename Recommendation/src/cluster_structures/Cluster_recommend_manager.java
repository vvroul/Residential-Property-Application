package cluster_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import dao.ListingDAO;
import dao.ListingDAOImpl;
import dao.RecommendDAO;
import dao.RecommendDAOImpl;
import dao.ReviewDAO;
import dao.ReviewDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import entities.Listing;
import entities.Recommendation;
import entities.Review;
import entities.User;
import other.Double_index;
import other.Double_index_comparator;

public class Cluster_recommend_manager {
	
	protected static int MAX = 100;

	protected Point[] PointTable;				// the table of points to be clustered
	protected int NumUsers;						// the number of users
	protected int NumItems;						// the number of items
	
	protected Triangular_matrix D_matrix;		// the table of precomputed distances
	protected int K_clusters;					// the number of clusters
	protected Cluster_algorithm Algorithm;		// the algorithms to run

	protected double[] MeanRatings;				// mean rating of each user
	protected boolean[][] RealRatings;			// true for items the user rated

	protected double[][] ResultRatings;			// results of predictions

	public Cluster_recommend_manager() {
		NumUsers = 0;
		NumItems = 0;
	}
	
	
	public static void main( String[] args ) {
		
		Cluster_recommend_manager manager = new Cluster_recommend_manager();
		
		manager.run();
		
		//print_from_database();
		
		//fix_reviews_count();
		
		//fix_hosts_listing_count();
		
		
	}
	
	public void fill_point_table() {
		
		UserDAO userdao = new UserDAOImpl(false);
		ArrayList<User> users = userdao.users_list();
		ListingDAO listingao = new ListingDAOImpl(false);
		ArrayList<Listing> listings = listingao.listings_list();
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		
		// print_from_database(users,listings,reviews);
		
		NumUsers = users.size();
		NumItems = listings.size();
		
		
		// allocate tables to store the results of each metric
		ResultRatings = new double[NumUsers][];					// the ratings that will be produced

		// allocate tables to store info about each user
		RealRatings = new boolean[NumUsers][];					// the ratings he actually did
		MeanRatings = new double[NumUsers];						// the mean rating
		
		for (int i=0; i<NumUsers; i++) {
			ResultRatings[i] = new double[NumItems];
			RealRatings[i] = new boolean[NumItems];
			for (int j=0; j<NumItems; j++) {
				ResultRatings[i][j] = MAX;
				RealRatings[i][j] = false;
			}
		}
		
		
		int[] listing_ids = new int[listings.size()];
		for (int i=0; i<listings.size(); i++) {
			listing_ids[i] = listings.get(i).getListing_id();
		}
		Point.setIds(listing_ids);		// initialize listing_id table
		System.out.println("Initialized listing_ids");
		
		PointTable = new Point[users.size()];
		
		int u=0;
		
		ArrayList<Review> reviews = reviewdao.reviews_list();
		
		for (User user : users) {
			
			double[] values = new double[listings.size()];
			
			double mean = 0;
			
			for (Review review : reviews) {
				
				if (review.getReviewer_id() == user.getUser_id()) {
					
					int review_listing_id = review.getListing_id();
					
					for (int i=0; i<NumItems; i++) {
						if (review_listing_id == listing_ids[i]) {
							values[i] = review.getScore();
							RealRatings[u][i] = true;
							mean += review.getScore();
							break;
						}
					}
				}
				
			}
			
			MeanRatings[u] = mean;
			for (int i=0; i<NumItems; i++) {
				ResultRatings[u][i] -= mean;
			}
			
			PointTable[u] = new Point(user.getUsername(),user.getUser_id(),values,listings.size(),u);
			
			u += 1;
			
		}
		
		
		
		
		System.out.println("Initialized PointTable");
		
	}
	
	
	public void compute_distances() {
		
		D_matrix = new Triangular_matrix(NumUsers, PointTable);
		
		System.out.println("Initialized D_matrix");
		
		K_clusters = 13;		// Number of clusters
		
		Algorithm = new Cluster_algorithm(PointTable, D_matrix, NumUsers, K_clusters);
		
		System.out.println("Initialized Algorithm");
		
	}
	
	
	public void evaluate() {
		
		for(int k = 0; k < K_clusters; k++){
			ArrayList<Assign_pair> cluster = Algorithm.getCluster(k);
			ArrayList<Point> Neighbors = findNeighbours(cluster);
			System.out.println("FOUND NEIGHBORS FOR CLUSTER = " + k);
			for (Assign_pair pair : cluster ) {
				estimateRating(pair.getAssigned() , Neighbors);
			}
			System.out.println("DONE WITH CLUSTER = " + k);
		}
		
		System.out.println("DONE WITH CLUSTERS");
		
		find_top_users();
	}

	
	public ArrayList<Point> findNeighbours(ArrayList<Assign_pair> cluster) {

		// the list of neighbors is essentially the cluster itself
		// the cluster holds more info, need to keep what I want
		ArrayList<Point> Neighbors = new ArrayList<Point>();
		for ( Assign_pair pair : cluster ) {
			Neighbors.add(PointTable[pair.getAssigned()]);
		}

		return Neighbors;
	}
	
	
	public void estimateRating(int user , ArrayList<Point> Neighbors) {

		int neighborNum = Neighbors.size();		// get the number of neighbors this user has

		if( neighborNum == 0 ) {						// if no neighbors are found

			// set every unrated item to mean value of user ans return
			for(int item = 0; item < NumItems; item++){

				if( RealRatings[user][item] ) {
					continue;
				}

				ResultRatings[user][item] = MeanRatings[user];
			}
			return;
		}

		// now, procceed normally
		int[] neighborIndexes = new int[neighborNum];	// stores the indexes of the neighbors in the global table
		double[] neighborSim = new double[neighborNum];	// stores the similarity with the above neighbor

		// fill the above tables with the appropriate values
		for (int index=0; index<Neighbors.size(); index++) {
			int neighbor = Neighbors.get(index).getPosition();
			neighborIndexes[index] = neighbor;
			neighborSim[index] = PointTable[user].similarity( PointTable[neighbor] ); 
		}

		// now, predict the rating of the unrated items
		for(int item = 0; item < NumItems; item++){

			if( RealRatings[user][item] ){
				continue;
			}

			int count = 0;
			double sumRatings = 0.0, sumWeights = 0.0;
			for (int index = 0 ; index < neighborNum; index++ ) {

				// skip the neighbor if he is me (of course!)
				// or if he hasn't rated the item I am interested in
				if( neighborIndexes[index] == user || !RealRatings[neighborIndexes[index]][item] ) {
					continue;
				}

				count++;								// another "usefull" neighbor was found for this item
				// follow the e-class definition
				sumRatings += neighborSim[index] * ResultRatings[ neighborIndexes[index] ][item];
				sumWeights += Math.abs(neighborSim[index]);
			}

			// if no "usefull" neighbors were found
			// of if our similarity is 0 (can't divide!)
			// then set to mean rating
			if( count == 0 || sumWeights == 0.0 ){
				ResultRatings[user][item] = MeanRatings[user];
			}
			else{
				ResultRatings[user][item] = MeanRatings[user] + (sumRatings / sumWeights);
			}
		}
		
	}
	
	
	public void find_top_users() {
		
		delete_database_top_listings();			// clear table
		
		int max_top_listings = 10;
		
		int global_count = 0;
		Recommendation recommendations[] = new Recommendation[NumUsers*max_top_listings];
		
		int[] ids = Point.getIds(); 

		for(int i = 0; i < NumUsers; i++) {
			
			System.out.println("User = " + PointTable[i].getName() + " :");

			// keep the indexes of items before sorting
			Double_index[] d_index_array = new Double_index[NumItems];
			for( int j = 0; j < NumItems; j++ ) {
				d_index_array[j] = new Double_index(ResultRatings[i][j],j);
				//System.out.println("ResultRatings[" + i + "][" + j + "] = " + ResultRatings[i][j]);
			}

			Arrays.sort(d_index_array, new Double_index_comparator());
			
			
			
			for(int j=0, count = 0; ((j<NumItems) && (count<max_top_listings)); j++ ) {
				// print only predicted items
				
				if( !RealRatings[i][ d_index_array[j].getIndex() ] ) {
					//System.out.println("	Item = " + ids[d_index_array[j].getIndex()]);
					recommendations[global_count] = new Recommendation(PointTable[i].getName_id(),ids[d_index_array[j].getIndex()],count+1);
					count += 1;
					global_count += 1;
				}
				
				
			}
			
			

		}
		
		update_database_top_listings(recommendations);

	}
	
	
	public void run() {
		
		fill_point_table();												// fill tables from db
		
		compute_distances();											// measure distances between users

		Algorithm.run();												// do the clustering
		
		System.out.println("DONE WITH CLUSTERING");
		
		//Algorithm.printCenters();
		
		Algorithm.printClusters("c:\\Users\\Geo\\Desktop\\cluster_results.txt");
		
		evaluate();														// do the recommendation
		
		System.out.println("DONE WITH EVALUATION");
		
	}
	
	
	public static void delete_database_top_listings() {
		RecommendDAO recommenddao = new RecommendDAOImpl(false);
		recommenddao.delete_all();
	}
	
	
	public static void update_database_top_listings(Recommendation[] recommendations) {
		RecommendDAO recommenddao = new RecommendDAOImpl(false);
		recommenddao.insert_listings(recommendations);
	}
	
	
	public static void random_score_reviews() {
		
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		ArrayList<Review> reviews = reviewdao.reviews_list();
		
		Random rand = new Random();
		int max = 100;
		int min = 40;
		
		for (Review review : reviews) {
			review.setScore(rand.nextInt((max - min) + 1) + min);
			System.out.println("Review_id = " + review.getReview_id() + " Listing_id = " + review.getListing_id() + " Score = " + review.getScore());
		}
		
		reviewdao.update_reviews_list(reviews);
		
	}
	
	
	
	
	
	
	public static void fix_hosts_listing_count() {
		
		ListingDAO listindao = new ListingDAOImpl(false);
		ArrayList<Listing> listings = listindao.listings_list();
		UserDAO userdao = new UserDAOImpl(false);
		ArrayList<User> hosts = userdao.hosts_list();
		
		for (User host : hosts) {
			int count = 0;
			for (Listing listing : listings) {
				if (listing.getHost_id() == host.getUser_id()) {
					count += 1;
				}
			}
			host.setHost_listings_count(count);
			host.setHost_total_listings_count(count);
		}
		
		userdao.update_hosts(hosts);
	}
	
	
	
	public static void fix_reviews_count() {
		
		ListingDAO listindao = new ListingDAOImpl(false);
		ArrayList<Listing> listings = listindao.listings_list();
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		ArrayList<Review> reviews = reviewdao.reviews_list();
		
		for (Listing listing : listings) {
			int count = 0;
			int score = 0;
			for (Review review : reviews) {
				if (listing.getListing_id() == review.getListing_id()) {
					count += 1;
					score += review.getScore();
				}
			}
			listing.setReview_scores_rating(score / count);
			listing.setNumber_of_reviews(count);
		}
		
		listindao.update_listings(listings);
	}
	
	
	public static void print_from_database() {
		
		UserDAO userdao = new UserDAOImpl(false);
		ArrayList<User> users = userdao.users_list();
		ListingDAO listindao = new ListingDAOImpl(false);
		ArrayList<Listing> listings = listindao.listings_list();
		ReviewDAO reviewdao = new ReviewDAOImpl(false);
		ArrayList<Review> reviews = reviewdao.reviews_list();
		
		System.out.println("users :\n");
		for (User user : users) {
			System.out.println(user.getUser_id() + " " + user.getUsername());
		}
		
		
		System.out.println("\nlistings :\n");
		for (Listing listing : listings) {
			System.out.println(listing.getListing_id() + " " + listing.getName());
		}
		
		
		System.out.println("\nreviews :\n");
		for (Review review : reviews) {
			System.out.println("Review_id = " + review.getReview_id() + " Listing_id = " + review.getListing_id() + " Reviewer_id = " + review.getReviewer_id() + " Score = " + review.getScore());
		}
		
		System.out.println("users.size() = " + users.size());
		System.out.println("listings.size() = " + listings.size());
		System.out.println("reviews.size() = " + reviews.size());
	}
	
	
	public static void some_js() {
		ScriptEngineManager factory = new ScriptEngineManager();
	    ScriptEngine engine = factory.getEngineByName("JavaScript");
	    try {
			engine.eval("print('Hello, World')");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	public Point[] getPointTable() {
		return PointTable;
	}

	public void setPointTable(Point[] pointTable) {
		PointTable = pointTable;
	}

	public int getNumUsers() {
		return NumUsers;
	}

	public void setNumUsers(int numUsers) {
		NumUsers = numUsers;
	}

	public int getNumItems() {
		return NumItems;
	}

	public void setNumItems(int numItems) {
		NumItems = numItems;
	}

	public Triangular_matrix getD_matrix() {
		return D_matrix;
	}

	public void setD_matrix(Triangular_matrix d_matrix) {
		D_matrix = d_matrix;
	}

	public int getK_clusters() {
		return K_clusters;
	}

	public void setK_clusters(int k_clusters) {
		K_clusters = k_clusters;
	}

	public Cluster_algorithm getAlgorithm() {
		return Algorithm;
	}

	public void setAlgorithm(Cluster_algorithm algorithm) {
		Algorithm = algorithm;
	}

	public double[] getMeanRatings() {
		return MeanRatings;
	}

	public void setMeanRatings(double[] meanRatings) {
		MeanRatings = meanRatings;
	}

	public boolean[][] getRealRatings() {
		return RealRatings;
	}

	public void setRealRatings(boolean[][] realRatings) {
		RealRatings = realRatings;
	}

	public double[][] getResultRatings() {
		return ResultRatings;
	}

	public void setResultRatings(double[][] resultRatings) {
		ResultRatings = resultRatings;
	}
	

}
