package patterns;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import colours.CGenerator;
import colours.RadialRainbow2;
import base.FactorSieve;

public class KlauberTriangle implements PGenerator {
	private int imgWidth;
	private int imgHeight;
	private int sSize;
	private int sOffset;

	public KlauberTriangle(int height, int size) {
		imgHeight = height;
		imgWidth = 2 * height - 1;
		sSize = size;
		sOffset = size / 2;
	}

	public void drawPattern(int n, FactorSieve factors, CGenerator cGen,
			Graphics2D g2d) {
		int[] NF = factors.getFactors();
		final int sY = sOffset;
		final int sX = imgWidth / 2 + 1;
		int cX = sX;
		int cY = sY;
		cGen = new RadialRainbow2(sX, sY);
		BufferedImage canvas = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_INT_RGB);

		int i = 1;
		int k = 1;
		int hi = 0;
		try {
			while (true) {
				for (int j = 0; j < k; j++) {
					canvas.setRGB(cX, cY, cGen.getColour(NF[i++], cX, cY));
					g2d.setColor(new Color(cGen.getColour(NF[i++], cX, cY)));
					g2d.fillOval(cX - sOffset, cY - sOffset, sSize, sSize);
					cX += sSize;

				}
				k += 2;
				hi++;
				cX = sX - hi * sSize;
				cY += sSize;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Image creation complete. Saving...");
		}
	}

}
