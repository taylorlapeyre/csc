class Bug {
  PImage image;
  int x;
  int y;
  int speed = 2;
  boolean squished = false;

  Bug(String imageName) {
    this.image = loadImage(imageName);
    this.x = int(random(0, width));
    this.y = int(random(0, height));
  }

  void draw() {
    this.x = constrain(this.x, 0, width - this.image.width);
    this.y = constrain(this.y, 0, height - this.image.height);
    image(this.image, this.x, this.y);
  }

  void move() {
    if (!this.squished) {
      this.x = this.x + int(random(-speed, speed));
      this.y = this.y + int(random(-speed, speed));
    }
  }

  void squish() {
    this.image = loadImage("dead-frog.png");
    this.squished = true;
  }
}
