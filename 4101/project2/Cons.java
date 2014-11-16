class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;
    private String name;

    public Cons(Node a, Node d) {
        car = a;
        cdr = d;
        parseList();
    }

    private Special parseList() {
        System.out.println("Trying to see if " + car + " is a symbol..");
        if (car.isSymbol()) {
            name = car.getName();

            System.out.println("And it is! Turns out its name is \"" + name + "\"");


            if (name.equals("quote")) {
                System.out.println(name + " is the same as 'quote'.");
                form = new Quote();
            } else if (name.equals("lambda")) {
                System.out.println(name + " is the same as 'lambda'.");
                form = new Lambda();
            } else if (name.equals("if")) {
                System.out.println(name + " is the same as 'if'.");
                form = new If();
            } else if (name.equals("begin")) {
                System.out.println(name + " is the same as 'begin'.");
                form = new Begin();
            } else if (name.equals("let")) {
                System.out.println(name + " is the same as 'let'.");
                form = new Let();
            } else if (name.equals("cond")) {
                System.out.println(name + " is the same as 'cond'.");
                form = new Cond();
            } else if (name.equals("define")) {
                System.out.println(name + " is the same as 'define'.");
                form = new Define();
            } else if (name.equals("set!")) {
                System.out.println(name + " is the same as 'set!'.");
                form = new Set();
            } else {
                System.out.println(name + " is none of the special forms.");
                form = new Regular();
            }

        } else {
            System.out.println("Not a symbol? Ok, "+car+" is a regular.");
            form = new Regular();
        }

        return form;
    }

    public void print(int n) {
        System.out.println("<LIST !!FIX ME!!>");
    }

    public void print(int n, boolean p) {
        System.out.println("<LIST !!FIX ME!!>");
    }

    @Override
    public Node getCar() {
        return this.car;
    }

    @Override
    public Node getCdr() {
        return this.cdr;
    }

    public boolean isPair() {
        return true;
    }

    public Node eval(Environment env) {
        System.out.println("[IN CONS EVAL]");
        System.out.println("FORM IS " + form);
        System.out.println("NAME IS " + name);
        return form.eval(this, env);
    }

}
