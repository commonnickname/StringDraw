package algorithms.concrete;

import algorithms.variants.GlobalGreedy;
import algorithms.variants.NaiveFitness;

public class GlobalNaive extends LocalNaive{
	public static String name = "Global Naive";
	
	public GlobalNaive() {
		super();
	}

	@Override
	protected void specificFunctionality() {
		greedy = new GlobalGreedy(this);
		fitness = new NaiveFitness(this);
	}
}
