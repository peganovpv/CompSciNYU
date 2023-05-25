package src;

import java.util.ArrayList;
public class Transcript {
    // Transcript: with properties of  EarnedCredits, CoursesTaken(Array of coursename (string))
    private int earnedCredits;
    private ArrayList<String> coursesTaken;

    public Transcript(int earnedCredits, ArrayList<String> coursesTaken) {
        this.earnedCredits = earnedCredits;
        this.coursesTaken = coursesTaken;
    }

    public int getEarnedCredits() {
        return this.earnedCredits;
    }

    public ArrayList<String> getCoursesTaken() {
        return this.coursesTaken;
    }
    
}
