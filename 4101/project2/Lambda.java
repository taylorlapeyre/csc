import java.io.*;

class Lambda extends Special {

    public Lambda() { }

    void print(Node c, int n, boolean p) {
        Printer.printLambda(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        System.out.println("[IN LAMBDA EVAL]");
        return new Closure(t, env);
    }
}
