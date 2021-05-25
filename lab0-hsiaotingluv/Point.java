/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2020/21
 *
 * The Point class encapsulates a point on a 2D plane.
 *
 * @author Chen Hsiao Ting
 */
class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	double distance(Point p) {
		double dispX = this.x - p.x;
		double dispY = this.y - p.y;
		return Math.sqrt(dispX * dispX + dispY * dispY);
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
