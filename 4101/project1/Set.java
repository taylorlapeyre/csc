import java.io.*;

class Set extends Special 
{
	private Cons cons;
	
	public Set(Cons c) 
	{
		this.cons=c;
	}

    void print(Node t, int n, boolean p) 
    {
       if(!p)
       {
          System.out.print("(");
       }
       if (cons.getCar() instanceof Cons) 
       {
		      cons.getCar().print(n, false);	
       }
       else 
       { 
       	  cons.getCar().print(n, false);
       }
       if (cons.getCdr() != null)
       {
		      System.out.print(" ");
		      cons.getCdr().print(n, true);
       }
	     else 
	     {
	        System.out.println(")");		
	     }
    }
 
    void printQuote(Node t, int n, boolean p)
    {
    	 print(t, n, p);
    }
    
    @Override
	  public void interprinter() 
    {
    }
}
