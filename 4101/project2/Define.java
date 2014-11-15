import java.io.*;

class Define extends Special {

    public Define(Node node) {}

    void print(Node c, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }

        System.out.print("(define ");

        Node name = c.getCdr().getCar();
        if (name.isSymbol()) {
            name.print(0, false);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        System.out.print(" ");

        Node value = c.getCdr().getCdr().getCar();
        if (!value.isNull()) {
            value.print(0, true);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }
        System.out.print(")");
    }

    public Node eval(Node node, Environment env) {
        Node id = node.getCdr().getCar();
        Node val = node.getCdr().getCdr().getCar();

        if(id.isSymbol()) {
            env.define(id, val);
        } else {
            Closure clos = new Closure(new Cons(node.getCdr().getCar().getCdr(), node.getCdr().getCdr()), env);
            env.define(id.getCar(), clos);
        }
        return new StrLit(": No values have been found");
    } 
}
