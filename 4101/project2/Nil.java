import java.io.*;
class Nil extends Node {
    private static Nil instance = null;

    public Nil() { }

    public void print(int n){
        print(n, false);
    }

    public void print(int n, boolean p) {
        Printer.printNil(n, p);
    }

    public boolean isNull() {
        return true;
    }

    public static Nil getInstance() {
        if (instance == null) {
            instance = new Nil();
        }
        return instance;
    }
}
