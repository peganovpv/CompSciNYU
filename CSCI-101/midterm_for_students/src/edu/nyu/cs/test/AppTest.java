// DO NOT TOUCH THIS FILE!
package edu.nyu.cs.test;

// import junit 4 testing framework
import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import edu.nyu.cs.App;
// import static org.mockito.Mockito.*;\
import java.util.Arrays;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule; // system rules lib - useful for capturing system output
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class AppTest {
    @ClassRule
    public static final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule public final SystemOutRule systemOutRule = new SystemOutRule();

    @Before
    public void init() {
        // any pre-test setup here
    }

    @Test
    public void testUserChoiceIfElse() {
        String[][] expecteds = {
            {"hi", "", "foo/bar/baz", " yes"},
            {"", "yes", "foo/bar/baz"},
            {"hi", "", " yes"},
            {"", "foo/bar/baz", "No", "yes"},
            {"no", "", " yes"},
            {"No", "yes!", "    no  "},
        };
        boolean[] results = {true,true,true,true,false,false};
        for(int j = 0; j < results.length; ++j) {
            String[] data = expecteds[j];
            boolean expectedResult = results[j];
            systemInMock.provideLines(data);
            try {
                boolean actual = App.userChoiceIfElse();
                // System.out.println(actual);
                if (!actual == expectedResult) {
                    assertEquals(
                        "Expected the userChoiceIfElse function to return " + expectedResult + " ",
                        "Returned " + actual + " instead.");
                }
            } catch (Exception e) {
                assertEquals("Expected the userChoiceIfElse() not to crash when called",
                    "The function threw an Exception when called.");
            }
        }
    }

    @Test
    public void testNonZeroValues() {
        int[][] testDatas = {
            {0,1,2,3,4},
            {},
            {2,3,4,0,0,0,0}
        };

        int[] expecteds = {4,0,3};
        for (int i = 0; i < expecteds.length; i++) {
            int[] testData = testDatas[i];
            int expected = expecteds[i];
            try {
                int actual = App.nonZeroValues(testData);
                if (!(expected== actual)) {
                    assertEquals("Expected the nonZeroValues() function to return "
                            + expected+ " nonzeros , given the input: " + Arrays.toString(testData) ,
                        "Returned an array of " + actual+ " instead");
                    return;
                }
            } catch (Exception e) {
                assertEquals("Expected the nonZeroValues() function not to crash when called",
                    "The function crashed when given the input: " + Arrays.toString(testData));
            }
        }
    }

    @Test
    public void testSortWithReturn() {
        int[][] testDatas = {

            {5,4,3,2,1},
            {1,0},
            {},
            {0,1}
        };

        for(int[] testData: testDatas) {
            int[] copyOfData = testData.clone();
            try {
                int[] actual = App.sortWithReturn(testData);
                if (actual.length != copyOfData.length) {
                    assertEquals("returned length " + actual.length + " should be the", "original length " + copyOfData.length);
                }
                if (testData.length != copyOfData.length) {
                    assertEquals("passed data length " + testData.length + " should be the", "original length " + copyOfData.length);
                }
                for(int j = 0; j < actual.length; ++j) {
                    assertEquals(testData[j], copyOfData[j]);
                }
                for(int j = 0; j < testData.length - 1; ++j) {
                    assertTrue("test data equality", actual[j] < actual[j+1]);
                }
            } catch (Exception e) {
                assertEquals("Expected the testSortWithReturn() function not to crash.",
                    "The function crashed when passed the " + Arrays.toString(copyOfData));
            }
        }
    }

    @Test
    public void testSortInline() {
        int[][] testDatas = {

            {5,4,3,2,1},
            {1,0},
            {},
            {0,1}
        };

        for(int[] testData: testDatas) {
            int[] actual = testData.clone();
            try {
                App.sortInline(actual);
                if (testData.length != actual.length) {
                    assertEquals("passed data length " + testData.length + " should be the", "original length " + actual.length);
                }
                for(int j = 0; j < testData.length - 1; ++j) {
                    assertTrue("test data equality", actual[j] < actual[j+1]);
                }
            } catch (Exception e) {
                assertEquals("Expected the testSortWithReturn() function not to crash.",
                    "The function crashed when passed the " + Arrays.toString(testData));
            }
        }
    }

    @Test
    public void testNullTerminated() {
        String[] mockInputs = {
            "\0",
            "hi\0bye",
            "welc\0me",
            "\0hi"
        };
        String[] expecteds = {"", "hi", "welc", ""};
        for (int i = 0; i < mockInputs.length; i++) {
            String mockInput = mockInputs[i];
            String expected = expecteds[i];
            try {
                String actual = App.nullTerminatedString(mockInput);
                if (!expected.equals(actual)) {
                    assertEquals("Expected the nullTerminatedString() function to return " + expected
                            + ", given the argument " + mockInput,
                        "Instead it returned " + actual);
                }
            } catch (Exception e) {
                assertEquals(
                    "Expected the nullTerminatedString() function not to crash when called.",
                    "The function threw an Exception when given the arguments " + mockInput);
            }
        }
    }

    @Test
    public void testCheckerboard() {
        int[][] mockInputs = {{3,5}, {2,2}};
        String[] expecteds = {
            "xoxox\noxoxo\nxoxox",
            "xo\nox",
        };

        for (int i = 0; i < mockInputs.length; i++) {

            int[] mockPair = mockInputs[i];
            int row = mockPair[0];
            int col = mockPair[1];
            String expected = expecteds[i];
            try {
                systemOutRule.clearLog();
                systemOutRule.enableLog();
                App.checkerboard(row,col);
                String actual = systemOutRule.getLog().trim();

                if (expected.equals(actual)) {
                    assertEquals("Expected the checkerboard() function to return \n" + expected
                            + ", given the arguments " + row + " and " 
                            + col,
                        "Instead it returned \n" + actual);
                }
            } catch (Exception e) {
                assertEquals("Expected the calculateTicDensity() function not to crash.",
                    "It crashed when given the arguments " + row + " and "
                        + col);
            }
        }
    }



}
