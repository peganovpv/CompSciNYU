package edu.nyu.cs;

import java.io.BufferedReader;
// some basic java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.SystemUtils;

import java.util.regex.Matcher;

// some imports used by the UnfoldingMap library
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.OpenStreetMap.*;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MapBox;
import de.fhpotsdam.unfolding.providers.Google.*;
import de.fhpotsdam.unfolding.providers.Microsoft;
// import de.fhpotsdam.unfolding.utils.ScreenPosition;

import edu.nyu.cs.MarkerBubble;

/*
 * - **the_geom** – Geospatial coordinates (longitude, latitude) of the location
- **Borough** – Borough count conducted in
- **Loc** – Count location identifier, ranging from 1-114
- **Street_Nam** – Street counts conducted on
- **From\_/To_Street** – Block of street counts conducted
- **Index** – Indicates if location is included in Mayor’s Management Report Pedestrian Index
- **(Month)(Year)\_AM** – weekday morning count, in the month/year indicated
- **(Month)(Year)\_PM** – weekday evening count, in the month/year indicated
- **(Month)(Year)\_MD** – weekend count, in the month/year indicated
 */


/**
 * A program that pops open an interactive map.
 */
public class App extends PApplet {
	/****************************************************************/
	/*                  BEGIN - DON'T MODIFY THIS CODE              */
	/****************************************************************/
	UnfoldingMap map; // will be a reference to the actual map
	String mapTitle; // will hold the title of the map
	final float SCALE_FACTOR = 0.0002f; // a factor used to scale pedestrian counts to calculate a reasonable radius for a bubble marker on the map
	final int DEFAULT_ZOOM_LEVEL = 11;
	final Location DEFAULT_LOCATION = new Location(40.7286683f, -73.997895f); // a hard-coded NYC location to start with
	String[][] data; // will hold data extracted from the CSV data file
	/****************************************************************/
	/*                    END - DON'T MODIFY THIS CODE              */
	/****************************************************************/


	/**
	 * This method is automatically called every time the user presses a key while viewing the map.
	 * The `key` variable (type char) is automatically is assigned the value of the key that was pressed.
	 * Complete the functions called from here, such that:
	 * 	- when the user presses the `1` key, the code calls the showMay2021MorningCounts method to show the morning counts in May 2021, with blue bubble markers on the map.
	 * 	- when the user presses the `2` key, the code calls the showMay2021EveningCounts method to show the evening counts in May 2021, with blue bubble markers on the map.
	 * 	- when the user presses the `3` key, the code calls the showMay2021EveningMorningCountsDifferencemethod to show the difference between the evening and morning counts in May 2021.  If the evening count is greater, the marker should be a green bubble, otherwise, the marker should be a red bubble.
	 * 	- when the user presses the `4` key, the code calls the showMay2021VersusMay2019Counts method to show the difference between the average of the evening and morning counts in May 2021 and the average of the evening and morning counts in May 2019.  If the counts for 2021 are greater, the marker should be a green bubble, otherwise, the marker should be a red bubble.
	 * 	- when the user presses the `5` key, the code calls the customVisualization1 method to show data of your choosing, visualized with marker types of your choosing.
	 * 	- when the user presses the `6` key, the code calls the customVisualization2 method to show data of your choosing, visualized with marker types of your choosing.
	 */
	public void keyPressed() {
		System.out.println("Key pressed: " + key);
		// complete this method 
		String cwd = Paths.get("").toAbsolutePath().toString();
		String path = Paths.get(cwd, "data", "PedCountLocationsMay2015.csv").toString(); 
		String[] lines = getLinesFromFile(path); 
		data = getDataFromLines(lines); 

		if (key == '1') {
			showMay2021MorningCounts(data);
		} else if (key == '2') {
			showMay2021EveningCounts(data);
		} else if (key == '3') {
			showMay2021EveningMorningCountsDifference(data);
		} else if (key == '4') {
			showMay2021VersusMay2019Counts(data);
		} else if (key == '5') {
			customVisualization1(data);
		} else if (key == '6') {
			customVisualization2(data);
		}

	}

	/**
	 * Adds markers to the map for the morning pedestrian counts in May 2021.
	 * These counts are in the third-to-last field in the CSV data file.  So we look at the third-to-last array element in our data array for these values.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021MorningCounts(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "May 2021 Morning Pedestrian Counts";
		
		float lat;
		float lng;
		for(int i=1; i<data.length;i++){
			
			String[] point = data[i][0].split(" ");
			lng = Float.parseFloat(point[1].substring(1));
			lat = Float.parseFloat(point[2].substring(0, point[2].length()-1));

			// Create a marker for each point
			Location location = new Location(lat, lng);
			// Add morning, evening and weekend counts for may 2021
			if(!(data[i][86].isEmpty())){
				// Subtract data points to find difference
				float radius = Float.parseFloat(data[i][86]);
				radius = radius * SCALE_FACTOR;
				float[] fillColor = {0, 0, 255, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
				MarkerBubble marker = new MarkerBubble(this, location, radius, fillColor); // don't worry about the `this` keyword for now... just make sure it's there.
				map.addMarker(marker);
			} else{
				continue;
			}
			
		}
		
	}

	/**
	 * Adds markers to the map for the evening pedestrian counts in May 2021.
	 * These counts are in the second-to-last field in the CSV data file.  So we look at the second-to-last array element in our data array for these values.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningCounts(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "May 2021 Evening Pedestrian Counts";
		float lat;
		float lng;
		for(int i=1; i<=data.length;i++){
			
			String[] point = data[i][0].split(" ");
			lng = Float.parseFloat(point[1].substring(1));
			lat = Float.parseFloat(point[2].substring(0, point[2].length()-1));

			Location location = new Location(lat, lng);

			if(!(data[i][87].isEmpty())){
				// Subtract data points to find difference
				float radius = Float.parseFloat(data[i][87]);
				radius = radius * SCALE_FACTOR;
				float[] fillColor = {0, 0, 255, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
				MarkerBubble marker = new MarkerBubble(this, location, radius, fillColor); // don't worry about the `this` keyword for now... just make sure it's there.
				map.addMarker(marker);
			} else{
				continue;
			}
		}
	}

	/**
	 * Adds markers to the map for the difference between evening and morning pedestrian counts in May 2021.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021EveningMorningCountsDifference(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 Evening and Morning Pedestrian Counts";
		// complete this method
		float lat;
		float lng;
		for(int i=1; i<data.length;i++){
			
			String[] point = data[i][0].split(" ");
			lng = Float.parseFloat(point[1].substring(1));
			lat = Float.parseFloat(point[2].substring(0, point[2].length()-1));

			// Create a marker for each point
			Location location = new Location(lat, lng);
			// Subtract data points to find difference
			if(!(data[i][87].isEmpty() || data[i][86].isEmpty())){
				float radius = Float.parseFloat(data[i][87]) - Float.parseFloat(data[i][86]);
				radius = radius * SCALE_FACTOR;
				float[] fillColor = {0, 0, 255, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
				MarkerBubble marker = new MarkerBubble(this, location, radius, fillColor); // don't worry about the `this` keyword for now... just make sure it's there.
				map.addMarker(marker);
			} else{
				continue;
			}
		}
	}

	/**
	 * Adds markers to the map for the difference between the average pedestrian count in May 2021 and the average pedestrian count in May 2019.
	 * Note that some pedestrian counts were not done in May 2019, and the data is blank.  
	 * Do not put a marker at those locations with missing data.
	 * 
	 * @param data A two-dimensional String array, containing the data returned by the getDataFromLines method.
	 */
	public void showMay2021VersusMay2019Counts(String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "Difference Between May 2021 and May 2019 Pedestrian Counts";
		
		float lat;
		float lng;
		for(int i=1; i<data.length;i++){
			
			String[] point = data[i][0].split(" ");
			lng = Float.parseFloat(point[1].substring(1));
			lat = Float.parseFloat(point[2].substring(0, point[2].length()-1));

			// Create a marker for each point
			Location location = new Location(lat, lng);
			// Add morning, evening and weekend counts for may 2021
			if(!(data[i][82].isEmpty() || data[i][81].isEmpty() || data[i][80].isEmpty() || data[i][88].isEmpty() || data[i][87].isEmpty() || data[i][86].isEmpty())){
				float may2021 = Float.parseFloat(data[i][88]) + Float.parseFloat(data[i][87]) + Float.parseFloat(data[i][86]);
				// Add morning, evening and weekend counts for may 2019
				float may2019 = Float.parseFloat(data[i][80]) + Float.parseFloat(data[i][81]) + Float.parseFloat(data[i][82]);
				// Subtract data points to find difference
				float radius = may2021 - may2019;
				radius = radius * SCALE_FACTOR;
				float[] fillColor = {0, 0, 255, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
				MarkerBubble marker = new MarkerBubble(this, location, radius, fillColor); // don't worry about the `this` keyword for now... just make sure it's there.
				map.addMarker(marker);
			} else{
				continue;
			}
			
		}
	}

	/**
	 * A data visualization of your own choosing.  
	 * Do some data analysis and map the results using markers.
	 * 
	 * @param data
	 */
	public void customVisualization1 (String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "October 2020 Pedestrian Count";
		// complete this method
		// October 2020 Weekend at index 85
		float lat;
		float lng;
		for(int i=1; i<data.length;i++){
			
			String[] point = data[i][0].split(" ");
			lng = Float.parseFloat(point[1].substring(1));
			lat = Float.parseFloat(point[2].substring(0, point[2].length()-1));

			// Create a marker for each point
			Location location = new Location(lat, lng);
			if(!(data[i][85].isEmpty())){
				float radius = Float.parseFloat(data[i][85]) * SCALE_FACTOR;
				float[] fillColor = {0, 0, 255, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
				MarkerBubble marker = new MarkerBubble(this, location, radius, fillColor); // don't worry about the `this` keyword for now... just make sure it's there.
				map.addMarker(marker);
			}
		}
		

	}

	/**
	 * A data visualization of your own choosing.  
	 * Do some data analysis and map the results using markers.
	 * 
	 * @param data
	 */
	public void customVisualization2 (String[][] data) {
		clearMap(); // clear any markers previously placed on the map
		mapTitle = "September 2018 Evening Pedestrian Count";
		// complete this method	
		// September 2018 Evening at index 78
		float lat;
		float lng;
		for(int i=1; i<data.length;i++){
			
			String[] point = data[i][0].split(" ");
			lng = Float.parseFloat(point[1].substring(1));
			lat = Float.parseFloat(point[2].substring(0, point[2].length()-1));

			// Create a marker for each point
			Location location = new Location(lat, lng);
			if(!(data[i][78].isEmpty())){
				float radius = Float.parseFloat(data[i][78]) * SCALE_FACTOR;
				float[] fillColor = {0, 0, 255, 127}; // a color, specified as a combinatino of red, green, blue, and alpha (i.e. transparency), each represented as numbers between 0 and 255.
				MarkerBubble marker = new MarkerBubble(this, location, radius, fillColor); // don't worry about the `this` keyword for now... just make sure it's there.
				map.addMarker(marker);
			}
		}
	}

	/** Done
	 * Opens a file and returns an array of the lines within the file, as Strings with their line breaks removed.
	 * 
	 * @param filepath The filepath to open
	 * @return A String array, where each String contains the text of a line of the file, with its line break removed.
	 * @throws FileNotFoundException
	 */
	public String[] getLinesFromFile(String filepath) {
		String filePath = "/Users/peterpeganov/Desktop/CODING/University/CSCI-101/Assignements/open-data-exercise/data/PedCountLocationsMay2015.csv"; 
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return new String[0];
        }
	}

	/** Done
	 * Takes an array of lines of text in comma-separated values (CSV) format and splits each line into a sub-array of data fields.
	 * For example, an argument such as {"1,2,3", "100,200,300"} could result in a returned array { {"1", "2", "3"}, {"100", "200", "300"} }
	 * This method must skip any lines that don't contain mappable data (i.e. don't have any geospatial data in them) 
	 * and do any other cleanup of the data necessary for it to be easily mapped by other code in the program.
	 *
	 * @param lines A String array of lines of text, where each line is in comma-separated values (CSV) format.
	 * @return A two-dimensional String array, where each inner array contains the data from one of the lines, split by commas.
	 */
	public String[][] getDataFromLines(String[] lines) {
		String[][] data = new String[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            data[i] = values;
        }
        return data;

	}

	// FINISHED


	/****************************************************************/
	/**** YOU WILL MOST LIKELY NOT NEED TO EDIT ANYTHING BELOW HERE */
	/****               Proceed at your own peril!                  */
	/****************************************************************/

	/**
	 * This method will be automatically called when the program runs
	 * Put any initial setup of the window, the map, and markers here.
	 */
	public void setup() {
		size(1200, 800, P2D); // set the map window size, using the OpenGL 2D rendering engine
		// size(1200, 800); // set the map window size, using Java's default rendering engine (try this if the OpenGL doesn't work for you)
		map = getMap(); // create the map and store it in the global-ish map variable

		// load the data from the file... you will have to complete the functions called to make sure this works
		try {
			String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
			String path = Paths.get(cwd, "data", "PedCountLocationsMay2015.csv").toString(); // e.g "data/PedCountLocationsMay2015.csv" on Mac/Unix vs. "data\PedCountLocationsMay2015.csv" on Windows
			String[] lines = getLinesFromFile(path); // get an array of the lines from the file.
			data = getDataFromLines(lines); // get a two-dimensional array of the data in these lines; complete the getDataFromLines method so the data from the file is returned appropriately
			// System.out.println(Arrays.deepToString(data)); // for debugging

			// automatically zoom and pan into the New York City location
			map.zoomAndPanTo(DEFAULT_ZOOM_LEVEL, DEFAULT_LOCATION);

			// by default, show markers for the morning counts in May 2021 (the third-to-last field in the CSV file)
			showMay2021MorningCounts(data);

			// various ways to zoom in / out
			// map.zoomLevelOut();
			// map.zoomLevelIn();
			// map.zoomIn();
			// map.zoomOut();

			// it's possible to pan to a location or a position on the screen
			// map.panTo(nycLocation); // pan to NYC
			// ScreenPosition screenPosition = new ScreenPosition(100, 100);
			// map.panTo(screenPosition); // pan to a position on the screen

			// zoom and pan into a location
			// int zoomLevel = 10;
			// map.zoomAndPanTo(zoomLevel, nycLocation);

			// it is possible to restrict zooming and panning
			// float maxPanningDistance = 30; // in km
			// map.setPanningRestriction(nycLocation, maxPanningDistance);
			// map.setZoomRange(5, 22);


		}
		catch (Exception e) {
			System.out.println("Error: could not load data from file: " + e);
		}

	} // setup

	/**
	 * Create a map using a publicly-available map provider.
	 * There will usually not be a need to modify this method. However, in some cases, you may need to adjust the assignment of the `map` variable.
	 * If there are error messages related to the Map Provider or with loading the map tile image files, try all of the other commented-out map providers to see if one works.
	 * 
	 * @return A map object.
	 */
	private UnfoldingMap getMap() {
		// not all map providers work on all computers.
		// if you have trouble with the one selected, try the others one-by-one to see which one works for you.
		map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		// map = new UnfoldingMap(this, new Microsoft.AerialProvider());
		// map = new UnfoldingMap(this, new GoogleMapProvider());
		// map = new UnfoldingMap(this);
		// map = new UnfoldingMap(this, new OpenStreetMapProvider());

		// enable some interactive behaviors
		MapUtils.createDefaultEventDispatcher(this, map);
		map.setTweening(true);
		map.zoomToLevel(DEFAULT_ZOOM_LEVEL);

		return map;
	}
	
	/**
	 * This method is called automatically to draw the map.
	 * This method is given to you.
	 * There is no need to modify this method.
	 */
	public void draw() {
		background(0);
		map.draw();
		drawTitle();
	}

	/**
	 * Clear the map of all markers.
	 * This method is given to you.
	 * There is no need to modify this method.
	 */
	public void clearMap() {
		map.getMarkers().clear();
	}

	/**
	 * Draw the title of the map at the bottom of the screen
	 */
	public void drawTitle() {
		fill(0);
		noStroke();
		rect(0, height-40, width, height-40); // black rectangle
		textAlign(CENTER);
		fill(255);
		text(mapTitle, width/2, height-15); // white centered text
	}

	/**
	 * The main method is automatically called when the program runs.
	 * This method is given to you.
	 * There is no need to modify this method.
	 * @param args A String array of command-line arguments.
	 */
	public static void main(String[] args) {
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.App"); // do not modify this!
		}
	}

}
