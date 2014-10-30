import java.io.*;

class Set extends Special
{
	private Cons cons;

	public Set() {}

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
}
