package algorithms.concrete;

import algorithms.DrawingAlgorithm;
import algorithms.variants.LocalGreedy;
import algorithms.variants.NaiveFitness;

public class LocalNaive extends DrawingAlgorithm{
	public static String name = "Local Naive";
	
	public LocalNaive() {
		super();
	}

	protected void specificFunctionality() {
		greedy = new LocalGreedy(this);
		fitness = new NaiveFitness(this);
	}
	

}
