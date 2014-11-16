import java.io.*;

class If extends Special {

    public If() { }

    void print(Node c, int n, boolean p) {
        Printer.printIf(c, n, p);
    }

    public Node eval(Node t, Environment env) {
		System.out.println("[IN IF EVAL]");

        Node predicate  = t.getCdr().getCar();
        Node ifClause   = t.getCdr().getCdr().getCar();
        Node elseClause = t.getCdr().getCdr().getCdr().getCar();

		System.out.println("predicate: " + predicate);
		System.out.println("ifClause: " + ifClause);
		System.out.println("elseClause: " + elseClause);

		boolean result = predicate.eval(env).getBoolean();

		System.out.println("THE PREDICATE IS " + result);

        if (predicate.eval(env).getBoolean()) {
			System.out.println("EVALING THE IF, because it's true");
            return ifClause.eval(env);
        } else {
			System.out.println("EVALING THE ELSE, because it's false");
            return elseClause.eval(env);
        }
    }
}
