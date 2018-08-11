package utils.objects;

import data.CONST;

public class ParameterPackage {
	public float cutoff;
	public double deltaExponent;
	public float deltaOpacity;
	public double gamma;
	
	public ParameterPackage() {
		this.cutoff = CONST.CUTOFF;
		this.deltaExponent = CONST.DELTA_EXPONENT;
		this.deltaOpacity = CONST.DELTA_OPACITY;
		this.gamma = CONST.GAMMA;
	}

	public void cutoff(float cutoff) {
		this.cutoff = cutoff;
	}

	public void setDeltaExponent(double deltaExponent) {
		this.deltaExponent = deltaExponent;
	}

	public void setDeltaOpacity(float deltaOpacity) {
		this.deltaOpacity = deltaOpacity;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}
	
}
