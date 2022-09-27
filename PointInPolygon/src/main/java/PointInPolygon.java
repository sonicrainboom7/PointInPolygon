package main.java;

public class PointInPolygon {

	public static void main(String[] args) {
		Polygon polygonAnalyzer = new Polygon();

		Point polygon[] = polygonAnalyzer
				.getPolygonFromFile(new Point[] { new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0) });

		Point testPoints[] = polygonAnalyzer
				.getPointsFromFile(new Point[] { new Point(1, 1), new Point(3, 3), new Point(0, 1) });

		polygonAnalyzer.getResultsFromFile(polygon, testPoints);

	}

}