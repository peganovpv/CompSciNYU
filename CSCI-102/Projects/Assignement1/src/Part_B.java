import java.util.Random;

/**
 * This is the Part_B class.
 */
public class Part_B {
    /**
     * This is the Process class. It contains a name and a list of resources.
     */
    static class Process {
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
     * Here we define the needed constants.
     */
    static final String[] POSSIBLE_RESOURCES = new String[]{"A", "B", "C"};
    static final int MAX_CYCLES = 1000;
    static final Random RANDOM = new Random();

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        LinkedList<Process> processes = generateProcesses(20);
        System.out.println(simulateOS(processes));
    }

    /**
     * This method generates a list of processes with random resources.
     *
     * @param count the number of processes to generate
     * @return a list of processes
     */
    static LinkedList<Process> generateProcesses(int count) {
        LinkedList<Process> processes = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            String name = "P" + i;
            LinkedList<String> resources = new LinkedList<>();
            int resourceCount = RANDOM.nextInt(POSSIBLE_RESOURCES.length);
            for (int j = 0; j < resourceCount; j++) {
                resources.addLast(POSSIBLE_RESOURCES[RANDOM.nextInt(POSSIBLE_RESOURCES.length)]);
            }
            Process process = new Process(name, resources);
            processes.addLast(process);
        }
        return processes;
    }

    /**
     * This method simulates an operating system.
     * It takes in a list of processes and their resources.
     *
     * @param processes the list of processes to simulate
     * @return a message indicating the result of the simulation
     */
    static String simulateOS(LinkedList<Process> processes) {

        /**
         * Here we define the needed variables.
         */
        int cycles = 0;
        LinkedList<String> availableResources = new LinkedList<>();
        LinkedList<Process> completedProcesses = new LinkedList<>();

        /**
         * Here we use a while loop to simulate the OS.
         */
        while (cycles < MAX_CYCLES && completedProcesses.size() < processes.size()) {
            for (int i = 0; i < processes.size(); i++) {
                Process process = processes.get(i);
                if (completedProcesses.containsElement(process)) {
                    continue;
                }

                /**
                 * Here we check if the process can run.
                 */
                boolean canRun = true;
                for (int j = 0; j < process.resources.size(); j++) {
                    String resource = process.resources.get(j);
                    if (!availableResources.containsElement(resource)) {
                        canRun = false;
                        break;
                    }
                }

                /**
                 * Here we run the process if it can run (using an if statement to check).
                 */
                if (canRun) {
                    for (int j = 0; j < process.resources.size(); j++) {
                        String resource = process.resources.get(j);
                        availableResources.remove(resource);
                    }
                    completedProcesses.addLast(process);
                }
            }

            for (int i = 0; i < processes.size(); i++) {
                Process process = processes.get(i);
                if (completedProcesses.containsElement(process)) {
                    continue;
                }

                boolean hasAvailableResource = false;
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

                for (int j = 0; j < process.resources.size(); j++) {
                    String resource = process.resources.get(j);
                    availableResources.addLast(resource);
                }
            }

            /**
             * Here we increment the cycles.
             */
            cycles++;

            /**
             * Print the length of processes every 100th cycle to watch its growth.
             */
            if (cycles % 100 == 0) {
                System.out.println("Length of processes at cycle " + cycles + ": " + processes.size());
            }

            /**
             * Generate new processes and add them to the existing list of processes.
             */
            processes.addAll(generateProcesses(2));
        }

        /**
         * Here we return the result of the simulation.
         */
        if (cycles == MAX_CYCLES) {
            return "After " + MAX_CYCLES + " cycles, " + processes.size() + " processes are left.";
        } else {
            return "All processes completed in " + cycles + " cycles.";
        }
    }
}
