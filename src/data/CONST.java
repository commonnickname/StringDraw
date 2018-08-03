package data;

public class CONST {
	public final static int CANVAS_W = 575; 
	public final static int CANVAS_H = 650;
	public final static int GAP_W = 8;
	public final static int GAP_H = 8;
	public final static int FRAME_W = CANVAS_W + GAP_W;
	public final static int FRAME_H = CANVAS_H + GAP_H;
	public final static int CONTROL_W = 160;
	
	public final static int MIN_SIDE = 50;
	public final static int MAX_SIDE = 10000;
	public final static double RATIO = 1.0/13; //ratio of sides
	public final static int FILTER_OPACITY = 0x50; // 0x0 = transparent; 0xff = opaque
	
	public final static int PINNUM = 400;
	public final static int PINSIZE = 3;
	public final static int SCALAR = 3;
	public final static int SKIP = 30;
	
	public static String algorithmName = "algorithms.DeltaAlgorithm";

	public static float MIN_EXPONENT = 0;
	public static float MAX_EXPONENT = 5;
	public static float MIN_OPACITY = 0.01f;
	public static float MAX_OPACITY = 1;
	
	public static float CUTOFF = 0.1f;
	public static double DELTA_EXPONENT = 2f;
	public static float DELTA_OPACITY = 0.15f;
	public static double GAMMA = 1.5;
}
