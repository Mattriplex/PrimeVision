package colours;

import base.STool;

public class Spiralvisual implements CGenerator {
	private static final double TAU = 2 * Math.PI;
	private int i;
	private int n;
	private int repetitions;
	public Spiralvisual(int n, int rep) {
		i = 0;
		this.n = n;
		repetitions = rep;
	}

	@Override
	public int getColour(int fac, int x, int y) {
		i++;
		double theta = ((double)i/(double)n) * TAU * repetitions;
		int red = (int) (Math.sin(theta)*128) + 127;
		int green = (int) (Math.sin(theta + STool.T2)*128) + 127;
		int blue = (int) (Math.sin(theta + STool.T3)*128) + 127;
		return rgbToInt(red, green, blue);
	}
	
	public static int rgbToInt(int r, int g, int b) {
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}

}
