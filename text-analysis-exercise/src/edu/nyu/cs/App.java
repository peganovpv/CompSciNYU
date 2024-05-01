package edu.nyu.cs;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) throws Exception {
        String filepath = getFilepathFromUser();
        String fullText = getContentsOfFile(filepath);
        String[] tics = getTicsFromUser();

        System.out.println("...............................Analyzing text...............................");
        int totalTics = countTics(fullText, tics);
        double ticDensity = calculateTicDensity(tics , fullText);
        System.out.println("Total number of tics: " + totalTics);
        System.out.printf("Density of tics: %.2f\n", ticDensity);

        System.out.println("...............................Tic breakdown...............................");
        for (String tic : tics) {
            int occurrences = countOccurrences(tic, fullText);
            double ticPercentage = calculatePercentage(occurrences, totalTics);
            System.out.printf("%-10s / %2d occurrences / %2d%% of all tics\n", tic, occurrences, (int)ticPercentage);
        }
    }

    public static String getFilepathFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What file would you like to open?");
        String filepath = scanner.nextLine().trim();
        return filepath;
    }

    public static String getContentsOfFile(String filepath) throws Exception {

            // the code in this function is given to you as a gift... don't change it.
        
            String fullText = "";
            // opening up a file may fail if the file is not there, so use try/catch to protect against such errors
        try {
            // try to open the file and extract its contents
            Scanner scn = new Scanner(new File(filepath));
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                fullText += line + "\n"; // nextLine() removes line breaks, so add them back in
            }
            scn.close(); // be nice and close the Scanner
        }
        catch (FileNotFoundException e) {
              // in case we fail to open the file, output a friendly message.
              System.out.println("Oh no... can't find the file!");
        }
        return fullText; // return the full text
    }

    public static String[] getTicsFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What words would you like to search for?");
        String ticsInput = scanner.nextLine();
        String[] tics = ticsInput.split(",");
        for (int i = 0; i < tics.length; i++) {
            tics[i] = tics[i].trim();
        }
        return tics;
    }

    public static int countTics(String text, String[] tics) {
        int totalTics = 0;
        for (String tic : tics) {
            totalTics += countOccurrences(text, tic);
        }
        return totalTics;
    }

    // count the number of times a word appears in a string if it is not consective
    public static int countOccurrences(String word, String text) {
        int count = 0;
        int index = 0;
        while (index < text.length()) {
            int found = text.indexOf(word, index);
            if (found == -1) {
                break;
            }
            if (found == 0 || !Character.isLetter(text.charAt(found - 1))) {
                int end = found + word.length();
                if (end == text.length() || !Character.isLetter(text.charAt(end))) {
                    count++;
                }
            }
            index = found + 1;
        }
        return count;
    }

    public static double calculateTicDensity(String[] tics, String text) {
        for (int i = 0; i < tics.length; i++) {
            tics[i] = tics[i].toLowerCase();
        }
        int totalTics = countTics(text, tics);
        int totalWords = countWords(text);
        double ticDensity = (double)totalTics / totalWords;
        System.out.println(totalTics);
        System.out.println(totalWords);
        return ticDensity;
    }

    public static int countWords(String text) {
        int count = 0;
        text.split(" ");
        for (String word : text.split(" ")) {
            if (word.length() > 0) {
                count++;
            }
        }
        return count;
    }

    public static int calculatePercentage(int occurences, int totalTics) {
        return (int) ((double) occurences / (double) totalTics * 100);
    }


}
