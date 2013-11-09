# Processing notes


## Basic drawing and setup

### Setup

1. make a **window**:

  `size(x, y);`

2. **background color**:

  `background(r,g,b,alpha or #hex);`

3. **Debug**:

  `println(frameRate)`

### Drawing

3. drawing a **line**:

  `line(x1, y1, x2, y2);`

4. drawing an **ellipse**:

  `ellipse(x, y, width, height);`

5. drawing a **rectangle**:

  `rect(x, y, width height);` [(x, y) is upper left coordinate]

6. drawing an **arc** [like pacman]:

  `arc(x, y, width, height, start-angle, end-angle);`

  *example:* `arc(100, 100, 80, 180, radians(-90), radians(90));` [half of a circle]

### Coloring

7. **Coloring** [put these before the shape that you want to color]:

  `fill(r,g,b,alpha or #hex);`

  `nofill();`

8. **Outining** a shape [put these before the shape that you want to color]:

  `stroke(r,g,b,alpha or #hex);` [used for coloring lines]

  `strokeWeight(size);` [width of the outline]

  `stokeCap(ROUND);` [shape of the outline]

### Making Shapes

1. **Start** defining a shape:

  `beginShape();`

2. **Points** in a shape:

  `vertex(x, y);`

3. **End** a shape:

  `endShape(CLOSE);`

---

*Missed this class*

---


## Basic Rendering & Animation

### Images

1. **Add** images:

  drap and drop images into sketch

  `PImage [shape];`

  `circle = loadImage("circle.png");`

2. **Draw** images:

  `image(shape, x, y, width, height);`

3. **Center** images:

  `imageMode(CENTER);`

4. **Array** of images:

  `PImage myArray_S[];`

  `myArray = new PImage[numImages];`

  `myArray[imageNum] = sheet.get(x, y, width, height);`

5. **Pixelate** images:

  `noSmooth();`

6. Get a **sub-image** of another image:

  `link = sheet.get(x, y, width, height);`

### Text & Font Rendering

1. **Choose** fonts:

  Tools -> Create Fonts -> Choose Font

2. **Load** fonts:

  `PFont font;`

  `font = loadFont("Futura-Bold-48.vlw");`

3. **Display** text with fonts:

  `text("text");`

4. **Use** fonts:

  `textFont(font);`

  `textSize(64);`

5. **Align** text:

  `textAlign(CENTER);`

### Vectors & Shapes

1. **Load** vector:

  `PShape star;`

  `star = loadShape("star.svg");`

2. **Draw** vector:

  `shape(star, x, y, width, height);`

3. **Center** vectors:

  `shapeMode(CENTER);`

### Rotating

*Order Matters*

1. `translate(x, y);`

2. `rotate(radians(variable));` [e.g. "angle"]

3. `shape(shape, 0, 0);`

### Frames

1. Define the **frames**:

  `int frame = (frameCount / frameSpeed) % numFrames` [frameSpeed is probably ~10]

2. Change how often the frame is updated?

  `if (frameCount % numFrames == 0) { .. }` [every 6 frames]

  `frame = (frame + 1) % numFrames`

### Key Controls

1. Detect if a key is **pressed**:

  `keyPressed`

2. Detect if a **certain key** is pressed:

  `if (key == CODED) { ... }` [for arrow keys]

  `if (keyCode == UP/DOWN/LEFT/RIGHT) { ... }`

---


## Objects

  1. **Define** a class:

    `class ClassName { ... }`

    Objects have instance variables, constructors, methods, etc.

    Objects can be initialized and inherit from other objects.

  2. **Constructors**:

    `ClassName(instanceVairables, ...) { ... }`

  3. Defining **instance methods**:

    `void methodName() { ... }`