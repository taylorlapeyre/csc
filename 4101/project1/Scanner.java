package taylorlapeyre; /**
 * Created by taylorlapeyre on 10/9/14.
 */

import java.io.*;

public class Scanner {
    private PushbackInputStream in;

    public Scanner(InputStream input) {
        in = new PushbackInputStream(input);
    }

    public int readNextBite() {
        int bite = -1;
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("Error getting input: " + e.getMessage());
        }

        return bite;
    }

    public Token getNextToken() {
        int bite = readNextBite();

        // Nothing
        if (bite == -1) {
            return null;
        }

        char ch = (char)bite;


        // Whitespace
        if (ch == ' ' || ch == '\n') {
            return getNextToken();
        }


        // Comments
        if (ch == ';') {
            while (ch != '\n') {
                ch = (char)readNextBite();
            }
            return getNextToken();
        }


        // Strings
        if (ch == '"') {
            StringBuilder sb = new StringBuilder();
            ch = (char)readNextBite();
            while (ch != '"') {
                sb.append(ch);
                ch = (char)readNextBite();
            }
            return new StringToken(sb.toString());
        }


        // Special Characters
        // Quote
        if (ch == '\'') {
            return new Token("QUOTE");
        }

        // Left Parenthesis
        if (ch == '(') {
            return new Token("LPAREN");
        }

        // Right Parenthesis
        if (ch == ')') {
            return new Token("RPAREN");
        }

        if (ch == '.') {
            return new Token("DOT");
        }


        // Boolean Constants
        if (ch == '#') {
            ch = (char)readNextBite();
            if (ch == 't') {
                return new Token("TRUE");
            } else if (ch == 'f') {
                return new Token("FALSE");
            } else {
                System.err.println("Invalid boolean constant");
            }
        }


        // Integer Constants
        if (ch >= '0' && ch <= '9') {
            StringBuilder sb = new StringBuilder();
            while (ch >= '0' && ch <= '9') {
                sb.append(ch);
                ch = (char)readNextBite();
            }

            try {
                in.unread((byte) ch);
            } catch (java.io.IOException e) {
                System.err.println("No character to unread!");
            }

            return new IntToken(Integer.parseInt(sb.toString()));
        }


        // Identifiers
        if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
            StringBuilder sb = new StringBuilder();
            while (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
                sb.append(ch);
                ch = (char)readNextBite();
            }

            try {
                in.unread((byte) ch);
            } catch (java.io.IOException e) {
                System.err.println("No character to unread!");
            }

            return new IdentToken(sb.toString());
        }

        System.err.println("Illegal input character '" + (char) ch + '\'');
        return getNextToken();
    }
}
