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
                System.out.println("------------- HOUSTON, WE HAVE LIFTOFF. STARTING EVAL -------------")   ;
                System.out.print("[MAIN]: Parser returned a: ");
                System.out.println(root);

                Node result = root.eval(env);
                System.out.print("[MAIN]: When eval'd, we got back a: ");
                System.out.println(result);

                System.out.println("[MAIN]: Now we'll try to call .print(0) on it:");
                result.print(0);
                System.out.println("[MAIN]: SUCCESSFUL EVAL!");
            } catch (NullPointerException e) {
                System.out.print("ERROR, NPE: ");
                System.out.println(e.getMessage());
            }

            System.out.print("> ");
            root = parser.parseExp();
        }

        System.exit(0);
    }

}
