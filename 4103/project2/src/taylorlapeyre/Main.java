package taylorlapeyre;

public class Main {

    public static void main(String[] args) {
	    String input = "0, 1, 2, 3, 2, 4, 5, 3, 4, 1, 6, 3, 7, 8, 7, 8, 4, 9, 7, 8, 1, 2, 9, 5, 4, 5, 0, 2";

        String typeOfAlgorithm    = args[0];
        int numberOfPageFrames = Integer.parseInt(args[1]);

        if (typeOfAlgorithm.equals("LRU")) {
            PageReplacementSimulator simulator = new LRUSimulator(numberOfPageFrames);
            simulator.simulate(input);
        } else {
            // TODO: Implement CLOCK.
            PageReplacementSimulator simulator = new PageReplacementSimulator(numberOfPageFrames);
            simulator.simulate(input);
        }


    }
}
