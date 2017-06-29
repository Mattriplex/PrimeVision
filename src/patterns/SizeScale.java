package patterns;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import shapes.Tile;
import colours.CGenerator;
import base.FactorSieve;
import base.NumberHolder;
import base.WeightFunction;

public class SizeScale implements PGenerator {
	private WeightFunction fWeight;
	private int maxFactor;
	private int minSize;
	private int maxSize;
	private int sizeOffs;

	public SizeScale(int min, int max, int maxfac, WeightFunction fWeight, double pct) {
		this.fWeight = new WeightFunction(1);
		minSize = min;
		maxSize = max;
		maxFactor = maxfac;
		sizeOffs = (int)(minSize + (maxSize-minSize) * pct);
	}

	public Queue<Tile> drawPattern(int n, FactorSieve factors, CGenerator cGen) {
		int[] NF = factors.getFactors();
		final int sY = n / 2;
		final int sX = sY;
		int cX = sX;
		int cY = sY;
		Queue<Tile> tiles = new LinkedList<Tile>();



		double sizeRatio = fWeight.get(1, maxFactor);
		// *******************possible error
		int size = (int) (sizeRatio * maxSize + minSize);
		int offset = size / 2;
		int i = 1;
		int k = 1;
		int[] diff = { 0, -sizeOffs, 0, sizeOffs };
		int xi = 2;
		int yi = 3;
		int foo = 0;
		try {
			while (true) {
				for (int j = 0; j < k; j++) {
					cX += diff[xi];
					cY += diff[yi];
					i++;
					sizeRatio = fWeight.get(NF[i], maxFactor);
					size = (int) (sizeRatio * maxSize + minSize);
					offset = size / 2;
					tiles.add(new NumberHolder(cX-offset, cY - offset, size, cGen.getColour(NF[i], cX, cY)));
				}
				k += Math.abs(foo++ % 2);
				xi = (xi + 1) % 4;
				yi = (yi + 1) % 4;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			
			System.out.println("Image creation complete. Saving...");
			return tiles;
		}
	}

}
