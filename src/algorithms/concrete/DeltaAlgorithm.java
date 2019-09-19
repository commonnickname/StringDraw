package algorithms.concrete;

import algorithms.DrawingAlgorithm;
import algorithms.variants.fitness.FitnessDelta;
import algorithms.variants.greedy.GreedyGlobal;
import algorithms.variants.greedy.GreedyLocal;
import data.CONST;
import utils.datatypes.CEnums.AlgStrategy;

public class DeltaAlgorithm extends DrawingAlgorithm{
	public DeltaAlgorithm() {
		super();
	}
	
	protected void setupFitnessAndStrategy() {
		fitness = new FitnessDelta(this);
		if(CONST.algStrategy == AlgStrategy.LOCAL) {
			greedy = new GreedyLocal(this);
		} else if (CONST.algStrategy == AlgStrategy.GLOBAL) {
			greedy = new GreedyGlobal(this);
		}
	}
}
