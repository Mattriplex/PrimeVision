package colours;

import base.STool;
import base.WeightFunction;

public class FactorGradient implements CGenerator {
	private int maxFactor;
	private WeightFunction heavy;
	double offset;
	double range;

	public FactorGradient(int maxF, double range, double offset,
			WeightFunction func) {
		maxFactor = maxF;
		this.offset = offset * TAU;
		this.range = range * TAU;
		heavy = func;
	}

	public int getColour(int fac, int x, int y) {
		double ratio = heavy.get(fac+1, maxFactor+1);
		int red = (int) ((Math.sin(ratio * range + offset) * 127 + 128));
		int green = (int) ((Math.sin(ratio * range + offset + T2) * 127 + 128));
		int blue = (int) ((Math.sin(ratio * range + offset + T3) * 127 + 128));
		return STool.rgbToInt(red, green, blue);
	}

}
