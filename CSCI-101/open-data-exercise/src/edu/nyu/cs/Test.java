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

public class Test {
    // AM May 2021: 86, 
    // PM May:
    public static void main(String[] args) {
        String[] lines = getLinesFromFile("/Users/peterpeganov/Desktop/CODING/University/CSCI-101/Assignements/open-data-exercise/data/PedCountLocationsMay2015.csv");
        String[][] data = getDataFromLines(lines);
        if(data[1][82].isEmpty()){
            System.out.println("Empty");
        }
        else{
            System.out.println("Not Empty");
        }

        
    }
    public static String[] getLinesFromFile(String filepath) {
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
	public static String[][] getDataFromLines(String[] lines) {
		String[][] data = new String[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(",");
            data[i] = values;
        }
        return data;

	}
}
