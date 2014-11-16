import java.io.*;

class Define extends Special {

    public Define() {}

    void print(Node c, int n, boolean p) {
        Printer.printDefine(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        System.out.println("[IN DEFINE EVAL]");
        Node name = t.getCdr().getCar();
        Node val  = t.getCdr().getCdr().getCar();

        if (name.isSymbol()) {
            System.out.println("looks like we're trying to define a variable. Doing that...");
            env.define(name, val);
            return name;
        } else {
            System.out.println("looks like we're trying to define a procedure. Doing that...");
            Closure procedure = new Closure(
                new Cons(
                    t.getCdr().getCar().getCdr(),
                    t.getCdr().getCdr())
                , env
            );
            env.define(name.getCar(), procedure);
            return name.getCar();
        }
    }
}
