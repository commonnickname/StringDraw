package utils.objects;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.CONST;
import utils.GUtils;

public class ImageProcessor {
	public BufferedImage displayImg;
	public BufferedImage processingImg;
	public float[][] imgFloatValues;
	public boolean imageProcessed = false, correct;
	private int imgWidth, imgHeight;
	
	public ImageProcessor(String sourcePath) {
		imageProcessed = false;
		correct = true;
		
		createDisplayImg(sourcePath);
		if (correct) correct = validateDimensions(displayImg);
		else return;
		displayImg = GUtils.fitBufferedImage(displayImg, new Dimension(CONST.CANVAS_W, CONST.CANVAS_H));
		setupWidthHeight();
		displayImg = GUtils.toGrayscale(displayImg, CONST.RED_WEIGHT, CONST.GREEN_WEIGHT, CONST.RED_WEIGHT);
		displayImg = GUtils.ensureARGB(displayImg);
		processingImg = GUtils.deepCopy(displayImg);
		ovalizeDisplayImg();
		processingImg = GUtils.ensureRGB(processingImg);
		imgFloatValues = new float[imgWidth][imgHeight];
		setupImgVals();
		imageProcessed = correct;
		
	}
	
	private void setupWidthHeight() {
		this.imgWidth = displayImg.getWidth();
		this.imgHeight = displayImg.getHeight();
	}
	
	private void createDisplayImg(String sourcePath) {
		try {
			displayImg = ImageIO.read(new File(sourcePath));
			setupWidthHeight();
		} catch (IOException e) {
			correct = false;
			e.printStackTrace();
		}		
	}
	
	private Boolean validateDimensions(BufferedImage img) {
		int w = imgWidth, h = imgHeight;
		if (CONST.MIN_SIDE > h) return false;
		if (CONST.MIN_SIDE > w) return false;
		if (CONST.MAX_SIDE < h) return false;
		if (CONST.MAX_SIDE < w) return false;
		if (CONST.RATIO > (double)w/h) return false;
		if (1.0/CONST.RATIO < (double)w/h) return false;
		return true;
	}
	
	private void ovalizeDisplayImg() {
		int xmid = imgWidth/2, ymid = imgHeight/2;
		float a = xmid, b = ymid;
		int width = imgWidth - 1;
		int height = imgHeight - 1;
		for(int x = 0; x < xmid; x++) {
		int ylimit = height - (int)(b + b * Math.sqrt(1 - Math.pow(((x - a) / a), 2)));
		int fo = CONST.FILTER_OPACITY;
		for(int y = 0; y < ylimit; y++) {
			//a bit of optimisation - oval is 4-symmetric, 
			//therefore we need to calculate the position for only a single quadrant
			int newX = width - x, newY = height - y;
			try { displayImg.setRGB(x, y, GUtils.changeOpacity(displayImg.getRGB(x, y), fo)); }
			catch(ArrayIndexOutOfBoundsException e) { 
				System.out.println("case 1"); 
				}
			try { displayImg.setRGB(newX, y, GUtils.changeOpacity(displayImg.getRGB(newX, y), fo)); }
			catch(ArrayIndexOutOfBoundsException e) { 
				System.out.println("case 2"); 
				}
			try { displayImg.setRGB(x, newY, GUtils.changeOpacity(displayImg.getRGB(x, newY), fo)); }
			catch(ArrayIndexOutOfBoundsException e) { 
				System.out.println("case 3"); 
				}
			try { displayImg.setRGB(newX, newY, GUtils.changeOpacity(displayImg.getRGB(newX, newY), fo)); }
			catch(ArrayIndexOutOfBoundsException e) { 
				System.out.println("case 4"); 
				}
			
			
			
			
		}
		}
	}
	
	public void setupImgVals() {
		for (int x = 0; x < imgWidth; x++) {
		for (int y = 0; y < imgHeight; y++) {
			try {
				int tmp = 255 - (processingImg.getRGB(x, y) & 0xFF);
				imgFloatValues[x][y] = (float)tmp/255;
			}catch(ArrayIndexOutOfBoundsException e) {}
		}}
	}
	
	
}
