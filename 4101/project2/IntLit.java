import java.io.*;
class IntLit extends Node {
    private int intVal;

    public IntLit(int i) { intVal = i; }

    public void print(int n) {
         Printer.printIntLit(n, intVal);
    }

    public boolean isNumber() {
        return true;
    }

    public int getValue() {
        return intVal;
    }

    public Node eval(Environment env) {
        System.out.println("[IN INT LIT EVAL]");
        return this;
    }
}
