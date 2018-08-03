package algorithms;
import java.util.ListIterator;

import utilDatatypes.CLine;
import utilDatatypes.Pin;
import utilDatatypes.Pixel;

public class GlobalNaiveAlgorithm extends DrawingAlgorithm{
	public static String name = "Global Naive";
	
	public GlobalNaiveAlgorithm() {
		super();
		
	}

	@Override
	public CLine getLine(){
		CLine line = getFirstLine();
		if (line == null) epilogue();
		return line;
	}
	private void epilogue() {
		algData.setupPins();
		algData.setupPinLines();
	}
	protected void specificFunctionality() {
		fitnessCutoff = params.cutoff;
		setupFitnessVals();
	}
	
	protected void setupFitnessVals() {
		for (int x = 0; x < algData.width; x++) {
		for (int y = 0; y < algData.height; y++) {
			algData.pixels[x][y].fitnessVal = algData.pixels[x][y].targetVal;
		}}
	}
	
	protected CLine getFirstLine() {
		CLine cline = greedySelect();
		//System.out.println("flag1");
		if(cline == null || getLineFitness(cline) < fitnessCutoff) return null;
		//System.out.println("flag2");
		transformLine(cline);
		return cline;
	}
	
	@Override
	protected CLine greedySelect() {
		float fitness, recordFitness = -1;
		CLine rline = null, cline = null;
		
		Pin p;
		ListIterator<Pin> iter = algData.pinList.listIterator();
		while(iter.hasNext()) {
			p = iter.next();
			
			if (p.lineList.isEmpty()) {
				//System.out.println("frag1");
				iter.remove();
				continue;
			}
			cline = greedySelect(p);
			fitness = getLineFitness(cline);
			if (fitness > recordFitness) {
				recordFitness = fitness;
				rline = cline;
			}
		}

		return rline;
	}
	
	
	protected CLine greedySelect(Pin p) {
		float fitness, recordFitness = -1;
		CLine rline = null, cline = null;
		//System.out.println(p.linesList.size());
		
		ListIterator<CLine> iter = p.lineList.listIterator();
		while(iter.hasNext()){
			cline = iter.next();
			
			if (cline.bestFitness < fitnessCutoff) {
				//System.out.println("" + cline.bestFitness);
				iter.remove();
				continue;
			}
						
			fitness = getLineFitness(cline);
			cline.bestFitness = fitness;
			if (fitness > recordFitness) {
				recordFitness = fitness;
				rline = cline;
			}
		}
		
		return rline;
	}
	
	private void transformLine(CLine line) {
		if (line == null) return;
		for (Pixel p: line.pixels) p.fitnessVal = 0;
	}
	
}
