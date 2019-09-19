package utils.helpers;

import data.CONST;
import utils.datatypes.CEnums.AlgStrategy;

public class ParameterPackage {
	public float cutoff;
	public double deltaExponent;
	public float deltaOpacity;
	public double gamma;
	public String algKey;
	public AlgStrategy strategy;
	
	public ParameterPackage() {
		this.cutoff = CONST.CUTOFF;
		this.deltaExponent = CONST.DELTA_EXPONENT;
		this.deltaOpacity = CONST.DELTA_OPACITY;
		this.gamma = CONST.GAMMA;
		this.algKey = CONST.algKey;
		this.strategy = CONST.algStrategy;
	}
	
	public ParameterPackage(float cutoff, double deltaExponent, float deltaOpacity, double gamma) {
		this.cutoff = 
				cutoff > 0 && cutoff <= 1 ? cutoff : CONST.CUTOFF;
		this.deltaExponent = 
				deltaExponent > 0 && deltaExponent <= 5 ? deltaExponent : CONST.DELTA_EXPONENT;
		this.deltaOpacity = 
				deltaOpacity > 0.01 && deltaOpacity <= 1 ? deltaOpacity : CONST.DELTA_OPACITY;
		this.gamma = 
				gamma > 0 && gamma <= 5 ? gamma : CONST.GAMMA;
		this.algKey = CONST.algKey;
		this.strategy = CONST.algStrategy;

	}

	public void setKey(String algKey) { this.algKey = algKey; }
	public void setStrategy(AlgStrategy strategy) { this.strategy = strategy; }
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if ((o == null) || !(o instanceof ParameterPackage)) return false;
		ParameterPackage p = (ParameterPackage)o;
		boolean result = this.cutoff == p.cutoff
				&& this.deltaExponent == p.deltaExponent 
				&& this.deltaOpacity == p.deltaOpacity
				&& this.gamma == p.gamma
				&& this.algKey.equals(p.algKey)
				&& this.strategy == p.strategy;
				
		return result;
	}
	
	@Override
	public ParameterPackage clone() {
		ParameterPackage p = new ParameterPackage(
				this.cutoff, this.deltaExponent, this.deltaOpacity, this.gamma);
		p.algKey = this.algKey;
		return p;
	}
	
	@Override 
	public String toString() {
		String s = "";
		s += "cutoff: " + cutoff;
		s += "; deltaExponent: " + deltaExponent;
		s += "; deltaOpacity: " + deltaOpacity;
		s += "; gamma: " + gamma;
		s += "; " + algKey;
		return s;
	}
	
}
