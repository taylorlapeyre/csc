import java.io.*;

class Cond extends Special {

	public Cond() { 
	}
	
	void printTheElements(Cons c, int n, boolean isAQuote) {
	   if(isAQuote == true) {
	      System.out.print(" ");
		  printQuote(c.getCar(), n, false);
	   } else {
	      for(int i = 0; i < n; i++) {
				System.out.print(" ");
	      }		
		  c.getCar().print(n);
		  System.out.println();
	   }

	   if(c.getCdr() != null) {
          printTheElements((Cons)c.getCdr(), n, isAQuote);
	   }
	}
 
    void print(Node c, int n, boolean p) {
       if(p != true) {
          System.out.print("(");
       }
       System.out.println("cond");
       if(c.getCdr() != null) {
          printTheElements((Cons)c.getCdr(), n+4, false);
       }
       for(int i = 0; i < n; i++) {
          System.out.print(" ");
       }
       System.out.print(")");
    }

    void printQuote(Node c, int n, boolean p) {
       if(p != true) {
          System.out.print("(");
       }
       System.out.print("cond ");
       if(c.getCdr() != null) {
          printTheElements((Cons)c.getCdr(), 0, true);
       }
       System.out.print(")");
    }
}
