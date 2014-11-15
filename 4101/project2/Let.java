import java.io.*;

class Let extends Special
{
   public Let(Node node) {
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
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        System.out.println();

        Node body = c.getCdr().getCdr();
        if (body.isPair()) {
            body.print(n + 2, true);
        } else {
            throw new IllegalArgumentException("SYNTAX ERROR");
        }

        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(' ');
        }
        System.out.print("/LET");
   }

   public Node eval(Node node, Environment env) {
        Environment localEnv = new Environment(env);
        Node args = node.getCdr().getCar();
        Node exp = node.getCdr().getCdr().getCar();
        args = letEnvironment(args, localEnv);
        return exp.eval(localEnv);
   }

   public Node letEnvironment(Node node, Environment env) {
        if(node.isNull() || node == null) {
            Node letList = new Cons(new Nil(), new Nil());
            return letList;
        } else {
            Node arg = node.getCar().getCar();
            Node exp = node.getCar().getCdr().getCar();
            Node rest = node.getCdr();

            if(arg.isSymbol()) {
                env.define(arg, exp.eval(env));
                return letEnvironment(rest, env);
            } else if(arg.isPair()) {
                return arg.eval(env);
            } else if(arg.isNull() || arg == null) {
                return new Nil();
            }
        }
        return null;
   }

}
