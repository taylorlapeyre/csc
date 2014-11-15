import java.io.*;

public class Main {
    public static final String TokenName[] = {
        "QUOTE",
        "LPAREN",
        "RPAREN",
        "DOT",
        "TRUE",
        "FALSE",
        "INT",
        "STRING",
        "IDENT"
    };


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args.length > 2) {
            System.err.println("Usage: java Main " + "[-d]");
            System.exit(1);
        }

        Environment env = new Environment();
        env.defineBuiltIns();
        System.out.println("Built-in procedures have been defined.");
        System.out.println("Interpreter is ready for evaluaion.");

        Parser parser = new Parser(scanner);
        System.out.print("> ");
        Node root = parser.parseExp();

        while (root != null) {
            try {
                root.eval(env).print(0);
            } catch (NullPointerException e) {
                System.out.print("NPE: ");
                System.out.println(e.getMessage());
            }

            System.out.print("> ");
            root = parser.parseExp();
        }

        System.exit(0);
    }

}
