class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special form;

    public Cons(Node a, Node d) {
      car = a;
      cdr = d;
    }

    private Special parseList() {
      if (car.isSymbol()) {
        String name = car.getName();
        switch (name) {
          case "quote":
            return new Quote();
          case "lambda":
            return new Lambda();
          case "if":
            return new If();
          case "begin":
            return new Begin();
          case "let":
            return new Let();
          case "cond":
            return new Cond();
          case "define":
            return new Define();
          case "set!":
            return new Set();
          default:
            return new Regular();
        }
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

    public void printQuote(int n, boolean p) {
      parseList().printQuote(this, n, p);
    }

    @Override
    public Node getCar() {
      return car;
    }

    @Override
    public Node getCdr() {
      return cdr;
    }

    public boolean isPair() {
        return true;
    }

}
