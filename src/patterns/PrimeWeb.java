package patterns;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import base.FactorSieve;
import base.STool;
import colours.CGenerator;

public class PrimeWeb implements PGenerator {

	private int size;
	private int offset;
	private double c;
	private static final double GA = Math.PI * (3 - Math.sqrt(5.0));

	public PrimeWeb(double c, int s) {
		size = s;
		offset = s / 2;
		this.c = c;
	}

	public void drawPattern(int n, FactorSieve factors, CGenerator cGen,
			Graphics2D g2d) {
		int[] primeFactors = factors.getFactors();
		int[] primes = factors.getPrimes();
		Map<Integer, Point> spaceN = new HashMap<Integer, Point>();
		final int N = factors.getN();
		final int numPrimes = factors.getPrimeAmt();
		final int sY = n / 2;
		final int sX = sY;
		System.out.println(Arrays.toString(primes));

		double r;
		double theta;
		try {
			for (int i = 1; i < N; i++) {
				r = c * Math.sqrt(i);
				theta = i * GA;
				spaceN.put(i,polarToCartesian(theta, r));
				if (primeFactors[i] == 0)
					g2d.setColor(new Color(0xFFFFFF));
				else
					g2d.setColor(new Color(0x888888));
				g2d.fillOval(spaceN.get(i).x - offset + sX, spaceN.get(i).y - offset + sY, size, size);
			}
			for (int i = 0; i < numPrimes; i++) {
				r = c * Math.sqrt(i);
				theta = i * GA;
				//Point primeOrigin = spaceN.get(primes[i]);
				Point primeOrigin = polarToCartesian(theta, r);
				if (primeFactors[i] == 0) {
					System.out.println(i + " is prime");
					for (int j = primes[i] + primes[i]; j < N; j += primes[i]) {
						//System.out.println("*" + j);
						r = c * Math.sqrt(j);
						theta = j * GA;
						//Point primeTarget = spaceN.get(j);
						Point primeTarget = polarToCartesian(theta, r);
						Color c = getColor(i, primes.length);
						g2d.setStroke(new BasicStroke());
						g2d.setColor(c);
						System.out.println((primeTarget.x-primeOrigin.x) + ", " + (primeTarget.y-primeOrigin.y));
						g2d.draw(new Line2D.Double(primeOrigin.x, primeOrigin.y, primeTarget.x, primeTarget.y));
					}
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Image creation complete. Saving...");
			g2d.setColor(Color.WHITE);
			g2d.draw(new Line2D.Double(100, 100, 400, 400));
		}
	}
	
	private Color getColor(int n, int max){
		double ratio = (double)n/max * STool.TAU;
		int red = (int) (Math.sin(ratio) * 128) + 127;
		int green = (int) (Math.sin(ratio + STool.T2) * 128) + 127;
		int blue = (int) (Math.sin(ratio + STool.T3) * 128) + 127;
		return new Color(STool.rgbToInt(red, green, blue));
	}

	public Point polarToCartesian(double theta, double r) {
		return new Point((int) (r * Math.cos(theta)),
				(int) (r * Math.sin(theta)));
	}

}
