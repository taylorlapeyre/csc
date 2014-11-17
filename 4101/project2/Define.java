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
            Node args = t.getCdr().getCar().getCdr();
            Node body = t.getCdr().getCdr();
            Closure procedure = new Closure(args, body, env);
            env.define(name.getCar(), procedure);
            return name.getCar();
        }
    }
}
