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

    if (args.length == 2 && args[0].equals("-d")) {
      Token token = scanner.getNextToken();
      while (token != null) {
        int tokenType = token.getType();
        System.out.print(TokenName[tokenType]);

        if (tokenType == Token.INT) {
          System.out.println(", intVal = " + token.getIntVal());
        } else if (tokenType == Token.STRING) {
          System.out.println(", strVal = " + token.getStrVal());
        } else if (tokenType == Token.IDENT) {
          System.out.println(", name = " + token.getName());
        } else {
          System.out.println();
        }

        token = scanner.getNextToken();
      }
    }

    Parser parser = new Parser(scanner);
    Node root = parser.parseNextExp();

    while (root != null) {
      root.print(0);
      root = parser.parseNextExp();
    }

    System.exit(0);
  }

}
