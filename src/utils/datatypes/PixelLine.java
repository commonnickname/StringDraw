package utils.datatypes;

import java.util.ArrayList;

public class PixelLine {
	public int from;
	public int to;
	public ArrayList<Pixel> pixels;
	
	public PixelLine(int i, int j) {
		this.from = Math.min(i, j);
		this.to = Math.max(i, j);
	}
	
	@Override
	public String toString() {
		return "( " + from + " : " + to + " )";
	}
	
	public int getOpposite(int index) {
		if (index == from) return to;
		if (index == to) return from;
		System.out.println("Line does not start or end with " + index);
		return 0;
	}
}
