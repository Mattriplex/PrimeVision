package shapes;

import java.awt.Polygon;
import java.awt.Shape;

public class RegularXagon implements ShapeGen {
	private int n;
	private double alpha;

	public RegularXagon(int n) {
		this.n = n;
		this.alpha = Math.PI * 2 / n;
	}

	public Shape getShape(double x, double y, int size, double angle) {
		double r = size / 2.0;
		int[] X = new int[n];
		int[] Y = new int[n];
		for (int i = 0; i < n; i++) {
			X[i] = (int) (x + Math.round(r * Math.sin(i * alpha + angle)));
			Y[i] = (int) (y + Math.round(r * Math.cos(i * alpha + angle)));
		}
		return new Polygon(X, Y, n);
	}

}
