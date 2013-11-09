// Taylor Lapeyre
// StudentCourse.java

import java.util.*;

public class StudentCourse {
  Student[] students = { new Student("stud0"), new Student("stud1"), new Student("stud2") };
  Course[] courses = { new Course("cour0", 0), new Course("cour1", 1), new Course("cour2", 2) };
  LinkedList<Registration> registrations = new LinkedList<Registration>();

  public void PrintRegistrations() {
    ListIterator<Registration> it = registrations.listIterator();
    System.out.println("Registrations:");
    while (it.hasNext())
      System.out.println(it.next().toString());
  }

  public void AddRegistrations(int studentId, int courseId) {

    int i, numStudents = students.length;
    int j, numCourses  = courses.length;

    // Check if studentId is valid
    for (i = 0; i < numStudents; i++)
      if (students[i].id == studentId) break;
    if (i == numStudents) {
      System.out.println("*** student.id=" + studentId + " not found; registration failed");
      return;
    }

    // Check if courseId is valid
    for (j = 0; j < numCourses; j++)
      if (courses[j].id == courseId) break;
    if (j == numCourses) {
      System.out.println("*** course.id=" + courseId +
        " not found; registration failed");
      return;
    }



    // Check if the student is already registered
    ListIterator<Registration> it = registrations.listIterator();
    while (it.hasNext()) {
      Registration reg = it.next();
      if ((reg.studentRef.id == studentId) && (reg.studentRef.id == courseId)) {
        System.out.println("??? " + reg.studentRef +
          " is already registered in " + reg.courseRef);
        return;
      }
    }

    int k = students[i].numCourseRegistered;
    if (k < Student.maxNumCoursesRegistered) {
      System.out.println("+++ " + students[i] + " is now regisstered in "
        + courses[j]);
      students[i].regCourseRefs[students[i].numCourseRegistered++] = courses[j];
      if (k > 0) {
        System.out.print("your previous course-registrations:");
        for (int m = 0; m < k; m++)
          System.out.println(" " + Arrays.toString(students[i].regCourseRefs));
        System.out.println();
      }
      registrations.add(new Registration(students[i], courses[j]));
    } else {
      System.out.print("??? " + students[i] + "'s registration in " +
        courses[j] + " not permitted ");
      System.out.println("due to maxLimit " + Student.maxNumCoursesRegistered);
    }
  }

  public void DeleteRegistration(int studentId, int courseId) {
    ListIterator<Registration> it = registrations.listIterator();
    while (it.hasNext()) {
      Registration reg = it.next();
      if ((reg.studentRef.id == studentId) && (reg.courseRef.id == courseId)) {
        System.out.println(reg.studentRef + " is already registered in "
         + reg.courseRef);
        it.remove();
        int k = reg.studentRef.numCourseRegistered, i, j;
        for (i = 0; i < k; i++)
          if (reg.studentRef.id == courseId) {
            for ( j = i; j < k; j++)
              reg.studentRef.regCourseRefs[j] =
            reg.studentRef.regCourseRefs[j - 1];
            break;
          }
          reg.studentRef.numCourseRegistered = --k;
          System.out.println(reg.studentRef + " is now registered "
            + "(after deletion) in");
          for (i = 0; i < k; i++)
            System.out.print(" " +
              reg.studentRef.regCourseRefs[i].toString());
          System.out.println();
          return;
      }
    }
    System.out.println("??? studentId = " + studentId + " is not registered "
      + "in courseId = " + courseId + "; deletion failed");
  }

  public static void main(String[] args) {
    StudentCourse studentCourse = new StudentCourse();
    System.out.println("Students: " + Arrays.toString(studentCourse.students));
    System.out.println("Courses: " + Arrays.toString(studentCourse.courses));
        //System.out.println("Courses: " + studentCourse.courses);
    studentCourse.AddRegistrations(5, 1);
    studentCourse.AddRegistrations(1, 5);
    studentCourse.AddRegistrations(1, 0);
    studentCourse.AddRegistrations(1, 1);
    studentCourse.AddRegistrations(1, 2);
    studentCourse.AddRegistrations(2, 1);
    studentCourse.AddRegistrations(2, 2);
    studentCourse.AddRegistrations(1, 1);
    studentCourse.PrintRegistrations();
    studentCourse.DeleteRegistration(1, 2);
    studentCourse.DeleteRegistration(1, 1);
    studentCourse.DeleteRegistration(1, 1);
  }
}