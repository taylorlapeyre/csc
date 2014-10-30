import java.io.*;
class Nil extends Node {
  public Nil() { }

  public void print(int n)		{ print(n, false); }
  public void print(int n, boolean p) {
    if (p) {  
      System.out.print(")");
    } else {
      if(n > 0) {
        for(int i = 0; i < n; i++) {
          System.out.print("");
        }
      }
      System.out.print("()");
    }
  }

  public boolean isNull() {
      return true;
  }
}
