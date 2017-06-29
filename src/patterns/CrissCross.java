package patterns;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import colours.CGenerator;
import base.FactorSieve;

public class CrissCross implements PGenerator {
	private int sizeSection;
	private int posOffset;

	public CrissCross(int size) {
		sizeSection = size;
		posOffset = size / 2;
	}

	public void drawPattern(int n, FactorSieve factors, CGenerator cGen,
			Graphics2D g2d) {
		int[] NF = factors.getFactors();
		final int center = n/2;
		int currentX, currentY;
		currentX = currentY = center;
		int i = 1;
		int k = 1;
		int v;
		int cornerX;
		int cornerY;
		int[] diff = { 2 * sizeSection, sizeSection, -2 * sizeSection,
				-sizeSection };
		int xi = 0;
		int yi = 3;
		int foo = 0;
		try {
			while (true) {
				for (int j = 0; j < k; j++) {
					currentX += diff[xi];
					currentY += diff[yi];
					i++;
					v = cGen.getColour(NF[i], currentX, currentY);
					g2d.setColor(new Color(v));

					cornerX = currentX - posOffset;
					cornerY = currentY - posOffset;
					g2d.fillRect(cornerX, cornerY, sizeSection, sizeSection);
					g2d.fillRect(cornerX - sizeSection, cornerY, sizeSection,
							sizeSection);
					g2d.fillRect(cornerX + sizeSection, cornerY, sizeSection,
							sizeSection);
					g2d.fillRect(cornerX, cornerY - sizeSection, sizeSection,
							sizeSection);
					g2d.fillRect(cornerX, cornerY + sizeSection, sizeSection,
							sizeSection);
				}
				k += Math.abs(foo++ % 2);
				xi = (xi + 1) % 4;
				yi = (yi + 1) % 4;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Image creation complete. Saving...");
		}

	}

}
