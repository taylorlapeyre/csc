import java.io.*;

class Set extends Special
{
	private Cons cons;

	public Set(Cons c) {
		this.cons = c;
	}

    void print(Node c, int n, boolean p) {
   		if(p != true) {
    	   System.out.print("(");
    	}
    	if(cons.getCar() instanceof Cons) {
    	   cons.getCar().print(n, false);
    	} else {
       	   cons.getCar().print(n, true);
    	}

    	if(cons.getCdr() != null) {
    	   System.out.print(" ");
    	}

    	if(cons.getCdr() != null) {
    	   cons.getCdr().print(n, true);
    	} else {
    	   System.out.println(")");
    	}
	}

	void printQuote(Node c, int n, boolean p) {
		print(c, n, p);
	}
}
