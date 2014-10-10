import java.io.*;

class Regular extends Special
{
	private Cons cons;

	public Regular() {}

  void print(Node c, int n, boolean p) {
		if(p != true) {
			System.out.print("(");
		}
		if(c.getCar() instanceof Nil || c.getCar() instanceof Cons) {
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
			System.out.print(")");
		}
  }

  void printQuote(Node c, int n, boolean p) {
  	print(c, n, p);
  }
}
