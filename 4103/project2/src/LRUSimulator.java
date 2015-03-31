import java.util.ArrayList;

public class LRUSimulator extends PageReplacementSimulator {
    ArrayList<String> mostRecentlyUsedValues;

    public LRUSimulator(int numberOfPageFrames) {
        super(numberOfPageFrames);
        mostRecentlyUsedValues = new ArrayList<String>();
    }

    @Override
    public void onAccessComplete(String val) {
        if (mostRecentlyUsedValues.contains(val)) {
            mostRecentlyUsedValues.remove(val);
        }

        mostRecentlyUsedValues.add(val);
        printPageFrames();
    }

    @Override
    public int getFrameToBeEvicted() {
        // go up the list of least recently used values until we find one that is in the frames.
        int leastUsed = 0;
        String lru = mostRecentlyUsedValues.get(leastUsed);

        while (!frames.contains(lru)) {
            lru = mostRecentlyUsedValues.get(leastUsed++);
        }

        return frames.indexOf(lru);
    }
}
