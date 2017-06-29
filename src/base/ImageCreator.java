package base;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;

import javax.imageio.ImageIO;

import colours.*;
import defunct.UlamBasic;
import patterns.*;
import shapes.*;

public class ImageCreator {
	
	// the highest number of factors of any number in the specified range
	private static int maxFactor;
	
	// the prime factor calculator
	private static FactorSieve pFactors;
	
	// length of specified range (0 to n)
	private static int n;

	public static void run() {

		// read image dimensions and output file name
		Scanner iread = new Scanner(System.in);
		System.out.print("Image dimension: ");
		n = iread.nextInt();
		System.out.print("Output File Name: ");
		String fileName = iread.next();
		fileName += ".png";

		// make user specify pattern/colour generator and weight function
		PGenerator god = requestPgen(iread);
		CGenerator magic = requestCgen(iread);
		ShapeGen xAgon = requestSgen(iread);
		iread.close();

		// create pattern according to specifications
		BufferedImage img = new BufferedImage(n, n, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Queue<Tile> pattern = god.drawPattern(n, pFactors, magic, xAgon);
		System.out.println("Pattern creation complete. Drawing image...");
		
		// draw pattern to image
		while (!pattern.isEmpty()){
			Tile actual = pattern.poll();
			g2d.setColor(new Color(actual.colour));
			g2d.fill(actual.shape);
		}
		System.out.println("Image drawn. Saving...");

		// save image to file
		try {
			File output = new File(fileName);
			ImageIO.write(img, "png", output);
		} catch (IOException e) {
			System.err.println("Saving failed!");
		}
		System.out.println("Image saved.");
	}

	private static PGenerator requestPgen(Scanner read) {
		System.out.println("Pattern Generator:" + "\n0 PixelUlam (default)"
				+ "\n1 UlamShapes" + "\n2 SizeScale" + "\n3 CrissCross"
				+ "\n4 KlauberTriangle" + "\n5 TriangleUlam"
				+ "\n6 FermatSpiral" + "\n7 SacksSpiral\n8 PrimeWeb");
		int userChoice = read.nextInt();
		switch (userChoice) {
		case 1:
			System.out.print("Shape size: ");
			int size = read.nextInt();
			pFactors = new FactorSieve(n * n / size / size);
			maxFactor = pFactors.getMaxFactor();
			return new SquareSpiral(size);
		case 2:
			System.out.print("Minimum shape size: ");
			int minSize = read.nextInt();
			System.out.print("Maximum shape size: ");
			int maxSize = read.nextInt();
			System.out.print("Size Offset scale (0-1):");
			double pct = read.nextDouble();
			int factorOffset = (int) (minSize + pct * (maxSize - minSize));
			pFactors = new FactorSieve(n * n / factorOffset / factorOffset);
			maxFactor = pFactors.getMaxFactor();
			WeightFunction fWeight = requestWfunc(read);
			return new SizeScale(minSize, maxSize, maxFactor, fWeight, pct);
		case 3:
			System.out.print("Cross side length: 3*");
			size = read.nextInt();
			pFactors = new FactorSieve(n * n / size / size / 9);
			maxFactor = pFactors.getMaxFactor();
			return new CrissCross(size);
		case 4:
			System.out.print("Shape size: ");
			int size1 = read.nextInt();
			pFactors = new FactorSieve((n * n - n) / size1);
			return new KlauberTriangle(n, size1);
		case 5:
			pFactors = new FactorSieve(n * n);
			maxFactor = pFactors.getMaxFactor();
			System.out.print("Triangle Side length: ");
			return new TriangleSpiral(read.nextInt());
		case 6:
			System.out.print("c: ");
			double constC = read.nextDouble();
			System.out.print("Circle Size: ");
			size1 = read.nextInt();
			pFactors = new FactorSieve((int) (Math.sqrt(n) * n));
			maxFactor = pFactors.getMaxFactor();
			return new FermatSpiral(constC, size1);
		case 7:
			pFactors = new FactorSieve((int) (1.2 * Math.sqrt(n) * n));
			maxFactor = pFactors.getMaxFactor();
			System.out.print("size: ");
			return new SacksSpiral(read.nextInt());
		case 8:
			pFactors = new FactorSieve((int) (Math.sqrt(n) * n));
			maxFactor = pFactors.getMaxFactor();
			System.out.print("c: ");
			constC = read.nextDouble();
			System.out.print("Circle Size: ");
			size1 = read.nextInt();
			return new PrimeWeb(constC, size1);
			
		default:
			pFactors = new FactorSieve(n * n);
			maxFactor = pFactors.getMaxFactor();
			return new UlamBasic();
		}

	}

	private static CGenerator requestCgen(Scanner read) {
		System.out
				.println("Color Model:\n0 Bicolour (default)\n1 Radial Rainbow"
						+ "\n2 Monochrome Scale" + "\n3 Radial2"
						+ "\n4 SpiralVisual" + "\n5 FactorGradient" + "\n6 CenterRainbow");
		int userChoice = read.nextInt();
		switch (userChoice) {
		case 1:
			WeightFunction fWeight = requestWfunc(read);
			return new RadialRainbow(maxFactor, n / 2, fWeight);
		case 2:
			System.out.print("Offset (0-255): ");
			int offSet = read.nextInt();
			System.out.println("Invert colours? (true/false)");
			boolean negative = read.nextBoolean();
			return new MonoScale(maxFactor, offSet % 256, negative);
		case 3:
			System.out
					.print("Gradient origin location (enter Coordinates or -1 to use centre of image:\nX: ");
			userChoice = read.nextInt();
			if (userChoice == -1)
				return new RadialRainbow2(n / 2, n / 2);
			else {
				System.out.print("Y: ");
				return new RadialRainbow2(userChoice, read.nextInt());
			}
		case 4:
			System.out.print("Number of repetitions: ");
			return new Spiralvisual(n * n, read.nextInt());
		case 5:
			System.out.print("Range (0-1): ");
			double range = read.nextDouble();
			System.out.print("Offset (0-1): ");
			double offs = read.nextDouble();
			fWeight = requestWfunc(read);
			return new FactorGradient(maxFactor, range, offs, fWeight);
		case 6:
			fWeight = requestWfunc(read);
			return new CenterRainbow(maxFactor, n/2, fWeight);
		default:
			System.out.print("Prime Colour (Hex): ");
			int p = read.nextInt(16);
			System.out.print("Composite Colour (Hex): ");
			int c = read.nextInt(16);
			return new DefaultColor(p, c);
		}
	}

	private static WeightFunction requestWfunc(Scanner read) {
		System.out.println("Weight Function (if applicable):"
				+ "\n1 linear (default)" + "\n2 logarithmic\n3 linear root");
		return new WeightFunction(read.nextInt());
	}
	
	private static ShapeGen requestSgen(Scanner read) {
		System.out.println("Shape:\n0 Circle (default)\n1 Regular Square \n2 Regular X-agon");
		int userChoice = read.nextInt();
		switch(userChoice){
		case 1:
			return new Square();
		case 2:
			System.out.print("Number of Vertices: ");
			int nVert = read.nextInt();
			return new RegularXagon(nVert);
		default:
			return new Circle();
		}
	}

}
