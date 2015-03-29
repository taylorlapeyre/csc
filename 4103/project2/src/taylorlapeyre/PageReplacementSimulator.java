package taylorlapeyre;

import java.util.ArrayList;

public class PageReplacementSimulator {
    ArrayList<String> frames;
    ArrayList<String> evictedFrames;
    int numberOfPageFrames;
    int totalHits;
    int totalMisses;
    int totalEvictions;

    public PageReplacementSimulator(int numberOfPageFrames) {
        this.numberOfPageFrames = numberOfPageFrames;
        this.frames = new ArrayList<String>();
        this.evictedFrames = new ArrayList<String>();
        this.totalHits = 0;
        this.totalMisses = 0;
        this.totalEvictions = 0;
    }

    public void onAlgorithmComplete() {
        System.out.println("Algorithm completed successfully.");
        System.out.println("Total hits: " + this.totalHits);
        System.out.println("Total misses: " + this.totalMisses);
        System.out.println("Total evictions: " + this.totalEvictions);
        System.out.println("Evicted frames: ");
        System.out.println(this.evictedFrames);
    }

    public void onHit(String val) {
        this.totalHits++;
    }

    public void onMiss(String val) {
        this.totalMisses++;
    }

    public void onAllocation(String val) {
    }

    public void onComplete(String val) {
        this.printPageFrames();
    }

    public void onEviction(String val, String evictedVal) {
        this.totalEvictions++;
        this.evictedFrames.add(evictedVal);
    }

    public int getFrameToBeEvicted() {
        // Default: always return the first frame.
        return 0;
    }

    public void simulate(String input) {
        String[] values = input.split(", ");
        for (String val : values) {
            if (this.frames.contains(val)) {
                // Hit: val was found in our page frames.
                onHit(val);
            } else {
                // Miss: Value was not found in our page frames.
                onMiss(val);

                if (this.frames.size() == this.numberOfPageFrames) {
                    // Eviction: A frame must be replaced.
                    int frameIndex = this.getFrameToBeEvicted();
                    this.onEviction(val, this.frames.get(frameIndex));
                    this.frames.set(frameIndex, val);
                } else {
                    // Allocation: There are empty page frames. Set val to one of them.
                    this.frames.add(val);
                    this.onAllocation(val);
                }

            }

            onComplete(val);
        }

        onAlgorithmComplete();
    }

    public void printPageFrames() {
        System.out.println("-----");
        for (String frame : this.frames) {
            System.out.println("| " + frame + " |");
        }
        System.out.println("-----");
    }


}
