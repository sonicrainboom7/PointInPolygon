package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.Point;
import main.java.Polygon;

class TestPolygon {

	@Test
	void testPolygon() {
		Polygon polygonAnalyzer = new Polygon();
		boolean onTheEdge;
		Point polygon[] = new Point[] { new Point(0, 0), new Point(0, 2), new Point(2, 2), new Point(2, 0) };

		Point testPoints[] = new Point[] { new Point(1, 1), new Point(3, 4), new Point(0, 1) };

		assertEquals("", true, polygonAnalyzer.isInside(polygon, polygon.length, testPoints[0])); // inside
		assertEquals("", false, polygonAnalyzer.isInside(polygon, polygon.length, testPoints[1])); // outside

		if (polygonAnalyzer.onSegment(polygon[0], testPoints[2], polygon[polygon.length - 1]) == true) {
			onTheEdge = true;
		} else {
			for (int i = 0; i < polygon.length - 1; i++) {
				if (polygonAnalyzer.onSegment(polygon[i], testPoints[2], polygon[i + 1]) == true) {
					onTheEdge = true;
					break;
				} else {
					onTheEdge = false;
				}
				assertEquals("", true, onTheEdge); // On the edge
			}
		}
	}

}
