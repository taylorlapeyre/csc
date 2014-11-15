import java.io.*;

class Begin extends Special {

    public Begin(Node node) {}

    void print(Node node, int n, boolean bool) {
        Printer.printBegin(node, n, bool);
    }

    public Node eval(Node node, Environment env) {
    	return null;
    }
}
