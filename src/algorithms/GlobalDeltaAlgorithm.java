package algorithms;
import java.util.ListIterator;

import utilDatatypes.CLine;
import utilDatatypes.Pin;
import utilDatatypes.Pixel;

public class GlobalDeltaAlgorithm extends DrawingAlgorithm{
	public static String name = "Global Delta";

	public GlobalDeltaAlgorithm() {
		super();
	}
	
	protected void specificFunctionality() {
		fitnessCutoff = params.cutoff * maxGain();
		setupCurrentVals();
		setupFitnessVals();
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
	
	protected void setupCurrentVals() {
		for (int x = 0; x < algData.width; x++) {
		for (int y = 0; y < algData.height; y++) {
			algData.pixels[x][y].currentVal = 0;
		}}
	}

	protected void setupFitnessVals() {
		float currentVal, targetVal;
		for (int x = 0; x < algData.width; x++) {
		for (int y = 0; y < algData.height; y++) {
			currentVal = algData.pixels[x][y].currentVal;
			targetVal = algData.pixels[x][y].targetVal;
			algData.pixels[x][y].fitnessVal = fitnessFunction(currentVal, targetVal);
		}}
	}
	
	protected CLine getFirstLine() {
		
		CLine cline = super.greedySelect();
		if(cline == null || getLineFitness(cline) < fitnessCutoff) {
			 return null;
		}
		
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
		float transformedVal, currentVal;
		for (Pixel p: line.pixels) {
			currentVal = p.currentVal;
			transformedVal = transformation(p.currentVal);
			p.fitnessVal = fitnessFunction(currentVal, p.targetVal);
			p.currentVal = transformedVal;
		}
	}
	
	protected float getLineFitness(CLine line) {
		if (line == null) return -1;
		float sum = 0;
		for(Pixel p: line.pixels) sum += p.fitnessVal;
		return sum/line.getLength();
	}
	
	private float transformation(float num) {
		return num + (1 - num) * params.deltaOpacity;
	}
	
	private float baseFitness(float f) {
		return -(float)Math.pow(Math.abs(f), params.deltaExponent);
	}
	
	private float fitnessFunction(float currentVal, float targetVal) {
		return baseFitness(transformation(currentVal) - targetVal) - baseFitness(currentVal - targetVal);
	}
	
	public float maxGain() {
		if (params.deltaOpacity <= 0 || params.deltaOpacity > 1 || params.deltaExponent <= 0) return 1;
		
		if (params.deltaExponent >= 1) {
			return (float)Math.abs(1.0 - Math.abs(baseFitness((float)(1.0 - params.deltaOpacity))));
		}else {
			return (float)Math.abs(baseFitness(params.deltaOpacity));
		}
	}

}
