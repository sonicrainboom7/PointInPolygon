package main.java;

import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextHandler {

	String tmpDir = System.getProperty("java.io.tmpdir"); // file path to temp directory
	private boolean inTestMode;
	
	/**
	 * @param inTestMode
	 */
	public TextHandler(boolean inTestMode) {
		this.inTestMode = inTestMode;
	}
	
	public String newFile(String fileName) {
		fileName = inTestMode ?  tmpDir + fileName : fileName;
		return fileName;
		
	}
	
	/**
	 * @param point
	 */
	public void writePointCoordinates(Point point[]) {
		FileWriter polyCoordinates = null;
		try {
			polyCoordinates = new FileWriter(newFile("pisteet.txt"));
			for (int i = 0; i < point.length; i++) {
				polyCoordinates.write(point[i].x + " " + point[i].y + "\n");

			}
			polyCoordinates.close();
		} catch (IOException e) {
			new RuntimeException(e).printStackTrace();
		}
	};

	/**
	 * @param polygon
	 */
	public void writePolygonCoordinates(Point polygon[]) {
		FileWriter polyCoordinates = null;
		try {
			polyCoordinates = new FileWriter(newFile("polygoni.txt"));
			for (int i = 0; i < polygon.length; i++) {
				polyCoordinates.write(polygon[i].x + " " + polygon[i].y + "\n");

			}
			polyCoordinates.close();
		} catch (IOException e) {
			new RuntimeException(e).printStackTrace();
		}
	};

	/**
	 * @return
	 */
	public Point[] readPoints() {
		Scanner myReader = null;
	    double[] xPoint = new double[100];
	    double[] yPoint = new double[100];
		ArrayList<Point> points = new ArrayList<Point>();
		int i = 0;
		
		try {
				myReader = new Scanner(new File(newFile("pisteet.txt")));
			while (myReader.hasNextDouble()) {

				xPoint[i] = myReader.nextDouble();
				yPoint[i] = myReader.nextDouble();
				points.add(new Point(xPoint[i], yPoint[i]));
				i++;
			}
		} catch (FileNotFoundException e) {
			new RuntimeException(e).printStackTrace();
		}

		Point[] pointsFromFile = points.toArray(new Point[points.size()]);
		return pointsFromFile;
	}

	/**
	 * @return
	 */
	public Point[] readPolygon() {
		Scanner myReader = null;
		double[] xPoint = new double[100];
		double[] yPoint = new double[100];
		ArrayList<Point> polygon = new ArrayList<Point>();
		int i = 0;
		
		try {
				myReader = new Scanner(new File(newFile("polygoni.txt")));
			while (myReader.hasNextDouble()) {

				xPoint[i] = myReader.nextDouble();
				yPoint[i] = myReader.nextDouble();
				polygon.add(new Point(xPoint[i], yPoint[i]));
				i++;
			}
		} catch (FileNotFoundException e) {
			new RuntimeException(e).printStackTrace();
		}

		Point[] polygonFromFile = polygon.toArray(new Point[polygon.size()]);
		return polygonFromFile;
	}

	/**
	 * 
	 */
	
	public void writeResults(Point[] polygonFromFile, Point[] pointsFromFile, Result[] results) {
		FileWriter report = null;
		try {
				report = new FileWriter(newFile("selvitys.txt"));
			for (int i = 0; i < pointsFromFile.length; i++) {
				report.write("Where does point (" + pointsFromFile[i].x + "," + pointsFromFile[i].y + ") lie? :"
						+ results[i].toString() + "\n");
			}
			report.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	
	public Result[] readResults() {
		Scanner myReader = null;
		Result results[] = new Result[3];
		int i = 0;
		try {
				myReader = new Scanner(new File(newFile("selvitys.txt")));
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				if (line.contains(Result.INSIDE.toString())) {
					results[i] = Result.INSIDE;
				} else if (line.contains(Result.OUTSIDE.toString())) {
					results[i] = Result.OUTSIDE;
				} else if (line.contains(Result.BORDER.toString())) {
					results[i] = Result.BORDER;
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
