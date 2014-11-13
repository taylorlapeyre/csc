import java.io.*;
class Ident extends Node {
    private String name;

    public Ident(String n) { name = n; }

    public void print(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(" ");

        System.out.print(name);
    }

    public String getName() {
        return name;
    }

    public boolean isSymbol() {
        return true;
    }

    public Node eval(Environment env) {
        return env.lookup(this);
    }
}
