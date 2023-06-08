package src;

public class NYUStudent extends NYUPerson implements Cloneable {
	
	public String SID;
	public NYUFaculty Advisor;
	public NYUPlan Program;
	public NYUStudent(String inname  , String inid   ) {
		name = inname;
		SID=inid;
	}
	
	public NYUStudent clone() throws CloneNotSupportedException {
		NYUStudent clonestudent;
		clonestudent = (NYUStudent) super.clone();
		clonestudent.Program = this.Program.clone();
		clonestudent.Advisor= new NYUFaculty("??","??");
		return clonestudent;
		
	}
	
	
	
	public String toString() {
		return " Student Name: " + name + " ID: " + SID + " Faculty advisor" + 
	             Advisor.name + " Plan info " + Program.major + "-" + Program.gradyear;
	}
	public void sendemail(String message, int priority) {
		// send an email with above message with given priority
		System.out.println("NYUStudent class " + message + " " + Integer.toString(priority) );
	}
}
