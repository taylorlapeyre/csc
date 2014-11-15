import java.io.*;
class IntLit extends Node {
  private int intVal;

  public IntLit(int i) { intVal = i; }

  public int getValue() {
    return intVal;
  }

  public void print(int n) {
    for (int i = 0; i < n; i++)
      System.out.print(" ");

    System.out.print(intVal);
  }

  public Node eval(Environment env) {
    return this;
  }
}
