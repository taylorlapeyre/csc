import java.io.*;

class Regular extends Special 
{
	private Cons cons;
	
	public Regular(Cons c) 
	{
       this.cons=c;	
	}
 
    void print(Node t, int n, boolean p) 
    {
       if(!p)
       {
          System.out.print("(");
       }

       if (cons.getCar() instanceof Cons || cons.getCar() instanceof Nil) 
       {
    	  cons.getCar().print(n, false);
       } 
       else 
       { 
          cons.getCar().print(n, true);
       }
       if (cons.getCdr() != null)
	   {
	   	  System.out.print(" ");
	   	  cons.getCdr().print(n, true);
	   }
       else 
       {
	      System.out.print(")");		
	   }
    }
    void printQuote(Node t, int n, boolean p)
    {
    	 print(t, n, p);
    }

    public Cons getCons()
    {
      return cons;
    }
    
    @Override
	  public void interprinter() 
    {
       System.out.print("(");
       cons.getCar().interprinter();
       Node next = cons.getCdr();
       while (next != null) 
       {
          System.out.print(" ");
          next.getCar().interprinter();
          next = next.getCdr();
       }
       System.out.print(")"); 
    }
}
