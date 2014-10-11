import java.io.*;

class Define extends Special {

    public Define() {}

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

        System.out.print(' ');

        Node value = c.getCdr().getCdr().getCar();
        if (!value.isNull()) {
            value.print(0, true);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print("/DEFINE");
    }

    public void printQuote(Node c, int n, boolean p) {

    }
}
