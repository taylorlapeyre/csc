// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner 
{
  private PushbackInputStream in;
  private byte[] buf = new byte[1000];

  public Scanner(InputStream i) { in = new PushbackInputStream(i); }
    
  public Token getNextToken() 
  {
    int bite = -1;
	
    // It would be more efficient if we'd maintain our own input buffer
    // and read characters out of that buffer, but reading individual
    // characters from the input stream is easier.
    try 
    {
       bite = in.read();
    } 
    catch (IOException e) 
    {
       System.err.println("We fail: " + e.getMessage());
    }

    if (bite == -1)
    {
       return null;
    }

    char ch = (char) bite;

    // Ignore Whitespace
    // TODO: Test if ignoring whitespace actually works
    if (ch == " " || ch == "\n") 
    {
      return getNextToken();
    } 
    else if (ch == ";") 
    {
      do 
      {
        bite = in.read();
      } while (ch != "\n");
      return getNextToken();
    }
	
    // Special characters
    if (ch == '\'')
      return new Token(Token.QUOTE);
    else if (ch == '(')
      return new Token(Token.LPAREN);
    else if (ch == ')')
      return new Token(Token.RPAREN);
    else if (ch == '.')
      // We ignore the special identifier `...'.
      return new Token(Token.DOT);

    // Boolean constants
    else if (ch == '#') 
    {
      try 
      {
	       bite = in.read();
      } 
      catch (IOException e) 
      {
	       System.err.println("We fail: " + e.getMessage());
      }

      if (bite == -1) 
      {
	       System.err.println("Unexpected EOF following #");
	       return null;
      }
      ch = (char) bite;
      if (ch == 't')
	       return new Token(Token.TRUE);
      else if (ch == 'f')
	       return new Token(Token.FALSE);
      else 
      {
	       System.err.println("Illegal character '" + (char) ch + "' following #");
	       return getNextToken();
      }
    }

    // String constants
    else if (ch == '"') 
    {
      for(int i = 0; i<buf.length; i++)
      {
        try
        {
          ch = (char)in.read();
          if(ch == "\\")
          {
            buf[i] = (byte)'\\';
            i++;
            ch = (char)in.read();
            buf[i] = (byte)ch;
            continue;
          }
          if(ch == '\"')
          {
            break;
          }
          buf[i] = (byte)ch;
        }
      }
      catch(IllegalArgumentException e)
      {
         System.err.println("Error, please try again: " + e.getMessage());
      }
      catch(IOException e)
      {
         System.err.println("Failed I/O operation. Please try again: " + e.getMessage())
      }
    }
  
    byte[] str = new byte[i];
    for(int j = 0; j < i; j++)
    {
       str[j] = buf[j];
    }
    return new StrToken(new String(str));
    }

 
    // Integer constants
    else if (ch >= '0' && ch <= '9') 
    {
       int i = ch - '0';
       int outcome = 0;

       do 
       {
          try 
          {
             outcome = outcome * 10 + i;
             ch = (char) in.read();
             i = ch - '0'; 
          }
          catch(IOException e)
          {
             System.err.println("Failed to create Integer constants: " + e.getMessage());
          }
       } while(ch >= '0' && ch <= '9');

       try
       {
          in.unread((byte) ch);
       }
       catch(IllegalArgumentException e)
       {
          System.err.println("Error, please try again: " + e.getMessage());
       }
       catch (IOException e)\
       {
          System.err.println("WE FAIL!!!" + e.getMessage());
       }

      // put the character after the integer back into the input
      // in->putback(ch);
       return new IntToken(outcome);
    }

    // Identifiers
    else if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') 
    {
       buf[0] = (byte) ch;
       int i = 1;
       do
       {
          try
          {
             bite = in.read();
             buf[i] = (byte) bite;
             ch = (char) bite;
             i++;
          }
          catch (IOException e)
          {
             System.err.println("We fail :( " + e.getMessage());
          }
          catch(IllegalArgumentException e)
          {
             System.err.println("Error, please try again: " + e.getMessage());
         }
       } while(ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z');

       // put the character after the identifier back into the input
       // in->putback(ch);
       try 
       {
         in.unread((byte) ch);
       } 
       catch (IOException e) 
       {
         System.err.println("We Fail!!! " + e.getMessage());
       }
       catch(IllegalArgumentException e)
       {
         System.err.println("Error, please try again: " + e.getMessage());
       }
       return new IdentToken(buf.toString());
    }

    // Illegal character
    else 
    {
      System.err.println("Illegal input character '" + (char) ch + '\'');
      return getNextToken();
    }
  };
}
