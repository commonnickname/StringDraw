package utilDatatypes;
import java.util.ArrayList;

import utilDatatypes.Point;

public class Pin extends Point{
	
	public ArrayList<CLine> lineList;

	public Pin(int x, int y) {
		super(x, y);
		lineList = new ArrayList<CLine>();
	}
	
	public Pin(Point p) {
		super(p.x, p.y);
		lineList = new ArrayList<CLine>();
	}
	
	public Boolean equals(Pin p) {
		if (this.x == p.x && this.y == p.y) return true;
		return false;
	}
}
