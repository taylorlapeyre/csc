public class Main {

    public static void main(String[] args) {
        String input = "0, 1, 2, 3, 2, 4, 5, 3, 4, 1, 6, 3, 7, 8, 7, 8, 4, 9, 7, 8, 1, 2, 9, 5, 4, 5, 0, 2";

        if (args.length == 0) {
            System.err.println("Please run the program with the following arguments: ");
            System.err.println("  1: Type of Algorithm (LRU or CLOCK)");
            System.err.println("  2: Number of page frames (integer only)");
            System.exit(1);
        }

        String typeOfAlgorithm    = args[0];
        int numberOfPageFrames = Integer.parseInt(args[1]);
        PageReplacementSimulator simulator;

        if (typeOfAlgorithm.equals("LRU")) {
            simulator = new LRUSimulator(numberOfPageFrames);
        } else if (typeOfAlgorithm.equals("CLOCK")) {
            simulator = new CLOCKSimulator(numberOfPageFrames);
        } else {
            simulator = new PageReplacementSimulator(numberOfPageFrames);
        }

        simulator.simulate(input);
    }

}
