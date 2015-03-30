import java.util.ArrayList;

public class LRUSimulator extends PageReplacementSimulator {
    ArrayList<String> mostRecentlyUsedValues;

    public LRUSimulator(int numberOfPageFrames) {
        super(numberOfPageFrames);
        mostRecentlyUsedValues = new ArrayList<String>();
    }

    @Override
    public void onComplete(String val) {
        mostRecentlyUsedValues.add(val);
        this.printPageFrames();
    }

    @Override
    public int getFrameToBeEvicted() {
        String lru = mostRecentlyUsedValues.get(0);
        mostRecentlyUsedValues.remove(0);
        int indexOfLRU = frames.indexOf(lru);

        if (indexOfLRU == -1) {
            return getFrameToBeEvicted();
        } else {
            return indexOfLRU;
        }
    }
}
