import java.io.*;
import java.util.*;

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
    Ident id;

    id = new Ident("b+");
    env.define(id, new BuiltIn(id));

    id = new Ident("b-");
    env.define(id, new BuiltIn(id));

    id = new Ident("b*");
    env.define(id, new BuiltIn(id));

    id = new Ident("b/");
    env.define(id, new BuiltIn(id));

    id = new Ident("b=");
    env.define(id, new BuiltIn(id));

    id = new Ident("b<");
    env.define(id, new BuiltIn(id));

    id = new Ident("b>");
    env.define(id, new BuiltIn(id));

    id = new Ident("car");
    env.define(id, new BuiltIn(id));

    id = new Ident("cdr");
    env.define(id, new BuiltIn(id));

    id = new Ident("cons");
    env.define(id, new BuiltIn(id));

    id = new Ident("set-car!");
    env.define(id, new BuiltIn(id));

    id = new Ident("set-cdr!");
    env.define(id, new BuiltIn(id));

    id = new Ident("symbol?");
    env.define(id, new BuiltIn(id));

    id = new Ident("number?");
    env.define(id, new BuiltIn(id));

    id = new Ident("null?");
    env.define(id, new BuiltIn(id));

    id = new Ident("pair?");
    env.define(id, new BuiltIn(id));

    id = new Ident("eq?");
    env.define(id, new BuiltIn(id));

    id = new Ident("procedure?");
    env.define(id, new BuiltIn(id));

    id = new Ident("display");
    env.define(id, new BuiltIn(id));

    id = new Ident("newline");
    env.define(id, new BuiltIn(id));

    id = new Ident("exit");
    env.define(id, new BuiltIn(id));

    id = new Ident("quit");
    env.define(id, new BuiltIn(id));

    id = new Ident("read");
    env.define(id, new BuiltIn(id));

    id = new Ident("write");
    env.define(id, new BuiltIn(id));

    id = new Ident("eval");
    env.define(id, new BuiltIn(id));

    id = new Ident("apply");
    env.define(id, new BuiltIn(id));

    id = new Ident("interaction-environment");
    env.define(id, new BuiltIn(id));

    Environment env2 = new Environment(env);
    BuiltIn builtIn = new BuiltIn(env2);

    Parser parser = new Parser(scanner);
    Node root = parser.parseExp();

    while (root != null) {
       if(env2 != null) {
          root.eval(env2).print(0);
          root = parser.parseExp();
        } else {
           System.err.println("Error: No environment is defined.");
       }
    }
    System.exit(0);
  }
}
