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
        if (car.isSymbol()) {
            name = car.getName();

            if (name.equals("quote")) {
                form = new Quote();
            } else if (name.equals("lambda")) {
                form = new Lambda();
            } else if (name.equals("if")) {
                form = new If();
            } else if (name.equals("begin")) {
                form = new Begin();
            } else if (name.equals("let")) {
                form = new Let();
            } else if (name.equals("cond")) {
                form = new Cond();
            } else if (name.equals("define")) {
                form = new Define();
            } else if (name.equals("set!")) {
                form = new Set();
            } else {
                form = new Regular();
            }

        } else {
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

    public Node getCar() {
        return this.car;
    }

    public Node getCdr() {
        return this.cdr;
    }

    public boolean isPair() {
        return true;
    }

    public Node eval(Environment env) {
        return form.eval(this, env);
    }

}
