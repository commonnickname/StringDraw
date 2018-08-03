package utilDatatypes;

import java.util.ArrayList;

import utilDatatypes.Point;

import java.util.ArrayList;

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
	
	public void setPixelList(ArrayList<Pixel> pixels) {
		this.pixels = pixels;
	}
	
	public int getLength() {
		return pixels.size();
	}
	
	public Boolean equals(CLine l){
		if (l == null) return false;
		if (from.equals(l.from) && to.equals(l.to)) return true;
		return false;
	}
	
}

