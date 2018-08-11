package algorithms.variants;

import algorithms.DrawingAlgorithm;
import algorithms.Fitness;
import algorithms.Implementable;
import utils.DUtils;
import utils.datatypes.Pixel;

public class NaiveFitness extends Implementable implements Fitness {
	public NaiveFitness(DrawingAlgorithm da) { super(da); }
	
	@Override
	public void setupFitnessCutoff() {
		da.fitnessCutoff = da.params.cutoff;
	}
	
	@Override
	public void setupTargetVals() {
		Pixel.setTargetVals( da.algData.pixels, 
						 	 DUtils.returnFloatArrayToPower( da.algData.imgVals, 
															 da.params.gamma ));
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
		p.fitnessVal = 0;
	}
}