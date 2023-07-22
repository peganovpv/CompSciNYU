
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MainClass {
	
	static int RomneyVotes = 0;
	static int ObamaVotes = 0;
	static HashMap <String, Integer> electoralVotes = new HashMap <String, Integer> ();

	public static void main(String[] args) {

		// File Formats: 
		//  data/ElectoralVotes.csv :  State, Number of electoral votes
		//  data/ElectionData 2012.csv:  State, # of Obama votes, # of Romney votes
		File selectedfile = new File ("data/ElectoralVotes.csv");		
		// Confirm if the file exists -- if not, prompt error
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(selectedfile);
		}
		catch (FileNotFoundException e1) {
			System.out.println("\n[ERROR]: Cannot find the electoral votes file.");
		}		
		// Scan each entry until no more exist  
		while (fileInput.hasNextLine()) {
			// Read the next line in the file
			String buffline = fileInput.nextLine();
			// Split the line into an array
			String[] data_line = buffline.split(",");
			// Store the information  in your HashMap
			data_line[0] = data_line[0].trim();
			data_line[1] = data_line[1].trim();
			electoralVotes.put(data_line[0], Integer.parseInt(data_line[1]));
		}

		// Read the election data file
		selectedfile = new File ("data/ElectionData2012.csv");
		
		try {

			fileInput = new Scanner(selectedfile);

		}
		catch (FileNotFoundException e1) {

			System.out.println("\n[ERROR]: Cannot find the election data file.");

		}

		while (fileInput.hasNextLine()) {

			String buffline = fileInput.nextLine();
			String[] data_line = buffline.split(",");

			data_line[0] = data_line[0].trim();
			data_line[1] = data_line[1].trim();
			data_line[2] = data_line[2].trim();

			if (Integer.parseInt(data_line[1]) > Integer.parseInt(data_line[2])) {

				ObamaVotes += electoralVotes.get(data_line[0]);

			}
			else {

				RomneyVotes += electoralVotes.get(data_line[0]);

			}
		}
		
		System.out.println("Romney Votes: " + RomneyVotes);
		System.out.println("Obama Votes: " + ObamaVotes);
		

	}

}
