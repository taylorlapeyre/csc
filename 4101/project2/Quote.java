import java.io.*;

class Quote extends Special {

  public Quote(Node node) {
  }

  void print(Node c, int n, boolean p) {
     System.out.print("'");
     if(c.getCdr() instanceof Nil) {
        System.out.print("");	
     } else {
     	c.getCdr().print(0, false); 
     }
  }

  public Node eval(Node node, Environment env) {
    return node.getCdr().getCar();
  }
}
