package other;

public class New_centers {
	
	private int Center1;
	private int Center2;
	private double min1;
	private double min2;
	
	public New_centers(int center1 , int center2) {
		Center1 = center1;
		Center2 = center2;
		min1 = 0;
		min2 = 0;
	}

	public int getCenter1() {
		return Center1;
	}

	public void setCenter1(int center1) {
		Center1 = center1;
	}

	public int getCenter2() {
		return Center2;
	}

	public void setCenter2(int center2) {
		Center2 = center2;
	}
	
	public double getMin1() {
		return min1;
	}

	public void setMin1(double min1) {
		this.min1 = min1;
	}

	public double getMin2() {
		return min2;
	}

	public void setMin2(double min2) {
		this.min2 = min2;
	}
	
	public void print() {
		System.out.println("Center1 = " + Center1 + " , Center2 = " + Center2);
	}

	

}
