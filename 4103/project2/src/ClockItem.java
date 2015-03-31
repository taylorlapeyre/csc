public class ClockItem {
    String value;
    ClockItem next;

    public boolean hasNext() {
        return next != null;
    }
}
