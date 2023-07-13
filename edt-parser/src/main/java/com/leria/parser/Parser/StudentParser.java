package com.leria.parser.Parser;

import java.util.List;

import com.leria.parser.Models.Leria.objects.Group;
import com.leria.parser.Models.Leria.objects.Student;
import com.leria.parser.Models.Leria.types.UniqueId;

// this class only contains the method to retrieve every course the student has to take
// the creation of a student is made directly in the CourseParser
public class StudentParser {
  private StudentParser() {
  }
  
  // Use classes id from the groups to find the courses the student has to take
  // (could create issues if the id changed)
  public static List<Student> parseStudent(ResultCourseParser result) {

	 for (Student student : result.getStudents()) {
      for (Group group : result.getSolution().getGroups()) {
        if (group.getStudents().contains(student.getId().getId())) {
          for (String c : group.getClasses()) {
            student.addCourseRefId(UniqueId.find(c.substring(0, c.indexOf("-"))));
          }
        }
      }
      System.out.println(student.getId().getId());
      student.getCoursesRefIds().stream().forEach(System.out::println);
    }
    
    return result.getStudents();
  }

}
