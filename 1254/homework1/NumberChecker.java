import java.util.*;

public class NumberChecker {

  public static int getNumber() {
    Scanner userInput = new Scanner(System.in);
    return (userInput.hasNextInt()) ? userInput.nextInt() : 0;
  }

  public static void main(String[] args) {
    int attempts = 4  ; // one less since we don't decrement for the first input
    boolean successful = false;
    System.out.print("Enter 2 positive integers. You have 5 attempts total : ");

    for (; attempts > 0; attempts--) {
      if (getNumber() > 0) { successful = true; break; }
      else System.out.print( (attempts == 1) ? "" : // nothing if exiting, else:
        "That's not a positive integer. Attempts remaining: "+attempts+" : ");
    }

    if (successful) {
      System.out.print("Good job! Enter a second positive integer : ");
      successful = false;
      attempts--; // when we broke out of the last loop, we did not decrement

      for (; attempts >= 0; attempts--) {
        if (getNumber() > 0) { successful = true; break; }
        else System.out.print(
          "That's not a positive integer. Attempts remaining: "+attempts+" : ");
      }
    } else System.out.println("Not enough attempts to continue");

    System.out.println( (successful) ? "Good Job!" : "No remaining attempts");
  }
}