package colours;

import base.STool;

public class MonoScale implements CGenerator {
	private CGenerator c;

	public MonoScale(int maxFactor, int off, boolean neg) {
		if (neg)
			c = new NegColor(maxFactor, off);
		else
			c = new PosColor(maxFactor, off);
	}

	public int getColour(int fac, int x, int y) {
		return c.getColour(fac, x, y);
	}

	public static int rgbToInt(int r, int g, int b) {
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}

	private class NegColor implements CGenerator {
		private int offset;
		private int maxFactor;

		public NegColor(int maxfac, int off) {
			maxFactor = maxfac;
			offset = off;
		}

		public int getColour(int fac, int x, int y) {
			int v = 255 - offset;
			if (fac == 0)
				return 0xFFFFFF;
			v = (int) (v - ((double) fac / (double) maxFactor) * v);
			return STool.rgbToInt(v, v, v);
		}
	}

	private class PosColor implements CGenerator {
		private int offset;
		private int maxFactor;

		public PosColor(int maxfac, int off) {
			maxFactor = maxfac;
			offset = off;
		}

		public int getColour(int fac, int x, int y) {
			int v = 255 - offset;
			if (fac == 0)
				return 0xFFFFFF;
			v = (int) (offset + ((double) fac / (double) maxFactor) * v);
			return STool.rgbToInt(v, v, v);
		}
	}
}
