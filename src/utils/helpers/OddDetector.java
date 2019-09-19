package utils.helpers;

import java.util.ArrayList;
import java.util.ListIterator;

import utils.CUtils;
import utils.datatypes.CLine;
import utils.datatypes.Pin;

public class OddDetector {
	private ArrayList<Pin> pinList;
	
	public OddDetector(ArrayList<CLine> linesList, AlgorithmData algData) {
		this.pinList = CUtils.returnFreshPinsWithLines(linesList);
		ListIterator<Pin> iter = pinList.listIterator();
		while(iter.hasNext()){
		    if(iter.next().lineList.size() % 2 == 0) iter.remove();
		}
	}
	
	public ArrayList<CLine> createCandidateLines() {
		ArrayList<CLine> candidateLines = new ArrayList<CLine>();
		for(int i = 0; i < pinList.size(); i++) {
		for(int j = i + 1; j < pinList.size(); j++) {
			//System.out.println(pinList.get(i).toString());
			candidateLines.add(new CLine(pinList.get(i), pinList.get(j)));
		}}
		return candidateLines;
	}
}
