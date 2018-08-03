package data;
import java.util.ArrayList;

import utilDatatypes.CLine;
import utilDatatypes.Pin;
import utilDatatypes.Pixel;
import utilDatatypes.Point;
import utils.MUtils;

public class AlgorithmData {
	public int width, height;
	public float[][] imgVals;
	
	public Pixel[][] pixels;
	public ArrayList<Pin> pinList;
	
	public AlgorithmData() { }
	
	public AlgorithmData(float[][] imgVals) {
		this.width = imgVals.length;
		this.height = imgVals[0].length;
		this.imgVals = imgVals;
		
		pixels = new Pixel[width][height];
		setupPixels();
		
		pinList = new ArrayList<Pin>();
		setupPins();
		setupPinLines();
	}
	
	
	public void setupPixels() {
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			
			pixels[x][y] = new Pixel();
		}}
	}
	
	public void setupPins() {
		int hOffset = width/2; 
		int vOffset = height/2;
		int x, y;
		pinList = new ArrayList<Pin>();
		for (Point p: MUtils.calculatePoints(width, height, CONST.PINNUM)) {
			x = Math.min(width - 1, p.x + hOffset);
			y = Math.min(height - 1, p.y + vOffset);
			pinList.add(new Pin(x, y));
		}
	}
	
	public void setupPinLines() {
		
		for (int i = 0; i < pinList.size(); i++) {
		for (int j = i + 1; j < pinList.size(); j++) {
			Pin p1 = pinList.get(i);
			Pin p2 = pinList.get(j);
			
			
			CLine line1 = new CLine(p1, p2);
			CLine line2 = new CLine(p2, p1);
			ArrayList<Pixel> pixelList = new ArrayList<Pixel>();
			for (Point p: MUtils.getLinePoints((Point)p1, (Point)p2)) {
				pixelList.add(pixels[p.x][p.y]);
			}
			
			line1.setPixelList(pixelList);
			line2.setPixelList(pixelList);
			p1.lineList.add(line1);
			p2.lineList.add(line2);
		}}
	}
	
}
