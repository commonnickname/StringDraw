package utils;

import java.util.ArrayList;

import utils.datatypes.Point;


/*
 * Mathematical utility functions
 */

public class MUtils {
	/**********************************************************************/
	public static double getEllipsePerimeter(double a, double b, int x) {
		double sum = 0;
		double h = Math.pow(a - b, 2)/Math.pow(a + b, 2);
		for (int n = 1; n < x; n++) {
			double factor1 = doubleFact(2*n - 1);
			double factor2 = Math.pow(2, n) * fact(n);
			double factor3 = Math.pow(h, n);
			double factor4 = Math.pow(2*n - 1, 2);
			
			sum += Math.pow((factor1/factor2),2)*(factor3/factor4);		
		}
		return Math.PI * (a + b) * (1 + sum);
	}
	public static long fact(int n) {
		long product = 1;
		for (int i = n; i > 0; i--) product *= i;
		return product;
	}
	public static long doubleFact(int n) {
		long product = 1;
		for (int i = n; i > 0; i -= 2) product *= i;
		return product;
	}
	
	/**********************************************************************/
	
	// source: https://stackoverflow.com/questions/6972331/how-can-i-generate-a-set-of-points-evenly-distributed-along-the-perimeter-of-an
	public static ArrayList<Point> calculatePoints(double width, double height, int n) {
		ArrayList<Point> pointList = new ArrayList<Point>();
		
		double r1 = width/2;
		double r2 = height/2;

		double theta = 0.0;
		double twoPi = Math.PI*2.0;
		double deltaTheta = 0.0001;
		double numIntegrals = Math.round(twoPi/deltaTheta);
		double circ=0.0;
		double dpt=0.0;

		    /* integrate over the elipse to get the circumference */
		for( int i=0; i < numIntegrals; i++ ) {
			theta += i*deltaTheta;
			dpt = computeDpt( r1, r2, theta);
			circ += dpt;
		}

		int nextPoint = 0;
		double run = 0.0;
		theta = 0.0;

		for( int i=0; i < numIntegrals; i++ ) {
			theta += deltaTheta;
			double subIntegral = n*run/circ;
			if( (int) subIntegral >= nextPoint ) {
				double x = r1 * Math.cos(theta);
				double y = r2 * Math.sin(theta);
				if (nextPoint < n) {
					pointList.add(new Point((int)Math.round(x), (int)Math.round(y)));
				}
				nextPoint++;
			}
			run += computeDpt(r1, r2, theta);
		}
		
		return pointList;
	}
	
	
	static double computeDpt( double r1, double r2, double theta ) {
		double dp=0.0;
		double dpt_sin = Math.pow(r1*Math.sin(theta), 2.0);
		double dpt_cos = Math.pow( r2*Math.cos(theta), 2.0);
		dp = Math.sqrt(dpt_sin + dpt_cos);

		return dp;
	}
	/**********************************************************************/
	// Source: https://stackoverflow.com/questions/8113629/simplified-bresenhams-line-algorithm-what-does-it-exactly-do
	
	public static ArrayList<Point> getLinePoints(Point from, Point to) {
		ArrayList<Point> pixels = new ArrayList<Point>();
		int x1 = from.x, x2 = to.x;
		int y1 = from.y, y2 = to.y;
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;
		int err = dx - dy;

		int e2;
		while (true) {
		    pixels.add(new Point(x1, y1));
		    if (x1 == x2 && y1 == y2) break;
		    e2 = err<<1;
		    if (e2 > -dy) { err = err - dy; x1 = x1 + sx; }
		    if (e2 < dx) { err = err + dx; y1 = y1 + sy; }
		}
		return pixels;
	}
	

	
	
}
