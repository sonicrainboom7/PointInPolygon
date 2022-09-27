package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.*;

class TestPoint {

	@Test
	void testPoint() {
		int a = 1;
		int b = 2;
		Point point = new Point(a, b);

		Point expectedPoint = point.getPoint();

		assertEquals(expectedPoint, point);

	}

}
