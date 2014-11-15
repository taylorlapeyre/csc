import java.io.*;

class Set extends Special
{
	private Cons cons;

	public Set(Node node) {}

    void print(Node c, int n, boolean p) {

   		if(p != true) {
    	   System.out.print("(");
    	}
    	if(c.getCar().isPair()) {
    	   c.getCar().print(0, false);
    	} else {
       	   c.getCar().print(0, true);
    	}

        System.out.print(" ");
        Node value = c.getCdr();
        if(value instanceof Nil) {
            System.out.println(")");
        } else {
    	   value.print(0, true);
    	} 
	}

    public Node eval(Node node, Environment env) {
       Node id = node.getCdr().getCar();
       Node exp = node.getCdr().getCdr().getCar();
       env.define(id, exp.eval(env));
       return new StrLit("");
    }
}
