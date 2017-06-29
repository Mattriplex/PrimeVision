package defunct;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import patterns.PGenerator;
import colours.CGenerator;
import base.FactorSieve;

public class UlamBasic implements PGenerator{

	public void drawPattern(int n, FactorSieve factors, CGenerator cGen) {
		int[] NF = factors.getFactors();
		final int sY = n / 2;
		final int sX = sY;
		int cX = sX;
		int cY = sY;
		BufferedImage canvas = new BufferedImage(n, n, BufferedImage.TYPE_INT_RGB);

		canvas.setRGB(sX, sY, cGen.getColour(1,0,0));

		int i = 1;
		int k = 1;
		int v;
		int[] diff = { 0, -1, 0, 1 };
		int xi = 2;
		int yi = 3;
		int foo = 0;
		try {
			while (true) {
				for (int j = 0; j < k; j++) {
					cX += diff[xi];
					cY += diff[yi];
					i++;
					v = cGen.getColour(NF[i], cX, cY);
					canvas.setRGB(cX, cY, v);
				}
				k += Math.abs(foo++ % 2);
				xi = (xi + 1) % 4;
				yi = (yi + 1) % 4;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Image creation complete. Saving...");
		}
		return canvas;
	}

	
}
