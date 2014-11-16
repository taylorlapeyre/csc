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

        if (first.isSymbol()) {
            System.out.println("LOOKING UP REGULAR...");
            first = env.lookup(first);
            System.out.println("Found it! it's: " + first);
        }

        if (first.isNull() || first == null) {
            return null;
        }

        if (first.isProcedure()) {
            System.out.println("APPLYING A PROCEDURE "+ first +" TO ARGS");
            if (rest.isNull()) {
                return first.apply(new Cons(Nil.getInstance(), Nil.getInstance()));
            } else {
                return first.apply(rest);
            }
        } else {
            System.out.println("APPLYING A NON-PROCEDURE "+ first +" TO ARGS");
            if (rest.isNull()) {
                return first.eval(env).apply(new Cons(Nil.getInstance(), Nil.getInstance()));
            } else {
                return first.eval(env).apply(rest);
            }

        }
    }
}
