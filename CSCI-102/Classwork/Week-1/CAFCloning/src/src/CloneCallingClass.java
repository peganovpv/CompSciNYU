package src;
import java.util.*;

public class CloneCallingClass {

	public static void main(String[] args) throws CloneNotSupportedException {
		/*
		 * Write  a Driver program that creates a students and associated classes. Then clone the student. 
		 * The cloned students should be objects with a references to the the same major and advisor object 
		 * but having a different set of courses taken object.
		 */

		// Create a student
		NYUStudent student = new NYUStudent("John", "123123");
		// Create a major
		NYUMajor major = new NYUMajor("Computer Science", new NYUFaculty("Dr. John", "123123"), 128);
		// Create a plan
		NYUPlan plan = new NYUPlan();
		plan.gradyear = 2020;
		// Create an array of courses
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("CSCI-UA 101");
		courses.add("CSCI-UA 102");
		courses.add("CSCI-UA 201");
		// Create a transcript
		Transcript transcript = new Transcript(128, courses);
		// Set the plan
		student.Program = plan;
		student.Program.major = major.getMajorName();
		// Clone the student
		NYUStudent clonedStudent = student.clone();
		// Print the student
		System.out.println(student.toString());
		// Print the cloned student
		System.out.println(clonedStudent.toString());

	}
}
