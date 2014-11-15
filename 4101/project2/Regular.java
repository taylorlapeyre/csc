import java.io.*;

class Regular extends Special
{
	private Cons cons;

	public Regular(Node node) {}

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

  public Node eval(Node node, Environment env) {
  	Node firstNode = node.getCar();
  	Node someArgs = evalList(node.getCdr(), env);

  	while(firstNode.isSymbol()) {
  		firstNode = env.lookup(firstNode);
  	}

  	if(firstNode.isNull() || firstNode == null) {
  		return new Nil();
  	}

  	if(firstNode.isProcedure()) {
  		return firstNode.apply(someArgs);
  	} else {
  		return firstNode.eval(env).apply(someArgs);
  	}
  }

  public Node evalList(Node node, Environment env) {
  	if(node.isNull() || node == null) {
  		Node evalList = new Cons(new Nil(), new Nil());
  		return evalList; 
  	} else {
  		Node arg = node.getCar();
  		Node rest = node.getCdr();

  		if(arg.isSymbol()) {
  			arg = env.lookup(arg);
  		}

        if (arg.isNull() || arg == null) {
            return new Nil();
        }else {
        }

  		Node evalList = new Cons(arg.eval(env), evalList(rest, env));
  		return evalList;
  	}
  }
}
