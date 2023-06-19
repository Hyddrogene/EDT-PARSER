package com.leria.parser.Parser;

import java.util.List;

import com.leria.parser.Models.Leria.objects.Course;
import com.leria.parser.Models.Leria.objects.Solution;
import com.leria.parser.Models.Leria.objects.Student;

public class ResultCourseParser {
  private List<Course> courses;
  private List<Student> students;
  private Solution solution;

  public ResultCourseParser(List<Course> courses, List<Student> students, Solution solution) {
    this.courses = courses;
    this.students = students;
    this.solution = solution;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public List<Student> getStudents() {
    return students;
  }

  public Solution getSolution() {
    return solution;
  }
}
