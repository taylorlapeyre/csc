import java.util.*;

public class Course {
  String name;
  final int id, creditHours;
  public Course (String name, int id) {
    this.name = name; this.id = id; this.creditHours = 3;
  }
  public Course (String name, int id, int creditHours) {
    this.name = name; this.id = id; this.creditHours = creditHours;
  }
  public String toString() {
    return ("(" + name + ", id=" + id + ")");
  }
}