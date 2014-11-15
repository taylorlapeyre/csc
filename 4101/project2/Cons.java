class Cons extends Node {
    private Node car;
    private Node cdr;
    private Special specialForm;

    public Cons(Node a, Node d) {
      car = a;
      cdr = d;
      parseList();
    }

    private void parseList() {
      if (car.isSymbol()) {
        String name = car.getName();
        if(name == "quote") {
          specialForm = new Quote(this);
        } else if (name == "lambda") {
          specialForm = new Lambda(this);
        } else if (name == "if") {
          specialForm = new If(this);
        } else if (name == "begin") {
          specialForm = new Begin(this);
        } else if (name == "let") {
          specialForm = new Let(this);
        } else if (name == "cond") {
          specialForm = new Cond(this);
        } else if (name == "define") {
          specialForm = new Define(this);
        } else if (name == "set!") {
          specialForm =  new Set(this);
        } else {
          specialForm = new Regular(this);
        }
      } else {
        specialForm = new Regular(this);
      }
    }

    void print(int n) {
    	specialForm.print(this, n, false);
    }

    void print(int n, boolean p) {
    	specialForm.print(this, n, p);
    }

    @Override
    public Node getCar() {
      return this.car;
    }

    @Override
    public Node getCdr() {
      return this.cdr;
    }

    public void setCar(Node node) {
      car = node;
      parseList();
    }

    public void setCdr(Node node) {
      cdr = node;
      parseList();
    }

    @Override
    public boolean isPair() {
        return true;
    }

    @Override
    public Node eval(Environment env) {
      return specialForm.eval(this, env);
    }

}
