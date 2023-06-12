package com.leria.parser.Models.UA;

public class ServiceSheet {
  private String teacher_uuid;
  private String course_id;
  private String typeCourse;
  private float hours;

  public ServiceSheet(String teacher_uuid, String course_id, String typeCourse, float hours) {
    this.teacher_uuid = teacher_uuid;
    this.course_id = course_id;
    this.typeCourse = typeCourse;
    this.hours = hours;
  }

  public String getTeacher_uuid() {
    return teacher_uuid;
  }

  public void setTeacher_uuid(String teacher_uuid) {
    this.teacher_uuid = teacher_uuid;
  }

  public String getCourse_id() {
    return course_id;
  }

  public void setCourse_id(String course_id) {
    this.course_id = course_id;
  }

  public String getTypeCourse() {
    return typeCourse;
  }

  public void setTypeCourse(String typeCourse) {
    this.typeCourse = typeCourse;
  }

  public float getHours() {
    return hours;
  }

  public void setHours(float hours) {
    this.hours = hours;
  }

}
