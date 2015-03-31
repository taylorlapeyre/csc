public class CLOCKSimulator extends PageReplacementSimulator {
    ClockItem clock;
    ClockItem hand;

    public CLOCKSimulator(int theNumberOfPageFrames) {
        super(theNumberOfPageFrames);

        // clock is a circular linked list of empty (for now) nodes.
        // hand is the first element in the clock, and will be rotated.
        hand = clock = new ClockItem();

        // The amount of nodes in the clock is equal to the number of page frames.
        for (int i = 1; i < theNumberOfPageFrames - 1; i++) {
            clock.next = new ClockItem();
            clock = clock.next;
        }

        // Close the loop.
        clock.next = hand;
    }

    @Override
    public void onMiss(String val) {
        super.onMiss(val);
        hand.value = val;
        hand = hand.next;
    }

    @Override
    public int getFrameToBeEvicted() {
        return frames.indexOf(hand.value);
    }
}