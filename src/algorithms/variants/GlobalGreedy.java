package algorithms.variants;

import java.util.ArrayList;

import algorithms.DrawingAlgorithm;
import algorithms.Greedy;
import algorithms.Implementable;
import utils.datatypes.CLine;
import utils.datatypes.CEnums.Action;
import utils.objects.ComponentDetector;

public class GlobalGreedy extends Implementable implements Greedy{
	public GlobalGreedy(DrawingAlgorithm da) { super(da); }
	
	@Override
	public CLine getLine(){
		CLine line = null;
		
		switch (da.action) {
		case FIRST :{
			//System.out.println("FIRST");
			line = da.getFirstLine();
			if (line == null) {
				da.action = Action.CONNECT;
				return getLine();
			} 
			break;
		} 
		case CONNECT: {
			//System.out.println("CONNECT");
			line = getConnectingLine();
			if (line == null) {
				da.action = Action.ODD;
				return getLine();
			} 
			break;
		} 
		case ODD: {
			//System.out.println("ODD");
			line = getOddConnectingLine();
			break;
		} default: {
			break;
		}}
		
		return line;
	}
	
	private CLine getConnectingLine() {
		ComponentDetector eEL = new ComponentDetector(da.fullLinesList);
		ArrayList<CLine> lines = eEL.createCandidateLines();
		//System.out.println("candidate lines: " + lines.size());
		for(CLine line: lines) line.createPixelList(da.algData.pixels);
		return da.selectBestLine(lines);
	}
	
	private CLine getOddConnectingLine() {
		// TODO Auto-generated method stub
		return null;
	}
}