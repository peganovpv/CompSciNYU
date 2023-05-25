import javax.swing.JOptionPane;

public class DynamicBinding {

	public static void main(String[] args) {
		String ptype,name,id;
		NYUStudent srecipient=null;
		NYUFaculty frecipient=null;
		NYUTransfer trecipient=null;
		ptype = JOptionPane.showInputDialog("Enter (S)tudent, (T)ransfer, (F)aculty");
		name = JOptionPane.showInputDialog("Enter name");
		id = JOptionPane.showInputDialog("Enter id");
		if (ptype.equals("S")) { srecipient = new NYUStudent(name,id);}
		if (ptype.equals("F")) { frecipient = new NYUFaculty(name,id);}
		if (ptype.equals("T")) { trecipient = new NYUTransfer(name,id);}
		// more code
		if (ptype.equals("S")) { srecipient.name += "(NYU)";}
		if (ptype.equals("F")) { frecipient.name += "(NYU)";}
		if (ptype.equals("T")) { trecipient.name += "(NYU)";}
		// more code 
		if (ptype.equals("S")) { srecipient.sendemail("Welcome " + srecipient.name);}
		if (ptype.equals("F")) { frecipient.sendemail("Welcome " + frecipient.name);}
		if (ptype.equals("T")) { trecipient.sendemail("Welcome " + trecipient.name);}
	}

}
