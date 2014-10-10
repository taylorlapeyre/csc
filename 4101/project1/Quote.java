import java.io.*;

class Quote extends Special {
  private Cons list = null;

  public Quote() {}

  void print(Node c, int n, boolean p) {
  	 System.out.print("'");
  	 ((Cons)list.getCar()).printQuote(n, false);
  }

  void printQuote(Node c, int n, boolean p) {
     print(c, n, p);
  }
}
