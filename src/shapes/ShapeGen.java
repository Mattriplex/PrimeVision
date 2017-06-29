package shapes;

import java.awt.Shape;

public interface ShapeGen {
	public Shape getShape(double x, double y, int size, double angle);
}
