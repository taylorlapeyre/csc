import java.io.*;

class Let extends Special {
    public Let() { }

    void print(Node c, int n, boolean p) {
        Printer.printLet(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        Node assignments = t.getCdr().getCar();
        Node body        = t.getCdr().getCdr().getCar();

        Environment letEnv = new Environment(env);

        while (!assignments.isNull()) {
            Node assignment = assignments.getCar();
            Node name = assignment.getCar();
            Node val  = assignment.getCdr().getCar();

            letEnv.define(name, val.eval(env));

            assignments = assignments.getCdr();
        }

        return body.eval(letEnv);
    }

}
