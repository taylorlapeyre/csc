import java.io.*;

class Begin extends Special {

    public Begin() {}

    void print(Node c, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }

        System.out.println("(begin");

        if (c.getCdr().isPair()) {
            c.getCdr().print(n + 2, p);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }

        System.out.print(")");
    }

    public Node eval(Node t, Environment env) {
        Node exp = t.getCdr();
        Node val = exp.getCar().eval(env);

        while (!exp.isNull()) {
            val = exp.getCar().eval(env);
            exp = exp.getCdr();
        }

        return val;
    }
}
