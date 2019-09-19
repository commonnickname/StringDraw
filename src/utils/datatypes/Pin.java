package utils.datatypes;
import java.util.ArrayList;
import java.util.Objects;

public class Pin{
	
	public ArrayList<CLine> lineList;
	public Point coords;
	
	public Pin(int x, int y) {
		coords = new Point(x, y);
		lineList = new ArrayList<CLine>();
	}
	
	public Pin(Point p) {
		coords = p;
		lineList = new ArrayList<CLine>();
	}
	
	public Pin(int x, int y, ArrayList<CLine> lines) {
		coords = new Point(x, y);
		lineList = lines;
	}
	
	public Pin(Point p, ArrayList<CLine> lines) {
		coords = p;
		lineList = lines;
	}
	
	public Pin returnStripped() {
		return new Pin(coords);
	}
	
	public CLine findConnectingLine(Pin to) {
		for(CLine line: lineList) {
			if (line.to.equals(to)) return line;
		}
		return null;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if ((o == null) || !(o instanceof Pin)) return false;
		Pin p = (Pin)o;
		return this.coords.equals(p.coords);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(coords.x, coords.y);
	}
	
	@Override
	public String toString() {
		return "Pin: " + coords.toString();
	}

}
