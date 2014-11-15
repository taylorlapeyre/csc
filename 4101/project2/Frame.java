import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Frame {
    private Map<String, Node> scope;

    public Frame() {
        scope = new HashMap<String, Node>();
    }

    public Node find(Node id) {
        if (!(id instanceof Ident)) {
            System.out.println("id must be an instance of Ident");
        }
        return scope.get(id.getName());
    }

    public void set(Node id, Node value) {
        if (id instanceof Ident) {
            scope.put(id.getName(), value);
        } else {
            System.out.println("First arg in set() must be an instance of Ident");
        }
    }

    public void printScope(int n) {
        Iterator<String> keyIterator = scope.keySet().iterator();
        while( keyIterator.hasNext() ) {
            String key   = keyIterator.next();
            Node value = scope.get(key);

            // This may or may not work right. Worried about how value will print for different types.
            System.out.println("key: " + key);
            System.out.println("value: " + value);
        }
    }
}
