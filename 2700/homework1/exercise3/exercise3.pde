// Setup
size(600, 300);
background(0);
strokeWeight(0);

// Pacman
fill(255, 251, 60);
arc(150, 150, 250, 250, radians(-140), radians(140));

// Ghost
fill(226, 41, 33);
rect(325, 150, 250, 120);
arc(450, 150, 250, 250, radians(-180), radians(0));

fill(255);
ellipse(380, 150, 60, 60);
fill(0, 0, 255);
ellipse(380, 150, 30, 30);

fill(255);
ellipse(520, 150, 60, 60);
fill(0, 0, 255);
ellipse(520, 150, 30, 30);
