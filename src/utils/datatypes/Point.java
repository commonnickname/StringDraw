package utils.datatypes;

import java.util.Objects;

public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static int pixelDistance(Point p1, Point p2) {
		if (p1.equals(p2)) return 0;
		int hDiff = Math.abs(p1.x - p2.x);
		int vDiff = Math.abs(p1.y - p2.y);
		return hDiff >= vDiff? hDiff : vDiff;
	}
	
	public static int taxicabDistance(Point p1, Point p2) {
		if (p1.equals(p2)) return 0;
		int hDiff = Math.abs(p1.x - p2.x);
		int vDiff = Math.abs(p1.y - p2.y);
		return hDiff + vDiff;
	}
	
	public static int euclidianDistance(Point p1, Point p2) {
		if (p1.equals(p2)) return 0;
		int hDiff = Math.abs(p1.x - p2.x);
		int vDiff = Math.abs(p1.y - p2.y);
		double distance = Math.sqrt(Math.pow(hDiff, 2) + Math.pow(vDiff, 2));
		return (int)distance;
	}
	
	@Override 
	public boolean equals(Object o) {
		if (this == o) return true;
		if ((o == null) || !(o instanceof Point)) return false;
		Point p = (Point)o;
		return (x == p.x) && (y == p.y);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public String toString() {
		return "( " + this.x + " : " + this.y + " )";
	}

}
