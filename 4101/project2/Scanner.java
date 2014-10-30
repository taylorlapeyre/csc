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
            return new StrToken(sb.toString());
        }


        // Special Characters

        // Quote
        if (ch == '\'') {
            return new Token(Token.QUOTE);
        }

        // Left Parenthesis
        if (ch == '(') {
            return new Token(Token.LPAREN);
        }

        // Right Parenthesis
        if (ch == ')') {
            return new Token(Token.RPAREN);
        }

        if (ch == '.') {
            return new Token(Token.DOT);
        }


        // Boolean Constants
        if (ch == '#') {
            ch = (char)readNextBite();
            if (ch == 't') {
                return new Token(Token.TRUE);
            } else if (ch == 'f') {
                return new Token(Token.FALSE);
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

        if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
            return new IdentToken(Character.toString(ch));
        }



        // Identifiers
        if (isValidIdentCharacter(ch)) {
            StringBuilder sb = new StringBuilder();
            while (isValidIdentCharacter(ch)) {
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

    private boolean isValidIdentCharacter(char ch) {
        return(
            (ch >= 'A' && ch <= 'Z') ||
            (ch >= 'a' && ch <= 'z') ||
            ch == '!'||
            ch == '*' ||
            ch == '/' ||
            ch == '-' ||
            ch == '+' ||
            ch == '<' ||
            ch == '>' ||
            ch == '?' ||
            ch == '!'
        );
    }
}
