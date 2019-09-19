package utils;

import java.util.Arrays;

/*
 * Utility Functions for standard data types.
 */

public class DUtils {

	// return a float array of specified size with specified initial value
	public static float[] floatArray(int length, float value) {
		if(value == 0) return new float[length];
		
		float[] newArr = new float[length];
		Arrays.fill(newArr, value);
		return newArr;
	}
	
	// return a 2D float array of specified size with specified initial value
	public static float[][] floatArray(int width, int height, float value){
		if(value == 0) return new float[width][height];
		
		float[][] newArr = new float[width][height];
		for(float[] arr: newArr) Arrays.fill(arr,  value);
		
		return newArr;
	}
	
	// sets every number in the array to specified power
	public static void setFloatArrayToPower(float[] sourceArr, double exponent) {
		for (int x = 0; x < sourceArr.length; x++) {
			sourceArr[x] = (float)Math.pow(sourceArr[x], exponent);
		}
	}

	
	// returns array with every number in the source array set to specified power
	public static float[] returnFloatArrayToPower(float[] sourceArr, double exponent) {
		float[] result = new float[sourceArr.length];
		for (int x = 0; x < sourceArr.length; x++) {
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
	public static float[][] returnFloatArrayToGamma(float[][] sourceArr, double exponent) {
		int width = sourceArr.length;
		int height = sourceArr[0].length;
		float[][] result = new float[width][height];
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			result[x][y] = (float)Math.pow(sourceArr[x][y], exponent);
		}}
		return result;
	}	
	
	/*************************************************************************/
	//glue two pieces of a polynomial in the middle - sloped middle, rounded extremes
	public static float[][] returnFloatArrayContrast1(float[][] sourceArr, double exponent) {
		int width = sourceArr.length;
		int height = sourceArr[0].length;
		float[][] result = new float[width][height];
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			float source = sourceArr[x][y];
			if (0 <= source && source <= 0.5) {
				result[x][y] = (float)(Math.pow(2*source, exponent))/2;
			} else if (0.5 < source && source <= 1) {
				result[x][y] = 1 - (float)(Math.pow(2 - 2*source, exponent))/2;
			} else {
				result[x][y] = 0;
			}
			
		}}
		return result;
	}	
	
	/*************************************************************************/
	//force odd polynomial - sloped extremes, rounded middle
	public static float[][] returnFloatArrayContrast2(float[][] sourceArr, double exponent) {
		int width = sourceArr.length;
		int height = sourceArr[0].length;
		float[][] result = new float[width][height];
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			double p = Math.pow(Math.abs(2*(sourceArr[x][y] - 0.5)), 1.0/exponent);
			result[x][y] = (float)(0.5 + 0.5*Math.signum(sourceArr[x][y] - 0.5)*p);
			
		}}
		return result;
	}	
	
	/*************************************************************************/
	public static float[][] returnFloatArrayScaled(float[][] sourceArr, double from, double to) {
		double diff = Math.abs(from - to);
		int width = sourceArr.length;
		int height = sourceArr[0].length;
		float[][] result = new float[width][height];
		for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			result[x][y] = (float)(from + sourceArr[x][y]*diff);
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
