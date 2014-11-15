import java.io.*;

class Regular extends Special {
    private Cons cons;

    public Regular() {}

    void print(Node c, int n, boolean p) {
        Printer.printRegular(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        System.out.println("[IN REGULAR EVAL]");
        Node first = t.getCar();
        Node rest  = t.getCdr();
        System.out.println(rest.getCar().getValue());

        if (first.isSymbol()) {
            first = env.lookup(first);
        }

        if (first.isNull() || first == null) {
            return null;
        }

        if (first.isProcedure()) {
            return first.apply(rest, env);
        } else {
            return first.eval(env).apply(rest, env);
        }
    }
}
