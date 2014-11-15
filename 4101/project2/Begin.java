import java.io.*;

class Begin extends Special {

    public Begin() {}

    void print(Node c, int n, boolean p) {
        Printer.printBegin(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        System.out.println("IN BEGIN EVAL");
        Node exp = t.getCdr();
        Node val = exp.getCar().eval(env);

        while (!exp.isNull()) {
            val = exp.getCar().eval(env);
            exp = exp.getCdr();
        }

        return val;
    }
}
