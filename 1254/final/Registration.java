import java.util.*;

public class Registration {
  Student studentRef;
  Course courseRef;
    //char grade;
  public Registration (Student student, Course course) {
    studentRef = student; courseRef = course;
  }
  public String toString() {
    return (studentRef.toSting() + " " + courseRef.toString());
  }
}