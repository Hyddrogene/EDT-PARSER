package com.leria.parser.Parser;

import com.leria.parser.Config.ConfigurationFile;
import com.leria.parser.Models.Leria.objects.Course;
import com.leria.parser.Models.Leria.objects.Room;
import com.leria.parser.Models.Leria.objects.Teacher;
import com.leria.parser.Models.Leria.objects.Timetabling;
import com.leria.parser.Models.UA.UACalendar;

import java.util.List;
import java.util.regex.Pattern;

// Main class for parsing the different elements and assembling them into a Timetabling object
public class Parser {

  private Parser() {
  }

  public static Timetabling parseTimetable(ConfigurationFile config) throws Exception {
    try {
      UACalendar calendar = CalendarParser.parseCalendar(config);
      Timetabling timetable = new Timetabling(config.getName(), calendar.getNrWeeks(), config.getNrDaysPerWeek(),
          config.getNrSlotsPerDay());
      // Check if year is valid
      Pattern pattern = Pattern.compile("^[12]\\d{3}$");
      if (!pattern.matcher(config.getYear()).matches()) {
        throw new IllegalArgumentException("Invalid year: " + config.getYear());
      }
      // Send request to API
      List<Teacher> teachers = TeacherParser.parseEnseignants(config.getYear());
      List<Room> rooms = RoomParser.parseSalles(config.getYear());
      ResultCourseParser resultCourseParser = CourseParser.parseCourses(config, calendar, rooms);

      timetable.setTeachers(teachers);
      timetable.setRooms(rooms);
      timetable.setCourses(resultCourseParser.getCourses());
      timetable.setStudents(StudentParser.parseStudent(resultCourseParser));
      timetable.setSolution(resultCourseParser.getSolution());

      String ruleConfigFile = "/home/etud/stage_2023/edt-parser/edt-parser/configuration_rule.xml";
      
      RuleGenerator ruleGenerator = new RuleGenerator(ruleConfigFile);
      
      return timetable;
    } catch (Exception e) {
      throw new Exception("Error while retrieving data from APIs. \n" + e.getMessage());
    }
  }
}
