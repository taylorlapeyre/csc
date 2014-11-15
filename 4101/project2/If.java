import java.io.*;

class If extends Special {

	public If(Node node) {
	}

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

    public Node eval(Node node, Environment env) {
    	Node exp;
        Node someCond = node.getCdr().getCar();
        if(someCond.eval(env).getBool()) {
        	exp = node.getCdr().getCdr().getCar();
        	return exp.eval(env);
        } else if(!(node.getCdr().getCdr().getCdr()).isNull()) {
        	exp = node.getCdr().getCdr().getCdr().getCar();
        	return exp.eval(env);
        } else {
        	return new Nil();
        }
    }
}
