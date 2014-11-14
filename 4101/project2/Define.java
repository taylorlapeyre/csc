import java.io.*;

class Define extends Special {

    public Define() {}

    void print(Node c, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }

        System.out.print("(define ");

        Node name = c.getCdr().getCar();
        if (name.isSymbol()) {
            name.print(0, false);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        System.out.print(" ");

        Node value = c.getCdr().getCdr().getCar();
        if (!value.isNull()) {
            value.print(0, true);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }
        System.out.print(")");
    }

    public Node eval(Node t, Environment env) {
        Node name = t.getCdr().getCar();
        Node val  = t.getCdr().getCdr().getCar();

        if (key.isSymbol()) {
            env.define(name, val);
        } else {
            Closure procedure = new Closure(t.getCdr().getCdr(), env);
            env.define(name.getCar(), procedure);
        }

        return name;
    }
}
