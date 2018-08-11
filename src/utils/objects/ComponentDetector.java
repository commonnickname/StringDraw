package utils.objects;

import java.util.ArrayList;

import utils.CUtils;
import utils.datatypes.CLine;
import utils.datatypes.Pin;


public class ComponentDetector {
	private ArrayList<CLine> lineList;
	private ArrayList<Pin> pinList;
	public ArrayList<ArrayList<Pin>> components;
	
	public ComponentDetector(ArrayList<CLine> linesList) {
		this.lineList = linesList;
		
		determineAccessedPins();
		addLinesToPins();
		determineConnectedComponents();
		createCandidateLines();
		
	}

	private void determineAccessedPins() {
		//System.out.println("total lines: " + lineList.size());
		//for(CLine l: lineList) System.out.println("line: " + l.toString());
		pinList = new ArrayList<Pin>();
		for(Pin p: CUtils.extractUniquePins(lineList)) 
			pinList.add(p.returnStripped());
		
		//System.out.println("total pins: " + pinList.size());
	}
	
	private void addLinesToPins() {
		for(CLine l: lineList) {
			Pin p1 = null, p2 = null;
			for(Pin p: pinList) {
				if (p.equals(l.from)) p1 = p; 
				if (p.equals(l.to)) p2 = p;
			}
			p1.lineList.add(new CLine(p1, p2, l.pixels));
			p2.lineList.add(new CLine(p2, p1, l.pixels));
		}
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
				Pin p = component.get(i);
				for(CLine line: p.lineList) {
					if (component.contains(line.to)) continue;
					component.add(line.to); 
					pinsAdded++; 
				}
			}
			components.add(component);
		}
		
		//System.out.println(pinsAdded + " Pins added to the list of components");
		//System.out.println("components: " + components.size());
		/*
		int i = 0, j = 0;
		for (ArrayList<Pin> list: components) {
			System.out.println("component #" + (i++) + ":");
			for (Pin p: list) {
				System.out.println("    " + p.toString());
				j++;
			}
		}
		System.out.println("test: " + j);
		*/
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
