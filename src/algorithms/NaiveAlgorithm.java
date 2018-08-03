package algorithms;
import utilDatatypes.CLine;
import utilDatatypes.Pin;
import utilDatatypes.Pixel;

public class NaiveAlgorithm extends DrawingAlgorithm{
	public static String name = "Naive";
	Pin p1 = null, p2 = null;
	
	public NaiveAlgorithm() {
		super();
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
		
		CLine cline = super.greedySelect();
		if(cline == null || getLineFitness(cline) < fitnessCutoff) return null;
		
		p1 = cline.from;
		p2 = cline.to;
		
		tarnsformLine(cline);
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
		tarnsformLine(cline);
		return cline;
	}
	
	private void tarnsformLine(CLine line) {
		if (line == null) return;
		for (Pixel p: line.pixels) p.fitnessVal = 0;
	}
}
