package algorithms.variants.fitness;

import algorithms.DrawingAlgorithm;
import algorithms.variants.Implementable;
import utils.DUtils;
import utils.datatypes.Pixel;

public class FitnessDeltaLog extends Implementable implements Fitness {
	public FitnessDeltaLog(DrawingAlgorithm da) { super(da); }
	
	@Override
	public void setupFitnessCutoff() {
		da.fitnessCutoff = da.params.cutoff * maxGain();
	}
	
	public void setupTargetVals() {
		float[][] targetVals = da.algData.imgVals;
		targetVals = DUtils.returnFloatArrayToGamma(targetVals, da.params.gamma);
		
		//correct values to eliminate pure black, which would otherwise result in log(0)
		targetVals = DUtils.returnFloatArrayScaled(targetVals, 0, 0.9);
		
		float denum = (float) Math.log(1 - da.params.deltaOpacity);
		for(int i = 0; i < targetVals.length; i++) {
		for(int j = 0; j < targetVals[0].length; j++) {
			targetVals[i][j] = (float)( Math.log(1 - targetVals[i][j]) / denum  );
		}}
		Pixel.setTargetVals(da.algData.pixels, targetVals);
	}
	
	@Override
	public void setupCurrentVals() {
		Pixel.setCurrentVals( da.algData.pixels, 
							  DUtils.floatArray( da.algData.width, da.algData.height, 0 ));
	}

	@Override
	public void setupFitnessVals() {
		Pixel[][] pixels = da.algData.pixels;
		for (int x = 0; x < da.algData.width; x++) {
		for (int y = 0; y < da.algData.height; y++) {
			pixels[x][y].fitnessVal = fitnessFunction(pixels[x][y].currentVal, pixels[x][y].targetVal);
		}}
	}

	@Override
	public void pixelTransformation(Pixel p) {
		float delta = -p.fitnessVal;
		p.fitnessVal = fitnessFunction(p.currentVal, p.targetVal);
		p.currentVal = baseTransform(p.currentVal);
		delta += p.fitnessVal;
		p.updateFitnessOfLines(delta);

	}
	
	private float baseTransform(float num) {
		return num + 1;
	}
	
	private float baseFitness(float f) {
		return -(float)Math.pow(Math.abs(f), da.params.deltaExponent);
	}
	
	private float fitnessFunction(float current, float target) {
		return baseFitness(baseTransform(current) - target) - baseFitness(current - target);

	}
	
	public float maxGain() {
		if (da.params.deltaOpacity <= 0) return 1;
		if (da.params.deltaOpacity > 1 ) return 1;
		if (da.params.deltaExponent <= 0) return 1;
		
		if (da.params.deltaExponent >= 1) {
			return (float)Math.abs(1 - Math.abs(baseFitness((float)(1 - da.params.deltaOpacity))));
		}else {
			return (float)Math.abs(baseFitness(da.params.deltaOpacity));
		}
	}




}