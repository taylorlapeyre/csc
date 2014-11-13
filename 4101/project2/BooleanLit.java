import java.io.*;
class BooleanLit extends Node {
    private boolean booleanVal;

    public BooleanLit(boolean b) {
        booleanVal = b;
    }

    public void print(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(" ");

        if (booleanVal) {
            System.out.print("#t");
        } else {
            System.out.print("#f");
        }
    }

    public Node eval(Node t, Environment env) {
        return this;
    }
}
