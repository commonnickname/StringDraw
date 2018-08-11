package utils;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import data.CONST;

/*
 * Graphical utility functions
 */

public class GUtils {

	
	/**********************************************************************/
	public static BufferedImage toGrayscale(BufferedImage bi, 
											double rW, double gW, double bW) {
		BufferedImage grayImage = new BufferedImage(bi.getWidth(), bi.getHeight(), 
													BufferedImage.TYPE_INT_ARGB);
		double scalar = 1.0/(rW + gW + bW);
		rW *= scalar; gW *= scalar; bW *= scalar;
		
		for(int x = 0; x < bi.getWidth(); x++) {
		for(int y = 0; y < bi.getHeight(); y++) {
			int argb = bi.getRGB(x, y);
			int rgb = argb & 0x00ffffff;
			int a = (argb >> 24) & 0x000000ff;
			int r = (rgb >> 16) & 0xFF;
			int g = (rgb >> 8) & 0xFF;
			int b = (rgb & 0xFF);
			int c = (int) Math.min(255, rW*r + gW*g + bW*b);
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
		
		float widthRatio = (float)w/targetDim.width;
		float heightRatio = (float)h/targetDim.height;
		int newW, newH = 0;
		if (widthRatio >= heightRatio) {
			//System.out.println("flag1");
			newW = CONST.CANVAS_W;
			float scalar = ((float)CONST.CANVAS_W)/w;
			newH = (int)(((float)h)*scalar);
		}else {
			//System.out.println("flag2");
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
	//source: https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
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
}
