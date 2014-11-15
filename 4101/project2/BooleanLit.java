import java.io.*;
class BooleanLit extends Node {
    private boolean booleanVal;

    public BooleanLit(boolean b) {
        booleanVal = b;
    }

    public void print(int n) {
        if (booleanVal) {
            Printer.printBoolLit(n, 1);
        } else {
            Printer.printBoolLit(n, 0);
        }
    }

    public Node eval(Node t, Environment env) {
        System.out.println("IN BOOL LIT EVAL");
        return this;
    }
}
