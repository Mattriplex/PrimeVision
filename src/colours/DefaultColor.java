package colours;

public class DefaultColor implements CGenerator {
	private int PrimeColour;
	private int CompositeColour;

	public DefaultColor(int p, int c) {
		PrimeColour = p;
		CompositeColour = c;
	}

	public int getColour(int factor, int x, int y) {
		if (factor == 0)
			return PrimeColour;
		else
			return CompositeColour;
	}

}
