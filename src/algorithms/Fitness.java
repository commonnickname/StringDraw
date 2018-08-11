package algorithms;

import utils.datatypes.Pixel;

public interface Fitness {
	void setupFitnessCutoff();
	void setupTargetVals();
	void setupCurrentVals();
	void setupFitnessVals();
	void pixelTransformation(Pixel p);
}



