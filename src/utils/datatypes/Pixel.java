package utils.datatypes;

public class Pixel {
	public float targetVal;
	public float currentVal;
	public float fitnessVal;
	
	public Pixel() {
		
	}
	
	public static float[][] getTargetVals(Pixel[][] source){
		int width = source.length;
		int height = source[0].length;
		float[][] targetVals = new float[width][height];
		for(int i = 0; i < width; i++) {
		for(int j = 0; j < height; j++){
			targetVals[i][j] = source[i][j].targetVal;
		}}
		return targetVals;
	}
	
	public static float[][] getCurrentVals(Pixel[][] source){
		int width = source.length;
		int height = source[0].length;
		float[][] currentVals = new float[width][height];
		for(int i = 0; i < width; i++) {
		for(int j = 0; j < height; j++){
			currentVals[i][j] = source[i][j].currentVal;
		}}
		return currentVals;
	}
	
	public static float[][] getFitnessVals(Pixel[][] source){
		int width = source.length;
		int height = source[0].length;
		float[][] fitnessVals = new float[width][height];
		for(int i = 0; i < width; i++) {
		for(int j = 0; j < height; j++){
			fitnessVals[i][j] = source[i][j].fitnessVal;
		}}
		return fitnessVals;
	}
	
	public static void setTargetVals(Pixel[][] destination, float[][] source) {
		for (int x = 0; x < source.length; x++) {
		for (int y = 0; y < source[0].length; y++) {
			destination[x][y].targetVal = source[x][y];
		}}
	}
	
	public static void setCurrentVals(Pixel[][] destination, float[][] source) {
		for (int x = 0; x < source.length; x++) {
		for (int y = 0; y < source[0].length; y++) {
			destination[x][y].currentVal = source[x][y];
		}}
	}
	
	public static void setFitnessVals(Pixel[][] destination, float[][] source) {
		for (int x = 0; x < source.length; x++) {
		for (int y = 0; y < source[0].length; y++) {
			destination[x][y].fitnessVal = source[x][y];
		}}
	}

	
}
