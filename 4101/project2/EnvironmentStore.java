import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EnvironmentStore {
    private Map<String, Node> hashmap;

    public EnvironmentStore() {
        hashmap = new HashMap<String, Node>();
    }

    public Node find(Node id) {
        if (!(id instanceof Ident)) {
            System.out.println("id must be an instance of Ident");
        }
        return hashmap.get(id.getName());
    }

    public void set(Node id, Node value) {
        if (id instanceof Ident) {
            hashmap.put(id.getName(), value);
        } else {
            System.out.println("First arg in set() must be an instance of Ident");
        }
    }

    public void printScope(int n) {
        Iterator<String> keyIterator = hashmap.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key   = keyIterator.next();
            System.out.println("key: " + key);
            System.out.println("value: " + hashmap.get(key));
        }
    }
}
