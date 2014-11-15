import java.io.*;

class If extends Special {

    public If() { }

    void print(Node c, int n, boolean p) {
        Printer.printIf(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        Node predicate  = t.getCar().getCdr();
        Node ifClause   = t.getCar().getCdr().getCdr();
        Node elseClause = t.getCdr().getCdr().getCar().getCdr();

        if (predicate.eval(env).getBoolean()) {
            return ifClause.eval(env);
        } else {
            return elseClause.eval(env);
        }
    }
}
