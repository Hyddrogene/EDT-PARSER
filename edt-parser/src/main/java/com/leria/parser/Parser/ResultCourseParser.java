package com.leria.parser.Parser;

import java.util.List;

import com.leria.parser.Models.Leria.objects.Course;
import com.leria.parser.Models.Leria.objects.Solution;

public class ResultCourseParser {
  private List<Course> courses;
  private Solution solution;

  public ResultCourseParser(List<Course> courses, Solution solution) {
    this.courses = courses;
    this.solution = solution;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public Solution getSolution() {
    return solution;
  }
}
