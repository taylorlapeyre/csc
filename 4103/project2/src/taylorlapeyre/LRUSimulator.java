package taylorlapeyre;

import java.util.ArrayList;

public class LRUSimulator extends PageReplacementSimulator {
    ArrayList<String> mostRecentlyUsedValues;

    public LRUSimulator(int numberOfPageFrames) {
        super(numberOfPageFrames);
        this.mostRecentlyUsedValues = new ArrayList<String>();
    }

    @Override
    public void onComplete(String val) {
        this.mostRecentlyUsedValues.add(val);
        this.printPageFrames();
    }

    @Override
    public int getFrameToBeEvicted() {
        String lru = this.mostRecentlyUsedValues.get(0);
        this.mostRecentlyUsedValues.remove(0);
        int indexOfLRU = this.frames.indexOf(lru);

        if (indexOfLRU == -1) {
            return getFrameToBeEvicted();
        } else {
            return indexOfLRU;
        }
    }
}
