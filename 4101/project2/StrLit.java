import java.io.*;
class StrLit extends Node {
    private String strVal;

    public StrLit(String s) { strVal = s; }

    public void print(int n) {
        Printer.printStrLit(n, strVal);
    }

    public String getStrVal() {
        return strVal;
    }

    public Node eval(Environment env) {
        return this;
    }
}
