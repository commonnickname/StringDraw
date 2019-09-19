package utils.helpers;

import java.util.ArrayList;

import utils.CUtils;
import utils.datatypes.CLine;
import utils.datatypes.Pin;


public class ComponentDetector {
	private ArrayList<Pin> pinList;
	public ArrayList<ArrayList<Pin>> components;
	
	public ComponentDetector(ArrayList<CLine> linesList, AlgorithmData algData) {
		this.pinList = CUtils.returnFreshPinsWithLines(linesList);

		determineConnectedComponents();
		createCandidateLines();
	}

	
	private void determineConnectedComponents() {
		components = new ArrayList<ArrayList<Pin>>();
		int pinsAdded = 0;
		while(pinsAdded != pinList.size()) {
			ArrayList<Pin> component = new ArrayList<Pin>();
			// search for the first pin that wasn't added yet and add it
			PS: 
			for (Pin p: pinList) {
				for (ArrayList<Pin> c: components) if (c.contains(p)) continue PS; 
				component.add(p); 
				pinsAdded++; 
				break; 
			}
			
			// add pins to the current component via breadth-first search
			for(int i = 0; i < component.size(); i++) {
			for(CLine line: component.get(i).lineList) {
				if (component.contains(line.to)) continue;
				component.add(line.to); 
				pinsAdded++; 
			}}	 
			components.add(component);
		}
	}
	
	public ArrayList<CLine> createCandidateLines() {
		ArrayList<CLine> candidateLines = new ArrayList<CLine>();
		for(int i = 0; i < components.size(); i++) {
		for(int j = i + 1; j < components.size(); j++) {
			for(Pin p1: components.get(i)) {
			for(Pin p2: components.get(j)) {
				candidateLines.add(new CLine(p1, p2));
			}}
		}}
		return candidateLines;
		
	}
	

}
