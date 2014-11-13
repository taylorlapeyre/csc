import java.io.*;

class Cond extends Special {

    public Cond() { }

    void print(Node c, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }

        System.out.println("(cond");

        Node conditions = c.getCdr();
        if (conditions.isPair()) {
            conditions.print(n + 2, true);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }
    }

    public Node eval(Node t, Environment env) {
        return null;
    }
}
