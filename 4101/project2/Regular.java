import java.io.*;

class Regular extends Special {
    private Cons cons;

    public Regular() {}

    void print(Node c, int n, boolean p) {
        Printer.printRegular(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        Node first = t.getCar();
        Node rest  = t.getCdr();

        if (first.isSymbol()) {
            first = env.lookup(first);
        }

        if (first.isNull() || first == null) {
            return null;
        }

        if (first.isProcedure()) {
            if (rest.isNull()) {
                return first.apply(new Cons(Nil.getInstance(), Nil.getInstance()), env);
            } else {
                return first.apply(rest, env);
            }
        } else {
            if (rest.isNull()) {
                return first.eval(env).apply(new Cons(Nil.getInstance(), Nil.getInstance()), env);
            } else {
                return first.eval(env).apply(rest, env);
            }
        }
    }
}
