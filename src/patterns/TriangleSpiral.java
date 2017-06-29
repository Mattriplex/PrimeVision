package patterns;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import shapes.ShapeGen;
import shapes.Tile;
import colours.CGenerator;
import base.FactorSieve;

public class TriangleSpiral implements PGenerator {
	private static final double sqrt3 = Math.sqrt(3.0);
	private CGenerator cGen;
	private int[][] hex;
	private final int sideL;
	private int[] factors;
	private int up;

	public TriangleSpiral(int a) {
		sideL = a;
		hex = new int[6][2];
		hex[3][0] -= hex[0][0] = 0;
		hex[4][0] -= hex[1][0] = a / 2;
		hex[5][0] -= hex[2][0] = a / 2;
		hex[3][1] -= hex[0][1] = (int) (-a / sqrt3);
		hex[4][1] -= hex[1][1] = (int) (-a / 2 / sqrt3);
		hex[5][1] -= hex[2][1] = (int) (a / 2 / sqrt3);

	}

	public Queue<Tile> drawPattern(int n, FactorSieve factors, CGenerator cGen, ShapeGen shape) {
		this.cGen = cGen;
		int[] numberOfFactors = factors.getFactors();
		this.factors = numberOfFactors;
		int currentX, currentY;
		currentX = currentY = n/2;
		up = 1;
		Queue<Tile> tiles = new LinkedList<Tile>();
		tiles.add(new Tile(cGen.getColour(1, 0, 0), getTriangle(currentX, currentY, true)));
		for (int j = 0; j < 6; j++)
			System.out.println(Arrays.toString(hex[j]));
		int i = 1;
		int k = 1;
		int t[];
		try {
			while (true) {
				for (int j = 0; j < 6; j++) {
					if (j == 3 || j == 5) {
						t = drawRow(currentX, currentY, (j+1)%6, i, k + 2, g2d);
						i += k + 2;
					} else {
						t = drawRow(currentX, currentY, (j+1)%6, i, k, g2d);
						i += k;
					}
					currentX = t[0];
					currentY = t[1];
				}
				k += 2;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Image creation complete. Saving...");
		}
	}

	private Polygon getTriangle(int x, int y, boolean up) {
		int[][] coords = new int[2][3];
		int[] xy = { x, y };
		int ind;
		if (up)
			ind = 0;
		else
			ind = 1;

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 3; j++)
				coords[i][j] = (int) (xy[i] + hex[(2 * (j + ind)) % 6][i]);

		if (up) {
			coords[0][0] = (int) (x + hex[0][0]);
			coords[0][1] = (int) (x + hex[2][0]);
			coords[0][2] = (int) (x + hex[4][0]);

			coords[1][0] = (int) (y + hex[0][1]);
			coords[1][1] = (int) (y + hex[2][1]);
			coords[1][2] = (int) (y + hex[4][1]);
		} else {
			coords[0][0] = (int) (x + hex[1][0]);
			coords[0][1] = (int) (x + hex[3][0]);
			coords[0][2] = (int) (x + hex[5][0]);

			coords[1][0] = (int) (y + hex[1][1]);
			coords[1][1] = (int) (y + hex[3][1]);
			coords[1][2] = (int) (y + hex[5][1]);
		}

		return new Polygon(coords[0], coords[1], 3);
	}

	private int[] drawRow(int sX, int sY, int d, int iF, int k,
			Graphics2D g) {
		boolean up = false;
		if (d % 2 == 0)
			up = true;
		int cX = sX;
		int cY = sY;
		for (int i = 0; i < k; i++) {
			cX += hex[(d + (i % 2))%6][0];
			cY += hex[(d + (i % 2))%6][1];
			Color c = new Color(cGen.getColour(factors[iF++], cX, cY));
			g.setColor(c);
			g.fillPolygon(getTriangle(cX, cY, up));
			up = !up;

		}
		int coords[] = { cX, cY };
		return coords;
	}

	private Point updateCoords(int sX, int sY, int d, int mod) {
		sX += hex[d][0];
		sY += hex[d][1];
		return new Point(sX, sY);
	}

	private class Point {
		public int x;
		public int y;

		public Point(int xCoord, int yCoord) {
			x = xCoord;
			y = yCoord;
		}
	}

}
