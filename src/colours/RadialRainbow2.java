package colours;

import base.STool;

public class RadialRainbow2 implements CGenerator {

	private int xOrig;
	private int yOrig;

	public RadialRainbow2(int xOrigin, int yOrigin) {
		xOrig = xOrigin;
		yOrig = yOrigin;
	}

	public int getColour(int fac, int x, int y) {
		if (fac == 0) {
			int xdif = x - xOrig;
			double theta;
			if (xdif == 0)
				theta = Math.atan2(y - yOrig, 1);
			else
				theta = Math.atan2(y - yOrig, (xdif));
			int red = (int) (Math.sin(theta) * 128) + 127;
			int green = (int) (Math.sin(theta + STool.T2) * 128) + 127;
			int blue = (int) (Math.sin(theta + STool.T3) * 128) + 127;
			return STool.rgbToInt(red, green, blue);
		} else
			return 0;
	}

}
