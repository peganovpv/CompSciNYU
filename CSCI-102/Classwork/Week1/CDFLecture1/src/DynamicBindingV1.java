
import javax.swing.JOptionPane;

public class DynamicBindingV1 {

	public static void main(String[] args) {
		String ptype,name,id;
		NYUPerson precipient=null;
		
		ptype = JOptionPane.showInputDialog("Enter (S)tudent, (T)ransfer, (F)aculty");
		name = JOptionPane.showInputDialog("Enter name");
		id = JOptionPane.showInputDialog("Enter id");
		if (ptype.equals("S")) { precipient = new NYUStudent(name,id);}
		if (ptype.equals("F")) { precipient = new NYUFaculty(name,id);}
		if (ptype.equals("T")) { precipient = new NYUTransfer(name,id);}
		// more code
		precipient.name += "(NYU)"; 
		 
		// more code 
		precipient.sendemail("Welcome " + precipient.name); 
		// more code
		if (precipient instanceof NYUFaculty) { precipient.name += " - Faculty - ";}
		 
	}

}
