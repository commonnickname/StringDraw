package algorithms;
import data.AlgorithmData;
import utilDatatypes.CLine;
import utilDatatypes.Pin;
import utilDatatypes.Pixel;
import utils.ParameterPackage;

public class DrawingAlgorithm {
	public static String name = "base";
	
	protected Boolean initiated, first;
	protected float fitnessCutoff;
	protected ParameterPackage params;
	
	public AlgorithmData algData;
	
	public DrawingAlgorithm(){
		initiated = false;
		first = true;
	}

	public void setup(AlgorithmData algData, ParameterPackage params) {
		this.algData = algData;
		this.params = params;
		if(algData == null) {
			System.out.println("algData null");
		}
		if(algData.pinList == null) {
			System.out.println("algData.pinList null");
		}
		
		setupTargetVals();
		specificFunctionality();
		initiated = true;
	}
	

	
	// to be overridden by a subclass
	protected void specificFunctionality() {}
	
	protected void setupTargetVals() {
		for (int x = 0; x < algData.width; x++) {
		for (int y = 0; y < algData.height; y++) {
			algData.pixels[x][y].targetVal = (float) Math.pow(algData.imgVals[x][y], params.gamma);
		}}
	}
	
	protected void setupCurrentVals() {}
	
	public CLine getLine(){
		if (first) {
			first = false;
			return getFirstLine();
		}
		return getNextLine();
		
	}
	
	protected CLine getFirstLine() {
		return null;
	}
	protected CLine getNextLine() {
		return null;
	}

	
	protected CLine greedySelect() {
		float fitness, recordFitness = -1;
		CLine rline = null, cline = null;
		for(Pin p: algData.pinList) {
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
		CLine rline = null;
		
		for(CLine line: p.lineList) {
			fitness = getLineFitness(line);
			if (fitness > recordFitness) {
				recordFitness = fitness;
				rline = line;
			}
		}
		return rline;
	}
	
	protected float getLineFitness(CLine line) {
		if (line == null) return -1;
		float sum = 0;
		if (line.pixels == null) System.out.println("null!");
		for(Pixel p: line.pixels) sum += p.fitnessVal;
		return sum/line.getLength();
	}
}
