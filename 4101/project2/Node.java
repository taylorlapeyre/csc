import java.io.*;

class Node {

    void print(int n) {}

    void print(int n, boolean p) {
        print(n);
    }

    // TODO: implement these in the appropriate subclasses to return TRUE.
    public boolean isBoolean()   { return false; }  // BooleanLit
    public boolean isNumber()    { return false; }  // IntLit
    public boolean isString()    { return false; }  // StringLit
    public boolean isSymbol()    { return false; }  // Ident
    public boolean isNull()      { return false; }  // nil
    public boolean isPair()      { return false; }  // Cons
    public boolean isProcedure() { return false; }

    public Node getCar() {
        return null;
    }

    public Node getCdr() {
        return null;
    }

    public void setCar(Node a) {
    }

    public void setCdr(Node d) {
    }

    public String getName() {
        return "";
    }

    public Node eval(Environment env) {
        return null;
    }

    public Node apply(Node args, Environment env) {
        System.err.println("Something went wrong!");
        return null;
    }

    public boolean getBoolean() {
        return false;
    }

    public int getValue() {
        return 0;
    }

    public String getStrVal() {
        return "";
    }
}
