class Environment extends Node {

    // An Environment is implemented like a Cons node, in which
    // every list element (every frame) is an association list.
    // Instead of Nil(), we use null to terminate the list.

    private Node scope;       // the innermost scope, an association list
    private Environment env;      // the enclosing environment

    public Environment()    {
        scope = new Nil();
        env = null;
    }
    public Environment(Environment e) {
        scope = new Nil();
        env = e;
    }

    public void print(int n) {
        for (int i = 0; i < n; i++)
            System.out.print(' ');
        System.out.println("#{Environment");
        scope.print(n+3);
        if (env != null)
            env.print(n+3);
        for (int i = 0; i < n; i++)
            System.out.print(' ');
        System.out.println('}');
    }

    // This is not in an object-oriented style, it's more or less a
    // translation of the Scheme assq function.
    private static Node find (Node id, Node alist) {
        if (!alist.isPair())
            return null; // in Scheme we'd return #f
        else {
            Node bind = alist.getCar();
            if (id.getName().equals(bind.getCar().getName()))
                // return a list containing the value as only element
                xreturn bind.getCdr();
            else
                return find(id, alist.getCdr());
        }
    }

    public Node lookup (Node id) {
        Node val = find(id, scope);
        if (val == null && env == null) {
            System.out.println("undefined variable");
            return null;
        } else if (val == null)
            // look up the identifier in the enclosing scope
            return env.lookup(id);
        else
            // get the value out of the list we got from find()
            return val.getCar();
    }

    public void define (Node id, Node val) {
        scope.set(id, val);
    }

    public void assign (Node id, Node val) {
        Node foundNode = scope.find(id);

        if (foundNode == null) {
            scope.assign(id, val);
        } else {
            scope.set(id, val);
        }
    }

    public void makeGlobalEnvironment() {
        String[] builtins = {"b+", "b-", "b*", "b/", "b=", "b<", "b>", "number?", "null?",
            "symbol?", "car", "cdr", "set-car!", "set-cdr!", "cons", "eq?",
            "procedure?", "read", "write", "eval", "apply", "display",
            "newline", "interaction-environment"};
            Ident id;
            for (int i = 0; i < builtins.length; i++) {
                id = new Ident(s);
                this.define(id, new Builtin(id));
            }
    }
}
