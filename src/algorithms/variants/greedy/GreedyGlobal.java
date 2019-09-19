package algorithms.variants.greedy;

import java.util.ArrayList;

import algorithms.DrawingAlgorithm;
import algorithms.variants.Implementable;
import utils.datatypes.CLine;
import utils.CUtils;
import utils.datatypes.CEnums.Action;
import utils.helpers.ComponentDetector;
import utils.helpers.OddDetector;

public class GreedyGlobal extends Implementable implements Greedy{
	
	public GreedyGlobal(DrawingAlgorithm da) { 
		super(da); 
		da.action = Action.FIRST;
	}
	
	@Override
	public CLine getLine(){
		CLine line = null;
		
		switch (da.action) {
		case FIRST :{
			//System.out.println("FIRST");
			line = getFirstLine();
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
		} 
		default: {
			break;
		}}
		
		return line;
	}
	
	public CLine getFirstLine() {
		CLine line = CUtils.selectLineFromPinList(da.algData.pinList);
		if(line == null || line.fitness < da.fitnessCutoff) return null;
		return line;
	}
	
	private CLine getConnectingLine() {
		//System.out.println("connecting components");
		ComponentDetector componentDetector = new ComponentDetector(da.fullLinesList, da.algData);
		ArrayList<CLine> lines = componentDetector.createCandidateLines();
		for(CLine line: lines) line.createPixelList(da.algData.pixels);
		return CUtils.selectLineFromLineList(lines);
	}
	
	private CLine getOddConnectingLine() {
		//System.out.println("connecting odd pins");
		OddDetector oddDetector = new OddDetector(da.fullLinesList, da.algData);
		ArrayList<CLine> lines = oddDetector.createCandidateLines();
		for(CLine line: lines) line.createPixelList(da.algData.pixels);
		return CUtils.selectLineFromLineList(lines);
	}
}