// Closure.java -- the data structure for function closures

// Class Closure is used to represent the value of lambda expressions.
// It consists of the lambda expression itself, together with the
// environment in which the lambda expression was evaluated.

// The method apply() takes the environment out of the closure,
// adds a new frame for the function call, defines bindings for the
// parameters with the argument values in the new frame, and evaluates
// the function body.

class Closure extends Node {
    private Node arguments;       // a lambda expression
    private Node body;
    private Environment env;      // the environment in which the function
    // was defined

    public Closure(Node arguments, Node body, Environment e) {
        this.arguments = arguments;
        this.body      = body;
        this.env       = e;
    }

    public Node getFun()    {
        return body;
    }
    public Environment getEnv()   {
        return env;
    }

    public boolean isProcedure()  {
        return true;
    }

    public void print(int n) {
        // there got to be a more efficient way to print n spaces
        for (int i = 0; i < n; i++)
            System.out.print(' ');
        System.out.println("#{Procedure");

        body.print(n+3);

        for (int i = 0; i < n; i++)
            System.out.print(' ');

        System.out.println('}');
    }


    public Node apply (Node values, Environment env2) {
        Node arguments = this.arguments;
        Environment closureEnv = new Environment(this.env);


        while (!arguments.isNull() && !values.isNull()) {
            Node key = arguments.getCar();
            Node val = values.getCar();

            env.define(key, val.eval(env));

            arguments = arguments.getCdr();
            values = values.getCdr();
        }

        return body.eval(closureEnv);
    }
}
