package cluster_structures;

import java.util.Arrays;

public class Point {

	protected String Name;						// a human-readable representattion of the point
	private int Name_id;
	protected double[] Values;					// the co-ordinates of a point can be stored in an array
	protected static int[] Ids;					// id of each element 
	protected int Dimension;					// the actual number of dimensions a point needs
	protected int Position;						// position in PointTable which is a ArrayList<Point>
	
	protected static double C;


	public Point(String name, int name_id, double[] values, int dimension, int position) {
		Name = name;	
		setName_id(name_id);
		Dimension = dimension;									
		Values = values;	
		Position = position;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double[] getValues() {
		return Values;
	}
	public void setValues(double[] values) {
		Values = values;
	}
	public static int[] getIds() {
		return Ids;
	}
	public static void setIds(int[] ids) {
		Ids = ids;
	}
	public int getDimension() {
		return Dimension;
	}
	public void setDimension(int dimension) {
		Dimension = dimension;
	}
	public int getPosition() {
		return Position;
	}
	public void setPosition(int position) {
		Position = position;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Dimension != other.Dimension)
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (!Arrays.equals(Values, other.Values))
			return false;
		return true;
	}
	
	
	public double multiply(Point p) {
		double[] P_values = p.getValues();						
		double innerProduct = 0;
		for(int i=0; i < Dimension; i++) {				// iterate over the arrays
			// mathematical definition
			innerProduct += Values[i] * P_values[i];
		}
		return innerProduct;	
	}
	
	
	double distance(Point p){
		double[] P_values = p.getValues();
		double distance = 0.0;								// the distance is gradually builh here
		for( int i=0; i < Dimension; i++) {
			double diff = ( Values[i] - P_values[i] );
			distance += diff * diff;						// euclidean norm
		}
		return distance;
	}

	
	double similarity(Point p){
		return 1.0 / ( distance(p) + 1.0  );			// compute the distance (in [0,2] )
	}
	

	public double multiplyDouble(double R, double times) {
			return R * times;
	}

	public boolean inRange(Point p, double R) {
			return distance(p) < C * R;
	}

	public static void setC(double c) {
			C = c;
	}
	
	
	public void print() {
		for (int i=0; i < Dimension; i++) {
			if (Values[i] != 0) {
				System.out.println(Name + " - " + Ids[i] + " - " + Values[i]);
			}
		}
	}

	public int getName_id() {
		return Name_id;
	}

	public void setName_id(int name_id) {
		Name_id = name_id;
	}


}
