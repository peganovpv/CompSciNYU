
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Process is a class that represents a process in the OS.
 * It has a name and a list of resources it needs to run.
 */
class Process {
    String name;
    List<String> resources;

    /*
     * Constructor for Process.
     */
    Process(String name, List<String> resources) {
        this.name = name;
        this.resources = resources;
    }
}

public class Part_A {
    /**
     * main method to run the simulateOS method.
     * @param args
     */
    public static void main(String[] args) {

        /*
         * We wrap the code in a try-catch block to catch any exceptions that may occur.
         * We read the input file and store it in a string.
         */
        String filePath = "test/TestCases.txt";
        String inputString = "";
        try {

            File inputFile = new File(filePath);
            Scanner scanner = new Scanner(inputFile);
            inputString = scanner.nextLine();
            
            /**
             * While there are more lines in the file, we run the simulateOS method on the line and print the result.
             */
            while (scanner.hasNextLine()) {
                inputString = scanner.nextLine();
                System.out.println(simulateOS(inputString));
            }

            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

    }

    /** 
     * simulateOS method takes in a string of processes and resources and returns the cycles it takes to run all the processes.
     * @param inputString is a string of processes and resources.
    */
    static int simulateOS(String inputString) {
        /**
         * Processes are separated by a semicolon.
         * We split the inputString by semicolon to get an array of processes.
         */
        String[] processStrings = inputString.split(";");
        List<Process> processes = new ArrayList<>();

        /*
         * cycles is the number of cycles it takes to run all the processes.
         */
        int cycles = 0;

        /**
         * We iterate through the array of processes and split each process by the open parenthesis.
         * The first element of the array is the name of the process.
         * The second element of the array is a comma separated list of resources the process needs.
         * We create a new Process object with the name and resources and add it to the list of processes.
         */
        for (String processString : processStrings) {

            String[] processInfo = processString.split("\\(");
            String name = processInfo[0];
            String[] resources = processInfo[1].split(",");
            processes.add(new Process(name, Arrays.asList(resources)));

        }

        List<String> availableResources = new ArrayList<>();
        List<Process> completedProcesses = new ArrayList<>();

        /**
         * We iterate through the list of processes until we have completed all the processes.
         */
        while (completedProcesses.size() < processes.size()) {
            /**
             * We iterate through the list of processes.
             * If the process is already completed, we skip it.
             * If the process can run, we remove the resources it needs from the availableResources list and add it to the completedProcesses list.
             */
            for (Process process : processes) {
                if (completedProcesses.contains(process)) {
                    continue;
                }

                boolean canRun = true;
                for (String resource : process.resources) {
                    if (!availableResources.contains(resource)) {
                        canRun = false;
                        break;
                    }
                }

                if (canRun) {
                    for (String resource : process.resources) {
                        availableResources.remove(resource);
                    }
                    completedProcesses.add(process);
                }
            }


            for (Process process : processes) {
                if (completedProcesses.contains(process)) {
                    continue;
                }

                for (String resource : process.resources) {
                    if (!availableResources.contains(resource)) {
                        availableResources.add(resource);
                    }
                }
            }

            /**
             * We increment the cycles by 1.
             */
            cycles++;
        }

        /**
         * We return the number of cycles it took to run all the processes.
         */
        return cycles;

    }
}
