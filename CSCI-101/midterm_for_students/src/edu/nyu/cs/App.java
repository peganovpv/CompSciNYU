package edu.nyu.cs;

import java.util.Arrays;
import java.util.Scanner;
public class App {
    // Implement the body of a function that uses a while loop to continuously
    // listen to lines typed by a user until they say the lines "yes" or "no".
    // If they say "yes" return true and if they say "no" return false . The
    // user must use the right capitalization but spaces are allowed. Use if/else statements
    public static boolean userChoiceIfElse() {
        Scanner scnr = new Scanner(System.in);

        while (true) {
            String line = scnr.nextLine().trim();
            if (line.equals("yes")) {
                scnr.close();
                return true;
            } else if (line.equals("no")) {
                scnr.close();
                return false;
            }
        }
        

    }

    // Implement the body of a function that uses a while loop to continuously
    // listen to lines typed by a user until they say the lines "yes" or "no".
    // If they say "yes" return true and if they say "no" return false . The
    // user must use the right capitalization but spaces are allowed. Use switch/case statements
    public static boolean userChoiceSwitchCase() {
        Scanner scnr = new Scanner(System.in);

        while(true){
            String line = scnr.nextLine().trim();
            switch(line){
                case "yes":
                    scnr.close();
                    return true;
                case "no":
                    scnr.close();
                    return false;
            }
        }
    }

    // return the number of non-zero values in an array
    public static int nonZeroValues(int[] values) {
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != 0) {
                count++;
            }
        }
        return count;
    }

    // return a sorted copy of the input array without modifying the input data
    public static int[] sortWithReturn(int[] values) {
        int[] sorted = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            sorted[i] = values[i];
        }
        Arrays.sort(sorted);
        return sorted;
    }

    // Sort by modifying the input data
    public static void sortInline(int[] values) {
        Arrays.sort(values);
    }

    // return string before \0
    public static String nullTerminatedString(String value) {
        int index = value.indexOf('\0');
        if (index == -1) {
            return value;
        } else {
            return value.substring(0, index);
        }
    }

    // checkerboard
    public static void checkerboard(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("x");
                } else {
                    System.out.print("o");
                }
            }
            System.out.println();
        }
    }
}
