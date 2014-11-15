class Cons extends Node {
    private Node car;
    private Node cdr;

    public Cons(Node a, Node d) {
        car = a;
        cdr = d;
        parseList();
    }

    private Special parseList() {
        if (car.isSymbol()) {
            String name = car.getName();
            if (name == "quote")  return new Quote();
            if (name == "lambda") return new Lambda();
            if (name == "if")     return new If();
            if (name == "begin")  return new Begin();
            if (name == "let")    return new Let();
            if (name == "cond")   return new Cond();
            if (name == "define") return new Define();
            if (name == "set!")   return new Set();
            return new Regular();
        } else {
            return new Regular();
        }
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

}
