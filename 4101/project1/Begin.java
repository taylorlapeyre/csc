import java.io.*;

class Begin extends Special {

    public Begin() {}

    void print(Node c, int n, boolean p) {
		for (int i = 0; i < n; i++) {
			System.out.print(' ');
		}
		System.out.println("(begin");

		if (c.getCdr().isPair()) {
			c.getCdr().print(n + 2, true);
		} else {
			// raise exception
		}
    }

    void printQuote(Node c, int n, boolean p) {

    }
}
