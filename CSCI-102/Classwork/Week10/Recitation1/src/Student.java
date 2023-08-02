
public class Student implements Comparable<Student> {
   private String sname="";  // Name
   private String IDnum;  //ID
   private String major;  // major
   private int totalcredits =0;
   private int gradepoints =0;
   public static int total_advisor_assigned=0;
   
   public String toString() {
	   return sname + "-" + IDnum;
   }
   
   
   public Student() {
	   major = "undeclared";
   }
   public Student (String inname, String inID, String inmajor) {
	   sname=inname; IDnum = inID;
	   
	   major=inmajor;
	   
	   setMajor(inmajor);
	   this.setMajor(inmajor);
	   
   }
   
   
   public void postGrade(String incredits, String ingrade) {
	   totalcredits+= Integer.parseInt(incredits);
	   int qpoints = 0;
	   switch(ingrade) {
	   case "A": qpoints= 4; break;
	   case "B": qpoints= 3; break;
	   case "C": qpoints= 2; break;
	   default: qpoints = 0;
	   }
	   gradepoints += qpoints *  Integer.parseInt(incredits);
   }
   public double getGPA() {
	   if (totalcredits ==0) {return 0;}
	   return ((double) gradepoints/totalcredits);
   }
   
   
   public void setName(String inname) {
	   sname = inname;
   }
   public String getName() {
	   return sname;
   }
   public void setID(String inid) {
	   IDnum= inid;
   }
   public String getID() {
	   return IDnum;
   }
   public void setMajor(String inmajor) {
	   major = inmajor;
   }
   public String getMajor() {
	   return major;
   }


 
public int compareTo(Student instudent) {
	// if I am less than instudent return a negative number (-1)
	//  if I am equal to the instudent return 0
//  if I am greater than the instudent return positive (1)
	  if (this.IDnum.compareTo(instudent.IDnum)  < 0 ) {
		  return -1;
	  } else if (this.IDnum.compareTo(instudent.IDnum)  == 0 ) {
		  return 0;
	  } else {
		   return 1;
	  }
		 
}

}
