package patterns;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import shapes.ShapeGen;
import shapes.Tile;
import base.FactorSieve;
import colours.CGenerator;

public class FermatSpiral implements PGenerator {
	private int size;
	private int offset;
	private double c;
	private static final double GOLDEN_ANGLE = Math.PI * (3 - Math.sqrt(5.0));

	public FermatSpiral(double c, int s) {
		size = s;
		offset = s / 2;
		this.c = c;
	}

	public Queue<Tile> drawPattern(int n, FactorSieve factors, CGenerator cGen,
			ShapeGen shape) {
		Queue<Tile> tiles = new LinkedList<Tile>();
		int[] NF = factors.getFactors();
		final int center = n / 2;
		int i = 1;
		double r;
		double theta;
		try {
			while (true) {
				r = c * Math.sqrt(i);
				theta = i * GOLDEN_ANGLE;
				Point xy = polarToCartesian(theta, r);
				tiles.add(new Tile(cGen.getColour(NF[i], xy.x + center, xy.y
						+ center), shape.getShape(xy.x + center, xy.y + center,
						size, 0)));
				i++;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			return tiles;
		}
	}

	public Point polarToCartesian(double theta, double r) {
		return new Point((int) (r * Math.cos(theta)),
				(int) (r * Math.sin(theta)));
	}
}
