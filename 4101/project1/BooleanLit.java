import java.io.*;
class BooleanLit extends Node {
  private boolean booleanVal;

  public BooleanLit(boolean b) {
    booleanVal = b;
  }

  public void print(int n) {
    // There got to be a more efficient way to print n spaces.
    for (int i = 0; i < n; i++)
      System.out.print(" ");
    
    if (booleanVal) {
      System.out.println("#t");
    } else {
      System.out.println("#f");
    }
  }
}
