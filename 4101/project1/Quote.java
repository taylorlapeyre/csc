import java.io.*;

class Quote extends Special {

  public Quote() { }

  void print(Node c, int n, boolean p) {
  	 System.out.print("'");
  	 c.getCar().print(n, false);
  }
}
