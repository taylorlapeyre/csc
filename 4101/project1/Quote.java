import java.io.*;

class Quote extends Special {

  public Quote() { 
  }

  void print(Node c, int n, boolean p) {
     System.out.print("'");
     if(c.getCdr() instanceof Nil) {
        System.out.print("");	
     } else {
     	c.getCdr().print(0, false); 
     }
  }
}
