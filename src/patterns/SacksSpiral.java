package patterns;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import base.FactorSieve;
import base.STool;
import colours.CGenerator;

public class SacksSpiral implements PGenerator {
	private int size;
	public SacksSpiral(int size){
		this.size = size;
	}

	public void drawPattern(int n, FactorSieve factors, CGenerator cGen, Graphics2D g2d) {
		int[] NF = factors.getFactors();
		final int sY = n / 2;
		final int sX = sY;
		int offset = size/2;


		int i = 1;
		double r;
		double theta;
		try {
			while (true) {
				r = size*Math.sqrt(i)*1.05;
				theta = Math.sqrt(i);
				Point xy = polarToCartesian(theta, r);
				g2d.setColor(new Color(cGen.getColour(NF[i], xy.x+ sY, xy.y + sY)));
				g2d.fillOval(xy.x - offset + sX, xy.y - offset + sY, size, size);
				i++;
				}
			

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Image creation complete. Saving...");
		}
	}

	
	public Point polarToCartesian(double theta, double r) {
		return new Point((int)(r*Math.cos(theta*STool.TAU)), (int)(r*Math.sin(theta*STool.TAU)));
	}
}
