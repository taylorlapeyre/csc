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

        Parser parser = new Parser(scanner);
        System.out.print("> ");
        Node root = parser.parseExp();

        while (root != null) {
            try {
                Node result = root.eval(env);
                result.print(0);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }

            System.out.print("> ");
            root = parser.parseExp();
        }

        System.exit(0);
    }

}
