import java.io.*;
class StrLit extends Node {
    private String strVal;

    public StrLit(String s) { strVal = s; }

    public void print(int n) {
        Printer.printStrLit(n, strVal);
    }

    public Node eval(Environment env) {
        System.out.println("[IN STR LIT EVAL]");
        return this;
    }
}
