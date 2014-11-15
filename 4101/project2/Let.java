import java.io.*;

class Let extends Special {
    public Let() { }

    void print(Node c, int n, boolean p) {
        Printer.printLet(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        return null;
    }

}
