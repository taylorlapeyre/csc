import java.util.ArrayList;

public class PageReplacementSimulator {
    ArrayList<String> frames;
    ArrayList<String> evictedFrames;
    int numberOfPageFrames;
    int totalHits;
    int totalMisses;
    int totalEvictions;

    public PageReplacementSimulator(int theNumberOfPageFrames) {
        numberOfPageFrames = theNumberOfPageFrames;
        frames = new ArrayList<String>();
        evictedFrames = new ArrayList<String>();
        totalHits = 0;
        totalMisses = 0;
        totalEvictions = 0;
    }

    public void onAlgorithmComplete() {
        System.out.println("Algorithm completed successfully.");
        System.out.println("Total hits: " + totalHits);
        System.out.println("Total misses: " + totalMisses);
        System.out.println("Total evictions: " + totalEvictions);
        System.out.println("Evicted frames: ");
        System.out.println(evictedFrames);
    }

    public void onHit(String val) {
        totalHits++;
    }

    public void onMiss(String val) {
        totalMisses++;
    }

    public void onAllocation(String val) {
    }

    public void onComplete(String val) {
        printPageFrames();
    }

    public void onEviction(String val, String evictedVal) {
        totalEvictions++;
        evictedFrames.add(evictedVal);
    }

    public int getFrameToBeEvicted() {
        // Default: always return the first frame.
        return 0;
    }

    public void simulate(String input) {
        String[] values = input.split(", ");
        for (String val : values) {
            if (frames.contains(val)) {
                // Hit: val was found in our page frames.
                onHit(val);
            } else {
                // Miss: Value was not found in our page frames.
                onMiss(val);

                if (frames.size() == numberOfPageFrames) {
                    // Eviction: A frame must be replaced.
                    int frameIndex = getFrameToBeEvicted();
                    onEviction(val, frames.get(frameIndex));
                    frames.set(frameIndex, val);
                } else {
                    // Allocation: There are empty page frames. Set val to one of them.
                    frames.add(val);
                    onAllocation(val);
                }

            }
            onComplete(val);
        }
        onAlgorithmComplete();
    }

    public void printPageFrames() {
        System.out.println("-----");
        for (String frame : frames) {
            System.out.println("| " + frame + " |");
        }
        System.out.println("-----");
    }
}
