package utils.datatypes;

import java.util.ArrayList;
import java.util.Objects;

import utils.MUtils;


public class CLine {
	public final Pin from;
	public final Pin to;
	public ArrayList<Pixel> pixels;
	public float fitness;

	
	public CLine(Pin from, Pin to){
		this.from = from;
		this.to = to;
		pixels = new ArrayList<Pixel>();
		updateFitness();
	}
	
	public CLine(Pin from, Pin to, ArrayList<Pixel> pixels){
		this.from = from;
		this.to = to;
		this.pixels = pixels;
		updateFitness();
	}
	
	public void setPixelList(ArrayList<Pixel> pixels) {
		this.pixels = pixels;
		updateFitness();
	}
	
	public void createPixelList(Pixel[][] sourceArray) {
		pixels = new ArrayList<Pixel>();
		for(Point p: MUtils.getLinePoints(from.coords, to.coords)) {
			pixels.add(sourceArray[p.x][p.y]);
		}
		updateFitness();
	}
	
	public int getLength() { return pixels.size(); }
	
	public void updateFitness() {
		fitness = 0;
		for(Pixel p: pixels) fitness += p.fitnessVal;
		fitness /= this.getLength();
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

