// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner
{
    private PushbackInputStream in;
    private byte[] buf = new byte[1000];

    public Scanner(InputStream i) {
        in = new PushbackInputStream(i);
    }

    public char readNextChar() {
        int bite = -1;

        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail: " + e.getMessage());
        }

        return bite;
    }

    public Token getNextToken() {

        char bite = readNextChar();

        // Nothing
        if (bite == -1) {
            return null;
        }

        char ch = (char) bite;

        // Whitespace
        if (ch == " " || ch == "\n") {
            return getNextToken();
        } else if (ch == ";") {
            do {
                bite = in.read();
            } while (ch != "\n");
            return getNextToken();
        }

        // Special characters
        if (ch == '\'') {
            return new Token(Token.QUOTE);
        } else if (ch == '(') {
            return new Token(Token.LPAREN);
        } else if (ch == ')') {
            return new Token(Token.RPAREN);
        } else if (ch == '.') {
            return new Token(Token.DOT);
        }

        // Boolean constants
        if (ch == '#') {
            bite = readNextChar();

            if (bite == -1) {
                System.err.println("Unexpected EOF following #");
                return null;
            }

            ch = (char) bite;

            if (ch == 't') {
                return new Token(Token.TRUE);
            } else if (ch == 'f') {
                return new Token(Token.FALSE);
            } else {
                System.err.println("Illegal character '" + (char) ch + "' following #");
                return getNextToken();
            }
        }

        // String constants
        if (ch == '"') {
            for (int i = 0; i < buf.length; i++) {
                try {
                    ch = (char) readNextChar();

                    if (ch == "\"") {
                        buf[i] = (byte) '\\';
                        i++;
                        ch = (char) in.read();
                        buf[i] = (byte) ch;
                        continue;
                    }

                    if (ch == '\"') {
                        break;
                    }
                    buf[i] = (byte) ch;
                } catch (IllegalArgumentException e) {
                    System.err.println("Error, please try again: " + e.getMessage());
                }
            }
            byte[] str = new byte[i];
            for (int j = 0; j < i; j++) {
                str[j] = buf[j];
            }
            return new StrToken(new String(str));
        }


        // Integer constants
        if (ch >= '0' && ch <= '9') {
            int i = ch - '0';
            int outcome = 0;

            do {
                try {
                    outcome = outcome * 10 + i;
                    ch = (char) readNextChar();
                    i = ch - '0';
                } catch (IOException e) {
                    System.err.println("Failed to create Integer constants: " + e.getMessage());
                }
            } while (ch >= '0' && ch <= '9');

            try {
                in.unread((byte) ch);
            } catch (IllegalArgumentException e) {
                System.err.println("Error, please try again: " + e.getMessage());
            }

            return new IntToken(outcome);
        }

        // Identifiers
        if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
            buf[0] = (byte) ch;
            int i = 1;

            do {
                try {
                    bite = readNextChar();
                    buf[i] = (byte) bite;
                    ch = (char) bite;
                    i++;
                } catch (IllegalArgumentException e) {
                    System.err.println("Error, please try again: " + e.getMessage());
                }
            } while (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z');

            try {
                in.unread((byte) ch);
            } catch (IOException e) {
                System.err.println("We Fail!!! " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Error, please try again: " + e.getMessage());
            }

            return new IdentToken(buf.toString());
        }

        // Illegal character
        System.err.println("Illegal input character '" + (char) ch + '\'');
        return getNextToken();
    }
};