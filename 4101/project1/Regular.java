import java.io.*;

class Regular extends Special
{
	private Cons cons;

	public Regular() {}

  void print(Node c, int n, boolean p) {
	for (int i = 0; i < n; i++) {
	  System.out.print(' ');
	}

	if (!p) {
		System.out.print('(');
	}

	c.getCar().print(0);
	System.out.print(' ');
	c.getCdr().print(0, true);
  }

  void printQuote(Node c, int n, boolean p) {
  }
}
