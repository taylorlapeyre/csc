import java.io.*;

class Begin extends Special {

    public Begin() {}

    void print(Node c, int n, boolean p) {
		for (int i = 0; i < n; i++) {
			System.out.print(' ');
		}
		System.out.println("(begin");

		if (c.getCdr().isPair()) {
			System.out.println();
			c.getCdr().print(n + 2, p);

		} else {
			throw new IllegalArgumentException("SYNTAX ERROR");
		}

		for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }


		System.out.print("/BEGIN");
    }
}
