package utils.datatypes;

import java.util.ArrayList;

public class LineTable {
	public PixelLine[][] lines;
	public int size;
	
	public LineTable(int size) {
		this.size = size;
		setupLines();
	}
	
	public void setupLines() {
		lines = new PixelLine[size][];
		for(int i = 0; i < size; i++) {
			lines[i] = new PixelLine[size - i];
		}
		
		for(int i = 0; i < size; i++) {
		for(int j = i + 1; j < size; j++) {
			lines[i][j - i] = new PixelLine(i, j);
		}}
	}
	
	public PixelLine getLine(int x, int y) {
		if (x >= size || y >= size) {
			System.out.println("Getting ( " + x + ", " + y + " ); index out of bounds");
			return null;
		}
		if (x < y) return lines[x][y - x];
		if (x > y) return lines[y][x - y];
		System.out.println("Getting ( " + x + ", " + y + " ); line between identical pins not allowed");
		return null;
	}
	
	public ArrayList<PixelLine> getIntersctingLines(PixelLine line){
		ArrayList<PixelLine> lineList = new ArrayList<PixelLine>();
		for(int i = 0; i < line.from; i++) {
		for(int j = line.from + 1; j < line.to; j++) {
			lineList.add(getLine(i, j));
		}}
		for(int i = line.to + 1; i < size; i++) {
		for(int j = line.from + 1; j < line.to; j++) {
			lineList.add(getLine(i, j));
		}}
		for(int j = 0; j < size; j++) {
			if (j == line.from || j == line.to) continue;
			lineList.add(getLine(j, line.from));
			lineList.add(getLine(j, line.to));
		}
		lineList.add(line);
		return lineList;
	}
}
