// Taylor Lapeyre
// Student.java

import java.util.*;

public class Student {
  String name;
  static int numStudents = 0;
  static final int maxNumCoursesRegistered = 2;
  final int id;
  int numCourseRegistered = 0;
  Course[] regCourseRefs;
  public Student (String name) {
    this.name = name; this.id = numStudents++;
    regCourseRefs = new Course[maxNumCoursesRegistered];
  }
  public String toSting() { return("(" + name + ", id=" + id + ")"); }
}