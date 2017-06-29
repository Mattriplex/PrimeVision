package shapes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Circle implements ShapeGen {

	public Shape getShape(double x, double y, int size, double angle) {
		int offset = size/2;
		return new Ellipse2D.Float((float)(x-offset), (float)(y-offset), (float)size, (float)size);
	}

}
