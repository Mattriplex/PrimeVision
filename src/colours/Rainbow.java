package colours;

import base.STool;

public class Rainbow {
	private double pOffset, cOffset;
	private int range, bOffset;

	public Rainbow(double hue, double brightness, double phaseoffset,
			double offset) {
		this.range = (int) (hue * 127) % 128;
		this.bOffset = (int) Math.round((256 - 2 * range) * brightness + range);
		if (phaseoffset == -1) 
			this.pOffset = 2 * Math.PI / 3;
		else
			this.pOffset = phaseoffset;
		this.cOffset = offset;
	}

	public int getColour(double ratio) {
		int red = (int) ((Math.sin(ratio + cOffset) * range + bOffset));
		int green = (int) ((Math.sin(ratio + cOffset + pOffset) * range + bOffset));
		int blue = (int) ((Math.sin(ratio + cOffset + 2 * pOffset) * range + bOffset));
		return STool.rgbToInt(red, green, blue);
	}
}
