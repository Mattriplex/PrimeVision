package shapes;

import java.awt.Rectangle;
import java.awt.Shape;

public class Square implements ShapeGen {

	public Shape getShape(double x, double y, int side, double angle) {
		int half = side / 2;
		return new Rectangle((int)Math.round(x-half), (int)Math.round(y-half), side, side);
	}

}
