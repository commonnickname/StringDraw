package utils;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

import data.CONST;
import utilDatatypes.Point;

/*
 * Additional functions for graphics
 * 
 * 
 * 
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

	
	public static ArrayList<Point> getLinePoints(Point from, Point to) {
		ArrayList<Point> pixels = new ArrayList<Point>();
		int x1 = from.x, x2 = to.x;
		int y1 = from.y, y2 = to.y;
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;
		int err = dx - dy;

		int i = 0;
		int e2;
		while (true) {
			//System.out.println("start loop");
		    pixels.add(new Point(x1, y1));
		    i++;

		    if (x1 == x2 && y1 == y2) {
		        break;
		    }

		    e2 = err<<1;

		    if (e2 > -dy) {
		    	//System.out.println("case1");
		        err = err - dy;
		        x1 = x1 + sx;
		    }

		    if (e2 < dx) {
		    	//System.out.println("case2");
		        err = err + dx;
		        y1 = y1 + sy;
		    }
		}
		return pixels;
	}
	
	/**********************************************************************/
	
	public static BufferedImage toGrayscale(BufferedImage bi) {
		BufferedImage grayImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < bi.getWidth(); x++) {
		for(int y = 0; y < bi.getHeight(); y++) {
			int argb = bi.getRGB(x, y);
			int rgb = argb & 0x00ffffff;
			int a = (argb >> 24) & 0x000000ff;
			int r = (rgb >> 16) & 0xFF;
			int g = (rgb >> 8) & 0xFF;
			int b = (rgb & 0xFF);
			int c = (int) Math.min(255, 0.2126*r + 0.7152*g + 0.0722*b);
			grayImage.setRGB(x, y, (a << 24) | (c << 16) | (c << 8) | c);	
		}}
		
		return grayImage;
	}
	
	/***********************************************************************/
	public static int changeOpacity(int argb, int opacity) {
		int a = (argb >> 24) & 0x000000ff;
		int rgb = argb & 0x00ffffff;
		a = opacity;
		argb = (a << 24) | rgb;
		return argb;
	}
	
	/**********************************************************************/
	//resizes a BufferedImage to fit target dimension, and preserves ratio
	public static BufferedImage fitBufferedImage(BufferedImage sourceImg, Dimension targetDim) { 
		int w = sourceImg.getWidth(), h = sourceImg.getHeight();
		if (targetDim.width >= w && targetDim.height >= h) return sourceImg;
		
		int newW, newH = 0;
		if (w >= h) {
			newW = CONST.CANVAS_W;
			float scalar = ((float)CONST.CANVAS_W)/w;
			newH = (int)(((float)h)*scalar);
		}else {
			newH = CONST.CANVAS_H;
			float scalar = ((float)CONST.CANVAS_H)/h;
			newW = (int)(((float)w)*scalar);
		}
	    Image tmp = sourceImg.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage resultImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = resultImage.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return resultImage;
	}  
	/************************************************************************/
	public static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	/************************************************************************/
	public static BufferedImage ensureRGB(BufferedImage sourceImg) {
		BufferedImage convertedImg = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), 
				 									   BufferedImage.TYPE_INT_RGB);
	    convertedImg.getGraphics().drawImage(sourceImg, 0, 0, null);
	    return convertedImg;
	    
	}
	
	/************************************************************************/
	public static BufferedImage ensureARGB(BufferedImage sourceImg) {
		BufferedImage convertedImg = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), 
													   BufferedImage.TYPE_INT_ARGB);
	    convertedImg.getGraphics().drawImage(sourceImg, 0, 0, null);
	    return  convertedImg;
	    
	}
	
	/************************************************************************/
	public static int[][] setupInt255BlueDArray(BufferedImage bi) {
		int[][] newArr = new int[bi.getWidth()][bi.getHeight()];
		for (int x = 0; x < bi.getWidth(); x++) {
		for (int y = 0; y < bi.getHeight(); y++) {
				newArr[x][y] = 255 - (bi.getRGB(x, y) & 0xFF);
		}}
		return newArr;
	}
	
	
	
	/************************************************************************/
	public static float[][] int255DArrToFloat(int[][] int255DArr) {
		float[][] newArr = new float[int255DArr.length][int255DArr[0].length];
		for (int x = 0; x < int255DArr.length; x++) {
		for (int y = 0; y < int255DArr[0].length; y++) {
			newArr[x][y] = (float)int255DArr[x][y]/255;
		}}
		return newArr;
	}
	
	/*************************************************************************/
	public static float[][] float2DArrayToPower(float[][] sourceArr, double exponent) {
		float[][] newArr = new float[sourceArr.length][sourceArr[0].length];
		for (int x = 0; x < sourceArr.length; x++) {
		for (int y = 0; y < sourceArr[0].length; y++) {
			newArr[x][y] = (float)Math.pow((double)sourceArr[x][y], exponent);
		}}
		return newArr;
	}
	
}
