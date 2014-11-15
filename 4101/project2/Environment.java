class Environment extends Node {

    // An Environment is implemented like a Cons node, in which
    // every list element (every frame) is an association list.
    // Instead of Nil(), we use null to terminate the list.

    private Frame scope;       // the innermost scope, an association list
    private Environment env;      // the enclosing environment

    public Environment()    {
        scope = new Frame();
        env = null;
    }
    public Environment(Environment e) {
        scope = new Frame();
        env = e;
    }

    public void print(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(' ');
        System.out.println("#{Environment");
        scope.printScope(n+3);
        if (env != null)
            env.print(n+3);
        for (int i = 0; i < n; i++)
            System.out.print(' ');
        System.out.println('}');
    }

    public Node lookup (Node id) {
        Node val = scope.find(id);
        if (val == null && env == null) {
            System.out.println("Unable to find identifier in current context");
            return Nil.getInstance();
        } else if (val == null)
            // look up the identifier in the enclosing scope
            return env.lookup(id);
        else
            // get the value out of the list we got from find()
            return val;
    }

    public void define (Node id, Node val) {
        scope.set(id, val);
    }

    public void assign (Node id, Node val) {
        Node foundNode = scope.find(id);

        if (foundNode == null) {
            // double check this...
            scope.set(id, val);
            //scope.assign(id, val);
        } else {
            scope.set(id, val);
        }
    }

    public void defineBuiltIns() {
        String[] builtins = {"b+", "b-", "b*", "b/", "b=", "b<", "b>", "number?", "null?",
                            "symbol?", "car", "cdr", "set-car!", "set-cdr!", "cons", "eq?",
                            "procedure?", "read", "write", "eval", "apply", "display",
                            "newline", "interaction-environment"};
        Ident id;
        for (int i = 0; i < builtins.length; i++) {
            id = new Ident(builtins[i]);
            this.define(id, new BuiltIn(id));
        }
    }
}
