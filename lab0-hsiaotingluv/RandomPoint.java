import java.util.Random;

public class RandomPoint extends Point {
	private static Random rng = new Random(1);

	public RandomPoint(double minX, double maxX, double minY, double maxY) { 
		// similar to Point(x, y);
		// calling the superclass, which is Point class
		super(minX + rng.nextDouble() * (maxX-minX), 
			minY + rng.nextDouble() * (maxY-minY));
	}

	public static void setSeed(int seed) {
		rng = new Random(seed);
	}
}
