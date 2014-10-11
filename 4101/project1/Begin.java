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
			throw new IllegalArgumentException("SYNTAX ERROR");
		}

		System.out.println();
		for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }


		System.out.print("/BEGIN");
    }

    void printQuote(Node c, int n, boolean p) {

    }
}
