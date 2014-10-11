import java.io.*;

class Quote extends Special {
  private Cons list = null;

  public Quote() {}

  void print(Node c, int n, boolean p) {
  	 System.out.print("'");
  	 c.getCdr().print(0, true);
  }

  void printQuote(Node c, int n, boolean p) {

  }
}
