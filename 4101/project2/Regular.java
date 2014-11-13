import java.io.*;

class Regular extends Special {
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

	public Node eval(Node t, Environment env) {
		Node first = t.getCar();
		Node rest  = t.getCdr();

		if (first.isSymbol()) {
			first = env.lookup(first);
		}

		if (first.isNull() || first == null) {
			return null;
		}

		if (first.isProcedure()) {
			return first.apply(rest, env);
		} else {
			return first.eval(env).apply(rest, env);
		}
	}
}
