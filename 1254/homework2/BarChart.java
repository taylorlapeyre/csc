// Taylor Lapeyre & Alfonzo Bausa

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class BarChart extends JPanel {
  static int average;
  static int[] numbers;

  public BarChart() throws FileNotFoundException {
    readInputData("BarChart.data");

    JFrame frame = new JFrame("Bar Chart"); // make a new JFrame

    frame.add(this); // add itself to the JFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800,600);
    frame.setLocationRelativeTo(null); // center on screen
    frame.setVisible(true);
  }

  public void paint(Graphics g) {
    int averageLine = (600*(max(numbers)-average)) / (max(numbers)-min(numbers));
    int width = 800 / numbers.length;
		double height;

    super.paint(g);
    g.drawLine(0, averageLine, 800, averageLine); // draw the average line

    for (int i = 0; i < numbers.length; i++) {
      height = (600 * (max(numbers) - numbers[i])) / (max(numbers) - min(numbers));
      
      if (numbers[i] > average) {         // bars above average
				g.setColor(Color.orange);
        g.fill3DRect(width * i, (int)height, width, averageLine-(int)height, true);
      } else if (height == averageLine) { // bars equal to average
        g.setColor(Color.blue);
        g.fill3DRect(width * i, averageLine, width, 0, true);
				
      } else {                            // bars below average
        g.setColor(Color.green);
        g.fill3DRect(width * i, averageLine, width, (int)height-averageLine, true);
      }
    }
  }

  public static int max(int[] values) { // find the maximum value in an array
    int max = Integer.MIN_VALUE;
    for (int value : values) if (value > max) max = value;
    return max;
  }

  public static int min(int[] values) { // find the minimum value in an array
    int min = Integer.MAX_VALUE;
    for (int value : values) if (value < min) min = value;
    return min;
  }

  public static void readInputData(String fileName) throws FileNotFoundException {
    try {
      Scanner file = new Scanner(new FileReader(fileName));
      numbers = new int[file.nextInt()];
      for (int i = 0; file.hasNextInt(); i++) // assign integers in file to numbers[]
        numbers[i] = file.nextInt();

      average = 0;
      for (int i = 0; i < numbers.length; i++)
        average = average + numbers[i];
      average = average / numbers.length;
    } catch (FileNotFoundException e) { System.out.println("File not found!"); }
  }

  public static void main(String[] args) throws FileNotFoundException {
    BarChart b = new BarChart();
  }
}