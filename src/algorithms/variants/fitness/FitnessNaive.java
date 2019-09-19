package algorithms.variants.fitness;

import algorithms.DrawingAlgorithm;
import algorithms.variants.Implementable;
import utils.DUtils;
import utils.datatypes.Pixel;

public class FitnessNaive extends Implementable implements Fitness {
	public FitnessNaive(DrawingAlgorithm da) { super(da); }
	
	@Override
	public void setupFitnessCutoff() {
		da.fitnessCutoff = da.params.cutoff;
	}
	
	public void setupTargetVals() {
		float[][] targetVals = da.algData.imgVals;
		targetVals = DUtils.returnFloatArrayToGamma(targetVals, da.params.gamma);
		Pixel.setTargetVals(da.algData.pixels, targetVals);
	}
	
	@Override
	public void setupCurrentVals() {
		return;
	}

	@Override
	public void setupFitnessVals() {
		Pixel.setFitnessVals(da.algData.pixels, Pixel.getTargetVals(da.algData.pixels));
	}
	
	@Override
	public void pixelTransformation(Pixel p) {
		float delta = -p.fitnessVal;
		p.updateFitnessOfLines(delta);
		p.fitnessVal = 0;
	}
}