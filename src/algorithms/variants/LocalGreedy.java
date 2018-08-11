package algorithms.variants;

import algorithms.DrawingAlgorithm;
import algorithms.Greedy;
import algorithms.Implementable;
import utils.datatypes.CLine;
import utils.datatypes.CEnums.Action;

public class LocalGreedy extends Implementable implements Greedy{
	public LocalGreedy(DrawingAlgorithm da) { super(da); }
	
	@Override
	public CLine getLine(){
		CLine line = null;
		switch(da.action) {
		case FIRST: {
			da.action = Action.NEXT;
			line = da.getFirstLine();
			break;
		} 
		case NEXT: {
			line = getNextLine();
			break;
		} 
		default: {
			break;
		}}
		
		return line;
	}
	
	private CLine getNextLine() {
		CLine line1 = da.selectBestLine(da.p1.lineList);
		CLine line2 = da.selectBestLine(da.p2.lineList);
		if (line1 == null && line2 == null) return null;
		
		CLine cline = null;
		if (da.getLineFitness(line1) >= da.getLineFitness(line2)) {
			da.p1 = line1.to;
			cline = line1;
		} else {
			da.p2 = line2.to;
			cline = line2;
		}
		if (da.getLineFitness(cline) < da.fitnessCutoff) return null;
		return cline;
	}
	
}
