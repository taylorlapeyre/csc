import java.io.*;

class Lambda extends Special {

    public Lambda() { }

    void print(Node c, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print("(lambda ");

        Node secondNode = c.getCdr().getCar();
        if (secondNode.isPair()) {
            secondNode.print(0, false);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        System.out.println();

        Node thirdNode = c.getCdr().getCdr().getCar();
        if (thirdNode.isPair()) {
            thirdNode.print(n + 2, false);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print(')');
    }

    public Node eval(Node t, Environment env) {
        return new Closure(t, env);
    }
}
