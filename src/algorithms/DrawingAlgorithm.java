package algorithms;
import java.util.ArrayList;

import algorithms.variants.fitness.Fitness;
import algorithms.variants.greedy.Greedy;
import utils.datatypes.CEnums.Action;
import utils.datatypes.CLine;
import utils.datatypes.Pin;
import utils.datatypes.Pixel;
import utils.helpers.AlgorithmData;
import utils.helpers.ParameterPackage;

public abstract class DrawingAlgorithm {
	public float fitnessCutoff;
	public Action action;
	
	public AlgorithmData algData;
	public ParameterPackage params;
	public ArrayList<CLine> fullLinesList;
	
	public Greedy greedy;
	public Fitness fitness;
	
	public boolean localityAvailable;
	
	public DrawingAlgorithm() { }

	public void setup(AlgorithmData algData, ParameterPackage params) {
		this.algData = algData;
		this.params = params;
		
		fullLinesList = new ArrayList<CLine>();

		setupFitnessAndStrategy();
		fitness.setupTargetVals();
		fitness.setupFitnessCutoff();
		fitness.setupCurrentVals();
		fitness.setupFitnessVals();
		algData.updateAllLineFitness();
		
	}
	
	protected abstract void setupFitnessAndStrategy();
	
	public CLine getLine(){
		CLine line = greedy.getLine();
		if (line == null) return null;
		
		fullLinesList.add(line);
		for (Pixel p: line.pixels) fitness.pixelTransformation(p);
		return line;
	}

}
