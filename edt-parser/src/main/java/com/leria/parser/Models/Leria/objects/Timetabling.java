package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Timetabling {
  // Attributes
  private String name;
  private int nrWeeks;
  private int nrDaysPerWeek;
  private int nrSlotsPerDay;

  // Elements
  private Calendar calendar;
  private List<Room> rooms;
  private List<Equipment> equipments;
  private List<Teacher> teachers;
  private List<Course> courses;
  private List<Student> students;

  public Timetabling(String name, int nrWeeks, int nrDaysPerWeek, int nrSlotsPerDay) {
    if (nrWeeks < 0)
      throw new IllegalArgumentException("nrWeeks must be non-negative");
    if (nrDaysPerWeek < 0)
      throw new IllegalArgumentException("nrDaysPerWeek must be non-negative");
    if (nrSlotsPerDay < 0)
      throw new IllegalArgumentException("nrSlotsPerDay must be non-negative");
    this.name = name;
    this.nrWeeks = nrWeeks;
    this.nrDaysPerWeek = nrDaysPerWeek;
    this.nrSlotsPerDay = nrSlotsPerDay;

    this.rooms = new ArrayList<>();
    this.equipments = new ArrayList<>();
    this.teachers = new ArrayList<>();
    this.courses = new ArrayList<>();
    this.students = new ArrayList<>();
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNrDaysPerWeek(int nrDaysPerWeek) {
    this.nrDaysPerWeek = nrDaysPerWeek;
  }

  public void setNrSlotsPerDay(int nrSlotsPerDay) {
    this.nrSlotsPerDay = nrSlotsPerDay;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public void addRooms(List<Room> rooms) {
    this.rooms.addAll(rooms);
  }

  public void setEquipments(List<Equipment> equipments) {
    this.equipments = equipments;
  }

  public void setTeachers(List<Teacher> teachers) {
    this.teachers = teachers;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public void addCourses(List<Course> courses) {
    this.courses.addAll(courses);
  }

  public void setStudent(List<Student> students) {
    this.students = students;
  }

  public String getName() {
    return name;
  }

  public int getNrWeeks() {
    return nrWeeks;
  }

  public int getNrDaysPerWeek() {
    return nrDaysPerWeek;
  }

  public int getNrSlotsPerDay() {
    return nrSlotsPerDay;
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public List<Room> getRooms() {
    return rooms;
  }

  public List<Equipment> getEquipments() {
    return equipments;
  }

  public List<Teacher> getTeachers() {
    return teachers;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public List<Student> getStudent() {
    return students;
  }

  public String toString() {
    return "Timetabling [name=" + name + ", nrWeeks=" + nrWeeks + ", nrDaysPerWeek=" + nrDaysPerWeek
        + ", nrSlotsPerDay=" + nrSlotsPerDay + ", calendar=" + calendar + ", rooms=" + rooms + ", equipments="
        + equipments + ", teachers=" + teachers + ", courses=" + courses + ", students=" + students + "]";
  }

  public void exportXML(String fileName) {
    try (FileWriter file = new FileWriter(fileName)) {
      file.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      file.write(
          "<timetabling xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"schema.xsd\" name=\""
              + name + "\" nrWeeks=\"" + nrWeeks + "\" nrDaysPerWeek=\"" + nrDaysPerWeek
              + "\" nrSlotsPerDay=\"" + nrSlotsPerDay + "\">\n");
      if (calendar != null)
        calendar.exportXML(file);
      if (rooms != null && !rooms.isEmpty()) {
        file.write("<rooms>\n");
        for (Room room : rooms) {
          room.exportXML(file);
        }
        file.write("</rooms>\n");
      }
      if (equipments != null && !equipments.isEmpty()) {
        file.write("<equipments>\n");
        for (Equipment equipment : equipments) {
          equipment.exportXML(file);
        }
        file.write("</equipments>\n");
      }
      if (teachers != null && !teachers.isEmpty()) {
        file.write("<teachers>\n");
        for (Teacher teacher : teachers) {
          teacher.exportXML(file);
        }
        file.write("</teachers>\n");
      }
      if (courses != null && !courses.isEmpty()) {
        file.write("<courses>\n");
        for (Course course : courses) {
          course.exportXML(file);
        }
        file.write("</courses>\n");
      }
      if (students != null && !students.isEmpty()) {
        file.write("<students>\n");
        for (Student student : students) {
          student.exportXML(file);
        }
        file.write("</students>\n");
      }
      file.write("</timetabling>\n");
    } catch (Exception e) {
      System.out.println("Error while exporting XML");
      e.printStackTrace();
    }
  }
}
