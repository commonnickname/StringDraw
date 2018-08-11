package utils;


/*
 * Utility Functions for standard data types.
 */

public class DUtils {

	// return a float array of specified size with specified initial value
	public static float[] floatArray(int length, float value) {
		if(value == 0) return new float[length];
		
		float[] newArr = new float[length];
		for(int i = 0; i < length; i++) newArr[i] = value;
		return newArr;
	}
	
	// return a 2D float array of specified size with specified initial value
	public static float[][] floatArray(int width, int height, float value){
		if(value == 0) return new float[width][height];
		
		float[][] newArr = new float[width][height];
		for(int i = 0; i < width; i++) {
		for(int j = 0; j < height; j++) {
			newArr[i][j] = value;
		}}
		return newArr;
	}
	
	// sets every number in the array to specified power
	public static void setFloatArrayToPower(float[] sourceArr, double exponent) {
		int width = sourceArr.length;
		for (int x = 0; x < width; x++) {
			sourceArr[x] = (float)Math.pow(sourceArr[x], exponent);
		}
	}

	
	// returns array with every number in the source array set to specified power
	public static float[] returnFloatArrayToPower(float[] sourceArr, double exponent) {
		int width = sourceArr.length;
		float[] result = new float[width];
		for (int x = 0; x < width; x++) {
			result[x] = (float)Math.pow(sourceArr[x], exponent);
		}
		return result;
	}
	
	/*************************************************************************/
	public static void setFloatArrayToPower(float[][] sourceArr, double exponent) {
		int width = sourceArr.length;
		int height = sourceArr[0].length;
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			sourceArr[x][y] = (float)Math.pow(sourceArr[x][y], exponent);
		}}
	}
	
	/*************************************************************************/
	public static float[][] returnFloatArrayToPower(float[][] sourceArr, double exponent) {
		int width = sourceArr.length;
		int height = sourceArr[0].length;
		float[][] result = new float[width][height];
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			result[x][y] = (float)Math.pow(sourceArr[x][y], exponent);
		}}
		return result;
	}	
	
	/************************************************************************/
	public static float[][] int255DArrToFloat(int[][] int255DArr) {
		float[][] newArr = new float[int255DArr.length][int255DArr[0].length];
		for (int x = 0; x < int255DArr.length; x++) {
		for (int y = 0; y < int255DArr[0].length; y++) {
			newArr[x][y] = (float)int255DArr[x][y]/255;
		}}
		return newArr;
	}
	

}
