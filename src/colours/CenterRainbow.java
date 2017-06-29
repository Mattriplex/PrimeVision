package colours;

import base.STool;
import base.WeightFunction;

public class CenterRainbow implements CGenerator {

	private int maxFactor;
	private int origin;
	private WeightFunction weight;
	private double maxdist;

	public CenterRainbow(int maxF, int center, WeightFunction f) {
		maxFactor = maxF;
		origin = center;
		weight = f;
		maxdist = center*Math.sqrt(2);
	}

	public int getColour(int fac, int x, int y) {
		double dist = Math.sqrt((y - origin)*(y-origin)+ (x - origin)*(x-origin))/maxdist;
		double ratio = weight.get(fac, maxFactor);
		int red = (int) ((Math.sin(dist*STool.TAU) * 127 + 128) * ratio);
		int green = (int) ((Math.sin(dist*STool.TAU + STool.T2) * 127 + 128) * ratio);
		int blue = (int) ((Math.sin(dist*STool.TAU + STool.T3) * 127 + 128) * ratio);
		return STool.rgbToInt(red, green, blue);
	}

}