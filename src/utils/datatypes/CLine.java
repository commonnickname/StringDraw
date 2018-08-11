package utils.datatypes;

import java.util.ArrayList;
import java.util.Objects;

import utils.MUtils;


public class CLine {
	public final Pin from;
	public final Pin to;
	public float bestFitness;
	public ArrayList<Pixel> pixels;
	
	
	public CLine(Pin from, Pin to){
		this.from = from;
		this.to = to;
		bestFitness = 1;
		pixels = new ArrayList<Pixel>();
	}
	
	public CLine(Pin from, Pin to, ArrayList<Pixel> pixels){
		this.from = from;
		this.to = to;
		bestFitness = 1;
		this.pixels = pixels;
	}
	
	public void setPixelList(ArrayList<Pixel> pixels) {
		this.pixels = pixels;
	}
	
	public void createPixelList(Pixel[][] sourceArray) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		ArrayList<Point> coords = MUtils.getLinePoints(from.coords, to.coords);
		for(Point p: coords) pixels.add(sourceArray[p.x][p.y]);
		this.pixels = pixels;
	}
	
	public int getLength() {
		return pixels.size();
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if ((o == null) || !(o instanceof CLine)) return false;
		CLine l = (CLine)o;
		return (from.equals(l.from)) && (to.equals(l.to));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(from.coords.x, from.coords.y, to.coords.x, to.coords.y);
	}
	
	@Override
	public String toString() {
		return "( From " + from.toString() + "; To " + to.toString() + " )";
	}
	
}

