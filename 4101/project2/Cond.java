import java.io.*;

class Cond extends Special {

    public Cond() { }

    void print(Node c, int n, boolean p) {
        Printer.printCond(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        Node exp = t.getCdr();

        while (!exp.getCar().getCar().eval(env).getBoolean() && !exp.isNull()) {
            exp = exp.getCdr();
        }

        if (exp.isNull()) {
            return null;
        } else {
            return exp.getCar().getCar().getCdr().eval(env);
        }
    }
}
