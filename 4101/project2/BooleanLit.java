import java.io.*;
class BooleanLit extends Node {
  private boolean booleanVal;
  private static BooleanLit True = null;
  private static BooleanLit False = null;

  public BooleanLit(boolean b) {
    booleanVal = b;
  }

  public boolean isBool() {
    return true;
  }

  public boolean getBool() {
    return booleanVal;
  }

  public void print(int n) {
    // There got to be a more efficient way to print n spaces.
    for (int i = 0; i < n; i++)
      System.out.print(" ");
    
    if (booleanVal) {
      System.out.print("#t");
    } else {
      System.out.print("#f");
    }
  }

    public static BooleanLit getInstance(boolean val) {
        if (val == true) {
            if (True == null) {
                True = new BooleanLit(true);
            }
            return True;
        } else {
            if (False == null) {
                False = new BooleanLit(false);
            }
            return False;
        }
    }

  public Node eval(Environment env) {
    return this;
  }
}
