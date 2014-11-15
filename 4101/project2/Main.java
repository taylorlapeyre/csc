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

        Parser parser = new Parser(scanner);
        Node root = parser.parseExp();

        while (root != null) {
            root.eval(env).print(0);
            root = parser.parseExp();
        }

        System.exit(0);
    }

}
