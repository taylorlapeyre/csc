Bug bugs[];
int numBugs;

void setup() {
  size(640, 480);
  numBugs = 10;
  background(#94C552);
  bugs = new Bug[numBugs];
  for (int i = 0; i < numBugs; i++)
    bugs[i] = new Bug("frog.gif");
}

void draw() {
  background(#94C552);
  for (Bug bug : bugs) {
    if ((mouseX < bug.x + bug.image.width && mouseX > bug.x) && (mouseY < bug.y + bug.image.height && mouseY > bug.y) && mousePressed) {
      bug.squish();
      speedUpBugs();
    }
    bug.move();
    bug.draw();
  }
}

void speedUpBugs() {
  int speed = 2;
  for (Bug bug : bugs)
    if (bug.squished) speed++;
  for (Bug bug : bugs)
    bug.speed = speed;
}
