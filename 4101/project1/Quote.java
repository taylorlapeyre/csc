import java.io.*;

class Quote extends Special {
  private Cons list = null;

  public Quote(Cons list) {
     this.list = list;
  }

  void print(Node c, int n, boolean p) {
  	 System.out.print("'");
  	 ((Cons)list.getCar()).printQuote(n, false);
  }

  void printQuote(Node c, int n, boolean p) {
     print(c, n, p); 
  }
}
