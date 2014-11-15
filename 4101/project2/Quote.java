import java.io.*;

class Quote extends Special {

    public Quote() { }

    void print(Node c, int n, boolean p) {
        Printer.printQuote(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        return null;
    }
}
