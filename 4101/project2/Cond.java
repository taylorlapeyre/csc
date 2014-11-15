import java.io.*;

class Cond extends Special {

	public Cond(Node node){
	}

    void print(Node c, int n, boolean p) {
		for (int i = 0; i < n; i++) {
			System.out.print(' ');
		}
		System.out.println("(cond");

		Node conditions = c.getCdr();
		if (conditions.isPair()) {
			conditions.print(n + 2, true);
		} else {
			throw new IllegalArgumentException("SYNTAX ERROR");
		}
    }

    public Node eval(Node node, Environment env) {
    	Node exp;
    	exp = node.getCdr();

    	while ((!(exp.getCar()).getCar().eval(env).getBool()) && (!exp.isNull())) {
    		exp = exp.getCdr();
    	}

    	if(exp.isNull()) {
    		return new Nil();
    	} else {
    		return (exp.getCar().getCdr().getCar().eval(env));
    	}
    }
}
