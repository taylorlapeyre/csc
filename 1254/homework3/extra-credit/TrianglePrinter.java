import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TrianglePrinter extends JPanel {

  public TrianglePrinter() {
    JFrame frame = new JFrame("Draw Triangles"); // make the JFrame..
    frame.add(this); // when a TrianglePrinter is created, add it to the JFrame

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,400);
    frame.setLocationRelativeTo(null); // center on screen
    frame.setVisible(true);
  }

  public void paint(Graphics g) {
    super.paint(g); // clean up..
    Random rand = new Random();
    g.setColor( // make the triangle a random color
        new Color(rand.nextInt(256),rand.nextInt(256), rand.nextInt(256)));

    int[] xCoordinates = {23, 95, 34}; // this is "triangle 3"
    int[] yCoordinates = {112, 44, 75}; 
    g.fillPolygon(xCoordinates, yCoordinates, 3);
    g.fillRect(5, 40, 20, 50);
  }

  public static void main(String[] args) {
      TrianglePrinter st = new TrianglePrinter(); // start the JPanel
  }
}