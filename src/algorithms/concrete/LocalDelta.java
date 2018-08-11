package algorithms.concrete;

import algorithms.DrawingAlgorithm;
import algorithms.variants.DeltaFitness;
import algorithms.variants.LocalGreedy;

public class LocalDelta extends DrawingAlgorithm{
	public static String name = "Local Delta";

	
	public LocalDelta() {
		super();
	}
	
	protected void specificFunctionality() {
		greedy = new LocalGreedy(this);
		fitness = new DeltaFitness(this);
	}
}
