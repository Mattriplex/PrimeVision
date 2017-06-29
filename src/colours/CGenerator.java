package colours;

public interface CGenerator {
	static final double TAU = 2 * Math.PI;
	static final double T2 = TAU / 3;
	static final double T3 = 2 * TAU / 3;

	public int getColour(int fac, int x, int y);
}
