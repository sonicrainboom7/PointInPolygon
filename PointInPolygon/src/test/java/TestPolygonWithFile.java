package test.java;

import main.java.Point;
import main.java.TextHandler;
import main.java.Result;

public class TestPolygonWithFile extends AbstractPolygonTest {

	/**
	 * @param polygon
	 * @return
	 */
	@Override
	public Point[] getTestPolygon(Point polygon[]) {
		TextHandler polygonWriter = new TextHandler(true);
		polygonWriter.writePolygonCoordinates(polygon);
		Point[] polygonFromFile = polygonWriter.readPolygon();
		return polygonFromFile;
	}

	/**
	 * @param points
	 * @return
	 */
	@Override
	public Point[] getTestPoints(Point points[]) {
		TextHandler polygonWriter = new TextHandler(true);
		polygonWriter.writePointCoordinates(points);
		Point[] pointsFromFile = polygonWriter.readPoints();
		return pointsFromFile;
	}

	/**
	 *@param polygon
	 *@param points
	 *@return
	 */
	@Override
	public Result[] getTestResults(Point polygon[], Point points[]) { //
		TextHandler polygonWriter = new TextHandler(true);
		polygonWriter.writeResults(polygon, points, super.getTestResults(polygon, points));
		Result resultsFromFile[] = polygonWriter.readResults();
		return resultsFromFile;
	}

}
