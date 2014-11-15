import java.io.*;
class Ident extends Node {
    private String name;

    public Ident(String n) { name = n; }

    public void print(int n) {
        Printer.printIdent(n, name);
    }

    public String getName() {
        return name;
    }

    public boolean isSymbol() {
        return true;
    }

    public Node eval(Environment env) {
        System.out.println("IN IDENT EVAL");
        return env.lookup(this);
    }
}
