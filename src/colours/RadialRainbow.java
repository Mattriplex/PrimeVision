package colours;

import base.STool;
import base.WeightFunction;

public class RadialRainbow implements CGenerator {
	private int maxFactor;
	private int origin;
	private WeightFunction weight;

	public RadialRainbow(int maxF, int o, WeightFunction f) {
		maxFactor = maxF;
		origin = o;
		weight = f;
	}

	public int getColour(int fac, int x, int y) {
		double theta = Math.atan2((y - origin), (x - origin));
		double ratio = weight.get(fac, maxFactor);
		int red = (int) ((Math.sin(theta) * 127 + 128) * ratio);
		int green = (int) ((Math.sin(theta + STool.T2) * 127 + 128) * ratio);
		int blue = (int) ((Math.sin(theta + STool.T3) * 127 + 128) * ratio);
		return STool.rgbToInt(red, green, blue);
	}

}
