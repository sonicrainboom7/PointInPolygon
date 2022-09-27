package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.Point;
import main.java.Polygon;
import main.java.TextHandler;
import main.java.Result;
//import test.java.TestPolygonWithFile;

public abstract class AbstractPolygonTest {

	/**
	 * @param result
	 */
	public void pointInside(Result result) {
		assertEquals("INSIDE", result.toString());
	}

	/**
	 * @param result
	 */
	public void pointOutside(Result result) {
		assertEquals("OUTSIDE", result.toString());
	}

	/**
	 * @param result
	 */
	public void pointOnBorder(Result result) {
		assertEquals("BORDER", result.toString());
	}

	/**
	 * @param polygon
	 * @return
	 */
	public Point[] getTestPolygon(Point polygon[]) {
		return polygon;
	}

	/**
	 * @param polygon
	 * @return
	 */
	public Point[] getPolygon(Point polygon[]) {
		TextHandler polygonWriter = new TextHandler(true);
		polygonWriter.writePolygonCoordinates(polygon);
		return polygon;
	}

	/**
	 * @param points
	 * @return
	 */
	public Point[] getTestPoints(Point points[]) {
		return points;
	}

	/**
	 * @param points
	 * @return
	 */
	public Point[] getPoints(Point points[]) {
		TextHandler polygonWriter = new TextHandler(true);
		polygonWriter.writePointCoordinates(points);
		return points;
	}

	/**
	 * @param polygon
	 * @param points
	 * @return
	 */
	public Result[] getTestResults(Point polygon[], Point points[]) {
		Result results[] = new Result[3];
		Polygon polygonAnalyzer = new Polygon();

		for (int i = 0; i < points.length; i++) {
			polygonAnalyzer.isInside(polygon, polygon.length, points[i]);
			results[i] = polygonAnalyzer.result;
		}
		return results;
	}

	/**
	 * 
	 */
	@Test
	void testBasicPolygon() {
		Point polygon[] = getTestPolygon(
				new Point[] { new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0) });

		Point testPoints[] = getTestPoints(new Point[] { new Point(1, 1), new Point(3, 3), new Point(0, 1) });

		Result results[] = getTestResults(polygon, testPoints);

		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}

	/**
	 * 
	 */
	@Test
	void testTriangle() {
		Point polygon[] = getTestPolygon(new Point[] { new Point(0, 0), new Point(5, 0), new Point(2.5f, 5) });

		Point testPoints[] = getTestPoints(new Point[] { new Point(2.5f, 2), new Point(10, 10), new Point(2.5f, 0) });

		Result results[] = getTestResults(polygon, testPoints);

		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}

	/**
	 * 
	 */
	@Test
	void testRectangle() {

		Point polygon[] = getTestPolygon(
				new Point[] { new Point(0, 0), new Point(5, 0), new Point(2, 5), new Point(7, 5) });

		Point testPoints[] = getTestPoints(new Point[] { new Point(3, 1), new Point(10, 10), new Point(2, 0) });

		Result results[] = getTestResults(polygon, testPoints);

		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}

	/**
	 * 
	 */
	@Test
	void testMShape() {

		Point polygon[] = getTestPolygon(new Point[] { new Point(0, 0), new Point(2, 0), new Point(2, 3),
				new Point(4, 0), new Point(6, 3), new Point(6, 0), new Point(8, 0), new Point(8, 5), new Point(0, 5) });

		Point testPoints[] = getTestPoints(new Point[] { new Point(1, 1), new Point(10, 10), new Point(1, 0) });

		Result results[] = getTestResults(polygon, testPoints);

		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}

	/**
	 * 
	 */
	@Test
	void testBasicPolygonWithFile() {

		TestPolygonWithFile filePolygon = new TestPolygonWithFile();

		Point polygon[] = filePolygon
				.getTestPolygon(new Point[] { new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0) });

		Point testPoints[] = filePolygon
				.getTestPoints(new Point[] { new Point(1, 1), new Point(3, 3), new Point(0, 1) });

		Result results[] = filePolygon.getTestResults(polygon, testPoints);
		
		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}

	/**
	 * 
	 */
	@Test
	void testTriangleWithFile() {

		TestPolygonWithFile filePolygon = new TestPolygonWithFile();

		Point polygon[] = filePolygon
				.getTestPolygon(new Point[] { new Point(0, 0), new Point(5, 0), new Point(2.5f, 5) });

		Point testPoints[] = filePolygon
				.getTestPoints(new Point[] { new Point(2.5f, 2), new Point(10, 10), new Point(2.5f, 0) });

		Result results[] = filePolygon.getTestResults(polygon, testPoints);

		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}

	/**
	 * 
	 */
	@Test
	void testRectangleWithFile() {

		TestPolygonWithFile filePolygon = new TestPolygonWithFile();

		Point polygon[] = filePolygon
				.getTestPolygon(new Point[] { new Point(0, 0), new Point(5, 0), new Point(2, 5), new Point(7, 5) });

		Point testPoints[] = filePolygon
				.getTestPoints(new Point[] { new Point(3, 1), new Point(10, 10), new Point(2, 0) });

		Result results[] = filePolygon.getTestResults(polygon, testPoints);

		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}

	/**
	 * 
	 */
	@Test
	void testMShapeWithFile() {

		TestPolygonWithFile filePolygon = new TestPolygonWithFile();

		Point polygon[] = filePolygon.getTestPolygon(new Point[] { new Point(0, 0), new Point(2, 0), new Point(2, 3),
				new Point(4, 0), new Point(6, 3), new Point(6, 0), new Point(8, 0), new Point(8, 5), new Point(0, 5) });

		Point testPoints[] = filePolygon
				.getTestPoints(new Point[] { new Point(1, 1), new Point(10, 10), new Point(1, 0) });

		Result results[] = filePolygon.getTestResults(polygon, testPoints);

		pointInside(results[0]);
		pointOutside(results[1]);
		pointOnBorder(results[2]);
	}
}