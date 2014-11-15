import java.io.*;
class BooleanLit extends Node {
    private boolean booleanVal;

    public BooleanLit(boolean b) {
        booleanVal = b;
    }

    public void print(int n) {
        return (booleanVal) ? Printer.printBoolLit(n, 1) : Printer.printBoolLit(n, 0);
    }

    public Node eval(Node t, Environment env) {
        return this;
    }
}
