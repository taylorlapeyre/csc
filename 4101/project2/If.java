import java.io.*;

class If extends Special {

    public If() { }

    void print(Node c, int n, boolean p) {
        Printer.printIf(c, n, p);
    }

    public Node eval(Node t, Environment env) {

        Node predicate  = t.getCdr().getCar();
        Node ifClause   = t.getCdr().getCdr().getCar();
        Node elseClause = t.getCdr().getCdr().getCdr().getCar();

        if (predicate.eval(env).getBoolean()) {
            return ifClause.eval(env);
        } else {
            return elseClause.eval(env);
        }
    }
}
