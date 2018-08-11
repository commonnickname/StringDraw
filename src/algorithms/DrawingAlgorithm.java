package algorithms;
import java.util.ArrayList;

import utils.datatypes.CEnums.Action;
import utils.objects.AlgorithmData;
import utils.objects.ParameterPackage;
import utils.datatypes.CLine;
import utils.datatypes.Pin;
import utils.datatypes.Pixel;

public abstract class DrawingAlgorithm {
	public static String name = "default name";
	
	public float fitnessCutoff;
	public Pin p1 = null;

	public Pin p2 = null;
	public Action action;
	
	public AlgorithmData algData;
	public ParameterPackage params;
	public ArrayList<CLine> fullLinesList;
	
	public Greedy greedy;
	public Fitness fitness;
	
	public DrawingAlgorithm() { }

	public void setup(AlgorithmData algData, ParameterPackage params) {
		this.algData = algData;
		this.params = params;
		action = Action.FIRST;
		fullLinesList = new ArrayList<CLine>();

		specificFunctionality();
		fitness.setupFitnessCutoff();
		setupPixels();
	}
	
	protected void setupPixels() {
		fitness.setupTargetVals();
		fitness.setupCurrentVals();
		fitness.setupFitnessVals();
	}
	
	protected void specificFunctionality() {}
	
	public CLine getLine(){
		CLine line = greedy.getLine();
		if (line == null) return null;
		fullLinesList.add(line);
		transformLine(line);
		return line;
	}
	
	protected void transformLine(CLine line) {
		if (line == null) return;
		for (Pixel p: line.pixels) fitness.pixelTransformation(p);
	}
	
	public CLine getFirstLine() {
		CLine cline = greedySelect(algData.pinList);
		if(cline == null || getLineFitness(cline) < fitnessCutoff) return null;
		p1 = cline.from; p2 = cline.to;
		return cline;
	}

	protected CLine greedySelect(ArrayList<Pin> pinList) {
		float fitness, recordFitness = -1;
		CLine rline = null, cline = null;
		
		for(Pin p: pinList) {
			cline = selectBestLine(p.lineList);
			fitness = getLineFitness(cline);
			if (fitness > recordFitness) {
				recordFitness = fitness;
				rline = cline;
			}
		}
		return rline;
	}
	
	public CLine selectBestLine(ArrayList<CLine> lines) {
		float fitness, recordFitness = -1;
		CLine rline = null;
		for(CLine cline: lines) {
			fitness = getLineFitness(cline);
			if(fitness > recordFitness) {
				recordFitness = fitness;
				rline = cline;
			}
		}
		return rline;
	}
	
	public float getLineFitness(CLine line) {
		if (line == null) return -1;
		float sum = 0;
		for(Pixel p: line.pixels) sum += p.fitnessVal;
		return sum/line.getLength();
	}
}
