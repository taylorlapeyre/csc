import java.io.*;
class Nil extends Node {
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
}
