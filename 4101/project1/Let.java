import java.io.*;

class Let extends Special 
{
   public Let() 
   { 
   }

   void printElements(Cons t, int n, boolean isQuote) 
   {
      if (isQuote) 
      {
         System.out.print(" ");
    	 t.getCar().printQuote(n, false);
      } 
      else 
      {
	     for(int i=0; i<n; i++)
	     {	
	        System.out.print(" ");
         }
         t.getCar().print(n);
	     System.out.println();
      }
      if(t.getCdr()!=null)
      {
         printElements((Cons)t.getCdr(), n, isQuote);
      }
   }
 
   void print(Node t, int n, boolean p) 
   {
      if(!p)
      {	
   	     System.out.print("(");
   	  }
      System.out.println("let");
   	  if(t.getCdr()!=null)
   	  {
	     printElements((Cons)t.getCdr(), n+4, false);
      }
      for(int i=0; i<n; i++)
      {	
         System.out.print(" ");
      }
      System.out.print(")");
   }

   @Override
   void printQuote(Node t, int n, boolean p) 
   {
      if(!p)
	  {
	     System.out.print("(");
	  }
      System.out.print("let ");
	  if(t.getCdr()!=null)
	  {
	     printElements((Cons)t.getCdr(), 0, true);
	  }	
	  System.out.print(")");
   }
	
   @Override
   public void interprinter() 
   {
   }
}
