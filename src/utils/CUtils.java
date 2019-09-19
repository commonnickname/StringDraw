package utils;

import java.util.ArrayList;

import utils.datatypes.CLine;
import utils.datatypes.Pin;

/*
 * Utility Functions for custom data types
 */

public class CUtils {
	// extracts an ArrayList<Pin> of unique Pins from an ArrayList<CLine>
	public static ArrayList<Pin> extractUniquePins(ArrayList<CLine> lineList){
		ArrayList<Pin> pinList = new ArrayList<Pin>();
		for(CLine l: lineList) {
			if (!pinList.contains(l.from)) pinList.add(l.from);
			if (!pinList.contains(l.to)) pinList.add(l.to);
		}
		return pinList;
	}
	
	public static ArrayList<Pin> returnFreshPinsWithLines(ArrayList<CLine> lineList){
		ArrayList<Pin> pinList = new ArrayList<Pin>();
		for(Pin p: CUtils.extractUniquePins(lineList)) {
			pinList.add(p.returnStripped());
		}
		for(CLine l: lineList) {
			Pin p1 = null, p2 = null;
			for(Pin p: pinList) {
				if (p.equals(l.from)) p1 = p; 
				if (p.equals(l.to)) p2 = p;
			}
			p1.lineList.add(new CLine(p1, p2, l.pixels));
			p2.lineList.add(new CLine(p2, p1, l.pixels));
		}
		return pinList;
	}

	public static CLine selectLineFromPinList(ArrayList<Pin> pinList) {
		float fitness, recordFitness = -1;
		CLine rline = null;
		
		for(Pin p: pinList) {
		for(CLine line: p.lineList) {
			fitness = line.fitness;
			if(fitness <= recordFitness) continue;
			
			recordFitness = fitness;
			rline = line;
		}}
		return rline;
	}
	
	public static CLine selectLineFromLineList(ArrayList<CLine> lines) {
		float fitness, recordFitness = -1;
		CLine rline = null;
		
		for(CLine line: lines) {
			fitness = line.fitness;
			if(fitness <= recordFitness) continue;
			
			recordFitness = fitness;
			rline = line;
		}
		return rline;
	}

}
