int x = 0;
int y = 0;
int w = 20;
int h = 20;
int drawWidth = 20;
int drawHeight = 20;
color currentColor;

int red, orange, yellow, green, lightBlue, blue, pink, brown, white, black;
int[] colors;

void setup()
{
  size(700, 400);
  background(255);

  red = color(255, 0, 0);
  orange = color(255, 130, 0);
  yellow = color(255, 255, 0);
  green = color(0, 255, 0);
  lightBlue = color(0, 170, 255);
  blue = color(0, 0, 255);
  pink = color(255, 0, 255);
  brown = color(99, 51, 17);
  white = color(255);
  black = color(0);
  currentColor = color(0);
  
  colors = new int[] { red, orange, yellow, green, lightBlue, blue, pink, brown, white, black };
}


void draw()
{ 
  // Draw the color pallete
  stroke(255);
  for (int i = 0; i < colors.length; i++)
  {
    fill(colors[i]);
    rect(0, i * 20, 20, 20);
  }
  
  if (mousePressed)
  {
    // Check to see if the mouse has clicked in any of the color squares
    // If so, set that color square's color as currentColor
    for (int i = 0; i < colors.length; i++)
      if ((mouseX < w) && (mouseY > i * 20) && (mouseY < i * 20 + h))
        currentColor = colors[i];
 
    // Paint with the currentColor
    noStroke();
    fill(currentColor);
    ellipse(mouseX, mouseY, drawWidth, drawHeight);
  }
}
