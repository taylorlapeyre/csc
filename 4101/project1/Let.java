import java.io.*;

class Let extends Special
{
   public Let() {
   }

   void print(Node c, int n, boolean p) {
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print("(let ");

        Node assignments = c.getCdr().getCar();
        if (assignments.isPair()) {
            assignments.print(0, false);
        } else {
            // raise exeption
        }

        System.out.println();

        Node body = c.getCdr().getCdr();
        if (body.isPair()) {
            body.print(n + 2, true);
        } else {
            // raise exception
        }
   }

   void printQuote(Node c, int n, boolean p) {

    }
}
