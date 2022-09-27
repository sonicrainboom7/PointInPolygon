package main.java;

//Source: https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/
public class Polygon {

	static double inf = 10000;
	public boolean onTheEdge;
	public Result result;
	
	/**
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	// Given three colinear points p, q, r,
	// the function checks if point q lies
	// on line segment 'pr'
	public boolean onSegment(Point p, Point q, Point r) {
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y)
				&& q.y >= Math.min(p.y, r.y)) {
			onTheEdge = true;
			result = Result.BORDER;
			return true; // On the edge
		}
		return false; // Not on the edge
	}

	// To find orientation of ordered triplet (p, q, r).
	// The function returns following values
	// 0 --> p, q and r are collinear
	// 1 --> Clockwise
	// 2 --> Counterclockwise
	/**
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	public int orientation(Point p, Point q, Point r) {
		double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

		if (val == 0) {
			return 0; // collinear
		}
		return (val > 0) ? 1 : 2; // clock or counterclock wise
	}

	// The function that returns true if
	// line segment 'p1q1' and 'p2q2' intersect.
	/**
	 * @param p1
	 * @param q1
	 * @param p2
	 * @param q2
	 * @return
	 */
	public boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
		// Find the four orientations needed for
		// general and special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		// General case
		if (o1 != o2 && o3 != o4) {

			return true;
		}

		// Special Cases
		// p1, q1 and p2 are collinear and
		// p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1)) {

			return true;
		}

		// p1, q1 and p2 are collinear and
		// q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1)) {

			return true;
		}

		// p2, q2 and p1 are collinear and
		// p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2)) {

			return true;
		}

		// p2, q2 and q1 are collinear and
		// q1 lies on segment p2q2
		if (o4 == 0 && onSegment(p2, q1, q2)) {

			return true;
		}

		// Doesn't fall in any of the above cases

		return false;
	}

	// Returns true if the point p lies
	// inside the polygon[] with n vertices
	/**
	 * @param polygon
	 * @param n
	 * @param p
	 * @return
	 */
	public boolean isInside(Point polygon[], int n, Point p) {
		result = Result.OUTSIDE;
		onTheEdge = false;
		// There must be at least 3 vertices in polygon[]
		if (n < 3) {
			return false;
		}

		// Create a point for line segment from p to infinite
		Point extreme = new Point(inf, p.y);

		// To count number of points in polygon
		// whose y-coordinate is equal to
		// y-coordinate of the point
		int decrease = 0;

		// Count intersections of the above line
		// with sides of polygon
		int count = 0, i = 0;
		do {
			int next = (i + 1) % n;

			if (polygon[i].y == p.y)
				decrease++;

			// Check if the line segment from 'p' to
			// 'extreme' intersects with the line
			// segment from 'polygon[i]' to 'polygon[next]'
			if (doIntersect(polygon[i], polygon[next], p, extreme)) {
				// If the point 'p' is collinear with line
				// segment 'i-next', then check if it lies
				// on segment. If it lies, return true, otherwise false
				if (orientation(polygon[i], p, polygon[next]) == 0) {
					return onSegment(polygon[i], p, polygon[next]);
				}

				count++;
			}
			i = next;
		} while (i != 0);

		// Reduce the count by decrease amount
		// as these points would have been added twice
		count -= decrease;
		if (count % 2 == 0 && result != Result.BORDER) {
			result = Result.OUTSIDE;
		} else {
			result = Result.INSIDE;
		}
		// Return true if count is odd, false otherwise
		return (count % 2 == 1);
	}

	/**
	 * @param polygon
	 * @return
	 */
	public Point[] getPolygonFromFile(Point polygon[]) {
		TextHandler polygonWriter = new TextHandler(false);
		polygonWriter.writePolygonCoordinates(polygon);
		Point[] polygonFromFile = polygonWriter.readPolygon();
		return polygonFromFile;
	}

	/**
	 * @param points
	 * @return
	 */
	public Point[] getPointsFromFile(Point points[]) {
		TextHandler polygonWriter = new TextHandler(false);
		polygonWriter.writePointCoordinates(points);
		Point[] pointsFromFile = polygonWriter.readPoints();
		return pointsFromFile;
	}
	
	/**
	 * @param polygon
	 * @param points
	 * @return
	 */
	public Result[] getResults(Point polygon[], Point points[]) {
		Result results[] = new Result[3];
		Polygon polygonAnalyzer = new Polygon();

		for (int i = 0; i < points.length; i++) {
			polygonAnalyzer.isInside(polygon, polygon.length, points[i]);
			results[i] = polygonAnalyzer.result;
		}
		return results;
	}

	/**
	 *@param polygon
	 *@param points
	 *@return
	 */
	public Result[] getResultsFromFile(Point polygon[], Point points[]) { //
		TextHandler polygonWriter = new TextHandler(false);
		polygonWriter.writeResults(polygon, points, getResults(polygon, points));
		Result results[] = polygonWriter.readResults();
		return results;
	}
}
