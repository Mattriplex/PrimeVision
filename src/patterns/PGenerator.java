package patterns;

import java.util.Queue;

import shapes.ShapeGen;
import shapes.Tile;
import colours.CGenerator;
import base.FactorSieve;

public interface PGenerator {
	Queue<Tile> drawPattern(int n, FactorSieve factors, CGenerator cGen, ShapeGen shape);
}
