// DO NOT TOUCH THIS FILE!
package edu.nyu.cs;

// import junit 4 testing framework
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.SystemOutRule; // system rules lib - useful for capturing system output
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
// import static org.mockito.Mockito.*;

import org.junit.ClassRule;

public class RunningSpeedCalculator_Test {

    // @Rule
    // public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @ClassRule
    public static final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    @Test
    public void testMain() {
        systemOutRule.enableLog(); // start capturing System.out
        String[][] mockInputs = {
            {"10", "200"},
            {"20.5", "375.2"},
            {"15.75", "37.6"}
        };
        String[] expecteds = {
            "1.8",
            "2.0",
            "15.6"
        };
        int i = 0;
        for (String[] mockInput : mockInputs) {
            systemInMock.provideLines(mockInput[0], mockInput[1]);
            String[] args = {};
            try {
                RunningSpeedCalculator.main(args);
                String output = systemOutRule.getLogWithNormalizedLineSeparator();
                boolean expected = true;
                boolean actual = output.contains(expecteds[i]);
                assertEquals("Given the input, '" + mockInput[0] + "' and '" + mockInput[1] + "', expected the output to contain '" + expecteds[i] + "'.", expected, actual);
                i++;
            }
            catch (Exception e) {
                assertEquals("Expected the program never to crash... unfortunately, it did!", true, e); // program crashed
            }
        }


    }

}
