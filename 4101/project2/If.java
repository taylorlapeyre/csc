import java.io.*;

class If extends Special {

	public If() { }

    void print(Node c, int n, boolean p) {
		for (int i = 0; i < n; i++) {
			System.out.print(' ');
		}
		System.out.print("(if ");

		Node predicate = c.getCdr().getCar();
		if (predicate.isPair()) {
			predicate.print(0, p);
		} else {
			throw new IllegalArgumentException("SYNTAX ERROR");
		}

		System.out.println();

		Node thenClause = c.getCdr().getCdr().getCar();
		if (!thenClause.isNull()) {
			thenClause.print(n + 2, p);
		} else {
			throw new IllegalArgumentException("SYNTAX ERROR");
		}

		System.out.println();

		Node elseClause = c.getCdr().getCdr().getCdr().getCar();
		if (!elseClause.isNull()) {
			elseClause.print(n + 2, p);
		} else {
			throw new IllegalArgumentException("SYNTAX ERROR");
		}

		for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
		System.out.print(')');
    }

	public Node eval(Node t, Environment env) {
		return null;
	}
}
