import java.io.*;

class Define extends Special {

    public Define() {}

    void print(Node c, int n, boolean p) {
        Printer.printDefine(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        Node name = t.getCdr().getCar();
        Node val  = t.getCdr().getCdr().getCar();

        if (name.isSymbol()) {
            env.define(name, val);
            return name;
        } else {
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
