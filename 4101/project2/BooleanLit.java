import java.io.*;
class BooleanLit extends Node {
    private boolean booleanVal;
    private static BooleanLit Truth = null;
    private static BooleanLit AntiTruth = null;

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

    public Node eval(Environment env) {
        System.out.println("[IN BOOL LIT EVAL]");
        return this;
    }

    public boolean getBoolean() {
        return booleanVal;
    }

     public static BooleanLit getInstance(boolean val){
        if (val) {
            if (Truth == null) Truth = new BooleanLit(true);
            return Truth;
        } else {
            if (AntiTruth == null) AntiTruth = new BooleanLit(false);
            return AntiTruth;
        }
    }
}
