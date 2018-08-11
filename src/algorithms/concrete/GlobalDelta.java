package algorithms.concrete;

import algorithms.variants.DeltaFitness;
import algorithms.variants.GlobalGreedy;

public class GlobalDelta extends LocalDelta{
	public static String name = "Global Delta";
	
	public GlobalDelta() {
		super();
	}
	
	@Override
	protected void specificFunctionality() {
		greedy = new GlobalGreedy(this);
		fitness = new DeltaFitness(this);
	}
}
