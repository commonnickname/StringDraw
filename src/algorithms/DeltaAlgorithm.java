package algorithms;
import utilDatatypes.CLine;
import utilDatatypes.Pin;
import utilDatatypes.Pixel;

public class DeltaAlgorithm extends DrawingAlgorithm{
	public static String name = "Delta";
	Pin p1 = null, p2 = null;

	public DeltaAlgorithm() {
		super();
	}
	
	protected void specificFunctionality() {
		fitnessCutoff = params.cutoff * maxGain();
		setupCurrentVals();
		setupFitnessVals();
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
		
		p1 = cline.from;
		p2 = cline.to;
		
		transformLine(cline);
		return cline;
	}

	protected CLine getNextLine() {
		CLine line1 = super.greedySelect(p1);
		CLine line2 = super.greedySelect(p2);
		
		if (line1 == null && line2 == null) return null;
		
		CLine cline = null;
		if (getLineFitness(line1) >= getLineFitness(line2)) {
			p1 = line1.to;
			cline = line1;
		} else {
			p2 = line2.to;
			cline = line2;
		}

		if (getLineFitness(cline) < fitnessCutoff) return null;
		transformLine(cline);
		return cline;
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
