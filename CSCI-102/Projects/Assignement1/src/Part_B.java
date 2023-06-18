
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

public class Part_B {
    /* Define some constants that will be used throughout the program.
    *  POSSIBLE_RESOURCES are the resources that a process can require to run.
    *  MAX_CYCLES is the maximum number of cycles the simulation will run for.
    *  RANDOM is a random number generator.
    */
    static final String[] POSSIBLE_RESOURCES = new String[]{"A", "B", "C"};
    static final int MAX_CYCLES = 1000;
    static final Random RANDOM = new Random();

    /**
     * main method to run the simulateOS method.
     * @param args
     */
    public static void main(String[] args) {
        List<Process> processes = generateProcesses(20);
        System.out.println(simulateOS(processes));
    }

    /**
     * generateProcesses method generates a list of processes with random names and resources.
     * The number of processes generated is given by the count parameter.
     * @param count
     * @return a list of processes
     */
    static List<Process> generateProcesses(int count) {
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<String> resources = new ArrayList<>();
            int resourceCount = RANDOM.nextInt(3) + 1;
            for (int j = 0; j < resourceCount; j++) {
                resources.add(POSSIBLE_RESOURCES[RANDOM.nextInt(POSSIBLE_RESOURCES.length)]);
            }
            processes.add(new Process("P" + (i + 1), resources));
        }
        return processes;
    }

    /**
     * simulateOS method takes in a list of processes and returns a message saying how many cycles it takes to run all the processes.
     * @param processes
     * @return a message saying how many cycles it takes to run all the processes
     */
    static String simulateOS(List<Process> processes) {
        int cycles = 0;
        List<String> availableResources = new ArrayList<>();
        List<Process> completedProcesses = new ArrayList<>();

        /**
         * We loop through the processes list until all processes are completed or the maximum number of cycles is reached.
         */
        while (cycles < MAX_CYCLES && !processes.isEmpty()) {
            /**
             * We loop through the processes list and check if each process can run.
             */
            for (Process process : new ArrayList<>(processes)) {
                if (canRun(process, availableResources)) {
                    availableResources.removeAll(process.resources);
                    processes.remove(process);
                    completedProcesses.add(process);
                }
            }
            /*
             * Print out the length of the processes list every 100 cycles.
             */
            if (cycles % 100 == 0) {
                System.out.println("Length of processes at cycle " + cycles + ": " + processes.size());
            }
            
            /*
             * Add two new processes and three new resources every cycle.
             */
            processes.addAll(generateProcesses(2));
            availableResources.addAll(Arrays.asList("A", "B", "C"));
            
            /*
             * Increment the cycles variable.
             */
            cycles++;
        }

        /*
        * If the maximum number of cycles is reached, return a message saying how many processes are left.
        * Otherwise, return a message saying how many cycles it took to complete all processes.
        */

        if (cycles == MAX_CYCLES) {
            return "After " + MAX_CYCLES + " cycles, " + processes.size() + " processes are left.";
        } else {
            return "All processes completed in " + cycles + " cycles.";
        }
    }

    /**
     * canRun method takes in a process and a list of available resources and returns true if the process can run.
     * Otherwise, it returns false.
     * @param process
     * @param availableResources
     * @return
     */
    static boolean canRun(Process process, List<String> availableResources) {
        for (String resource : process.resources) {
            if (!availableResources.contains(resource)) {
                return false;
            }
        }
        return true;
    }
}
