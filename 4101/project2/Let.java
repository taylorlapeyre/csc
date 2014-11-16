import java.io.*;

class Let extends Special {
    public Let() { }

    void print(Node c, int n, boolean p) {
        Printer.printLet(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        System.out.println("[IN LET EVAL]");
        Node assignments = t.getCdr().getCar();
        Node body        = t.getCdr().getCdr().getCar();

        Environment letEnv = new Environment(env);

        while (!assignments.isNull()) {
            Node assignment = assignments.getCar();
            Node name = assignment.getCar();
            Node val  = assignment.getCdr().getCar();

            System.out.print("LET ASSIGNMENT: ");
            System.out.print("KEY: " + name);
            System.out.println(" VAL: " + val);

            letEnv.define(name, val.eval(env));

            assignments = assignments.getCdr();
        }

        return body.eval(letEnv);
    }

}
