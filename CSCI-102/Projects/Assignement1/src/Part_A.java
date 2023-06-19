import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This is the Process class. It contains a name and a list of resources.
 */
class Process {
    String name;
    LinkedList<String> resources;

    /**
     * This is the constructor for the Process class.
     *
     * @param name      the name of the process
     * @param resources the list of resources required by the process
     */
    Process(String name, LinkedList<String> resources) {
        this.name = name;
        this.resources = resources;
    }
}

/**
 * This is the Part_A class.
 */
public class Part_A {

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        String filePath = "test/TestCases.txt";
        String inputString = "";
        try {
            File inputFile = new File(filePath);
            Scanner scanner = new Scanner(inputFile);
            inputString = scanner.nextLine();

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
     * This method simulates an operating system.
     * It takes in a string of processes and their resources.
     *
     * @param inputString the input string representing processes and their resources
     * @return the number of cycles required to complete all the processes
     */
    static int simulateOS(String inputString) {
        String[] processStrings = inputString.split(";");
        LinkedList<Process> processes = new LinkedList<>();

        int cycles = 0;

        /**
         * This loop creates a list of processes.
         */
        for (String processString : processStrings) {
            String[] processInfo = processString.split("\\(");
            String name = processInfo[0];
            String[] resources = processInfo[1].split(",");
            LinkedList<String> resourceList = new LinkedList<>();

            // Add resources to the resource list
            for (String resource : resources) {
                resourceList.addLast(resource);
            }

            // Create a new process and add it to the list of processes
            processes.addLast(new Process(name, resourceList));
        }

        LinkedList<String> availableResources = new LinkedList<>();
        LinkedList<Process> completedProcesses = new LinkedList<>();

        /**
         * This loop runs until all processes are completed.
         */
        while (completedProcesses.size() < processes.size()) {
            for (int i = 0; i < processes.size(); i++) {
                Process process = processes.get(i);

                // Skip processes that have already been completed
                if (completedProcesses.containsElement(process)) {
                    continue;
                }

                boolean canRun = true;

                // Check if the process can run by verifying if all required resources are available
                for (int j = 0; j < process.resources.size(); j++) {
                    String resource = process.resources.get(j);
                    if (!availableResources.containsElement(resource)) {
                        canRun = false;
                        break;
                    }
                }

                if (canRun) {
                    // Remove the resources required by the process from the available resources
                    for (int j = 0; j < process.resources.size(); j++) {
                        String resource = process.resources.get(j);
                        availableResources.remove(resource);
                    }

                    // Add the completed process to the list of completed processes
                    completedProcesses.addLast(process);
                }
            }

            for (int i = 0; i < processes.size(); i++) {
                Process process = processes.get(i);

                // Skip processes that have already been completed
                if (completedProcesses.containsElement(process)) {
                    continue;
                }

                boolean hasAvailableResource = false;

                // Check if there is at least one available resource for the process to run
                for (int j = 0; j < process.resources.size(); j++) {
                    String resource = process.resources.get(j);
                    if (availableResources.containsElement(resource)) {
                        hasAvailableResource = true;
                        break;
                    }
                }

                if (hasAvailableResource) {
                    continue;
                }

                // Add the resources required by the process to the available resources
                for (int j = 0; j < process.resources.size(); j++) {
                    String resource = process.resources.get(j);
                    availableResources.addLast(resource);
                }
            }

            /**
             * Increment the number of cycles.
             */
            cycles++;
        }
        
        return cycles;
    }

}
