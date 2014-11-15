class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;

    public Cons(Node a, Node d) {
        car = a;
        cdr = d;
        parseList();
    }

    private Special parseList() {
        if (car.isSymbol()) {
            String name = car.getName();
            if (name == "quote")  form = new Quote();
            if (name == "lambda") form = new Lambda();
            if (name == "if")     form = new If();
            if (name == "begin")  form = new Begin();
            if (name == "let")    form = new Let();
            if (name == "cond")   form = new Cond();
            if (name == "define") form = new Define();
            if (name == "set!")   form = new Set();
            form = new Regular();
        } else {
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
        System.out.println("IN CONS EVAL");
        // t.print(0);
        return form.eval(this, env);
    }

}
