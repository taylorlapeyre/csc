import java.io.*;

class Set extends Special
{
	private Cons cons;

	public Set() {}

    void print(Node c, int n, boolean p) {
   		if(p != true) {
    	   System.out.print("(");
    	}
    	if(c.getCar() instanceof Cons) {
    	   c.getCar().print(n, false);
    	} else {
       	   c.getCar().print(n, true);
    	}

    	if(c.getCdr() != null) {
    	   System.out.print(" ");
    	}

    	if(c.getCdr() != null) {
    	   c.getCdr().print(n, true);
    	} else {
    	   System.out.println(")");
    	}
	}

	void printQuote(Node c, int n, boolean p) {
		print(c, n, p);
	}
}
