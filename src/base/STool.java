package base;

import java.awt.Point;

public class STool {
	public static final double TAU = 2 * Math.PI;
	public static final double T2 = TAU / 3;
	public static final double T3 = 2 * TAU / 3;

	public static int rgbToInt(int r, int g, int b) {
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}
	
	public Point polarToCartesian(double theta, double r) {
		return new Point((int)(r*Math.cos(theta)), (int)(r*Math.sin(theta)));
	}
	
	public int[] intifyArr(double[] a){
		int[] res = new int[a.length];
		for (int i = 0; i < a.length; i++)
			res[i] = (int)Math.round(a[i]);
		return res;
	}

}
