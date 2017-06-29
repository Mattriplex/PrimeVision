package patterns;

import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.Queue;

import shapes.ShapeGen;
import shapes.Tile;
import colours.CGenerator;
import base.FactorSieve;

public class SquareSpiral implements PGenerator {
	private int size;
	private int offset;

	public SquareSpiral(int size) {
		this.size = size;
		offset = size / 2;
	}

	public Queue<Tile> drawPattern(int n, FactorSieve factors, CGenerator cGen,
			ShapeGen shape) {
		int[] NF = factors.getFactors();
		int cX, cY, i = 1, k = 1, xi = 2, yi = 3, foo = 0;
		boolean incK = false;
		cY = cX = n / 2;
		Queue<Tile> tiles = new LinkedList<Tile>();
		double alpha = 0;
		tiles.add(new Tile(cGen.getColour(1, 0, 0), shape.getShape(cX, cY,
				size, alpha)));
		int[] diff = { 0, -size, 0, size };
		try {
			while (true) {
				for (int j = 0; j < k; j++) {
					cX += diff[xi];
					cY += diff[yi];
					i++;
					alpha = getOrient(cX, cY, n/2);
					tiles.add(new Tile(cGen.getColour(NF[i], cX, cY), shape
							.getShape(cX, cY, size, alpha)));
				}
				// k += Math.abs(foo++ % 2);
				if (incK)
					k++;
				incK = !incK;
				xi = (xi + 1) % 4;
				yi = (yi + 1) % 4;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			return tiles;
		}
	}

	private double getOrient(int x, int y, int center) {
		double xd = x - center;
		double yd = y - center;
			
		double r = Math.sqrt(xd * xd + yd * yd);
		double alpha;
		if (xd > yd)
			alpha = Math.asin(xd / r);
		else
			alpha = Math.acos(yd / r);
		return Math.PI + alpha;
	}

}
