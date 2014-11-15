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
        System.out.println("Trying to see if " + name + " is a symbol..");
        if (car.isSymbol()) {
            name = car.getName();
            if (name == "quote") {
                form = new Quote();
            } else if (name == "lambda") {
                form = new Lambda();
            } else if (name == "if") {
                form = new If();
            } else if (name == "begin") {
                form = new Begin();
            } else if (name == "let") {
                form = new Let();
            } else if (name == "cond") {
                form = new Cond();
            } else if (name == "define") {
                form = new Define();
            } else if (name == "set!") {
                form = new Set();
            } else {
                form = new Regular();
            }

        } else {
            System.out.println("Not a symbol? Ok, "+name+" is a regular.");
            form = new Regular();
        }

        return form;
    }

    void print(int n) {
        parseList().print(this, n, false);
    }

    void print(int n, boolean p) {
        parseList().print(this, n, p);
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
        return form.eval(this, env);
    }

}
