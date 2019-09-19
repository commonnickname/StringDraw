package algorithms.variants.greedy;

import algorithms.DrawingAlgorithm;
import algorithms.variants.Implementable;
import utils.datatypes.CLine;
import utils.datatypes.Pin;
import utils.CUtils;
import utils.datatypes.CEnums.Action;

public class GreedyLocal extends Implementable implements Greedy{
	public Pin p1 = null, p2 = null;
	public GreedyLocal(DrawingAlgorithm da) { 
		super(da); 
		da.action = Action.FIRST;
	}
	
	@Override
	public CLine getLine(){
		CLine line = null;
		switch(da.action) {
		case FIRST: {
			da.action = Action.NEXT;
			line = getFirstLine();
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
	
	public CLine getFirstLine() {
		CLine line = CUtils.selectLineFromPinList(da.algData.pinList);
		if(line == null || line.fitness < da.fitnessCutoff) return null;
		p1 = line.from; p2 = line.to;
		return line;
	}
	
	private CLine getNextLine() {
		CLine line = null;
		CLine line1 = CUtils.selectLineFromLineList(p1.lineList);
		CLine line2 = CUtils.selectLineFromLineList(p2.lineList);
		
		if (line1 == null) {
			if (line2 == null) return null;
			p2 = line2.to;
			line = line2;
		} else if (line2 == null) {
			p1 = line1.to;
			line = line1;
		} else if (line1.fitness >= line2.fitness){
			p1 = line1.to;
			line = line1;
		} else {
			p2 = line2.to;
			line = line2;
		}
		
		if (line.fitness < da.fitnessCutoff) return null;
		return line;
	}
	
}
