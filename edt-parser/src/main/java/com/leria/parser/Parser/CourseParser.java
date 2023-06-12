package com.leria.parser.Parser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.leria.parser.Api.API;
import com.leria.parser.Config.ConfigurationFile;
import com.leria.parser.Models.Leria.objects.AllowedRooms;
import com.leria.parser.Models.Leria.objects.AllowedSlots;
import com.leria.parser.Models.Leria.objects.AllowedTeacher;
import com.leria.parser.Models.Leria.objects.AllowedTeachers;
import com.leria.parser.Models.Leria.objects.Classes;
import com.leria.parser.Models.Leria.objects.Class;
import com.leria.parser.Models.Leria.objects.Course;
import com.leria.parser.Models.Leria.objects.Part;
import com.leria.parser.Models.Leria.objects.Room;
import com.leria.parser.Models.Leria.types.IntegerRange;
import com.leria.parser.Models.Leria.types.IntegerRangeMin;
import com.leria.parser.Models.Leria.types.UniqueId;
import com.leria.parser.Models.UA.BaseEtape;
import com.leria.parser.Models.UA.Etape;
import com.leria.parser.Models.UA.EtapeCalendar;
import com.leria.parser.Models.UA.ServiceSheet;
import com.leria.parser.Models.UA.UACalendar;
import com.leria.parser.Models.UA.UACourse;

public class CourseParser {

  public static final String ALLOWED_SLOTS80 = "480,570,660,750,840,930,1020,1110";
  public static final String ALLOWED_SLOTS60 = "480,540,600,660,720,780,840,900,960,1020,1080";

  public static final int MAX_HEAD_COUNT_CM = 200;
  public static final int MAX_HEAD_COUNT_TD = 40;
  public static final int MAX_HEAD_COUNT_TP = 20;

  private CourseParser() {
  }

  public static List<Course> parseEtapes(ConfigurationFile config, UACalendar calendar, List<Room> rooms)
      throws Exception {
    List<Course> etapes = new ArrayList<>();
    System.out.println("Parsing etapes");
    try { // Send request to API
      HttpResponse<String> response = API.requestEtapes(config.getYear());
      Gson gson = new Gson();
      BaseEtape[] baseEtapes = gson.fromJson(response.body(), BaseEtape[].class);
      // Parse each etape and add it to the timetable
      for (int i = 0; i < baseEtapes.length; i++) {
        BaseEtape baseEtape = baseEtapes[i];
        System.out.print("Parsing etape " + (i + 1) + " / " + baseEtapes.length + "\r");
        if (config.getSelectEtape(baseEtape.getCodeEtape()) != null) {
          Etape etape = parseEtape(baseEtape, config.getYear());
          etapes.addAll(etapeToCourses(etape, config, calendar, rooms));
        } else {
          System.out.println("Etape " + baseEtape.getCodeEtape() + " was ignored because it was not selected");
        }
      }
      System.out.println("Parsed " + baseEtapes.length + " etapes            \n");
      return etapes;
    } catch (NullPointerException e) {
      throw new Exception("The Etape API returned a null response, check that the server is up and running");
    }
  }

  private static Etape parseEtape(BaseEtape baseEtape, String year) {
    HttpResponse<String> response = API.requestMaquette(baseEtape.getCodeEtape(), year);
    Gson gson = new Gson();
    UACourse[] courses = gson.fromJson(response.body(), UACourse[].class);
    return new Etape(baseEtape, courses);
  }

  private static List<Course> etapeToCourses(Etape etape, ConfigurationFile config, UACalendar calendar,
      List<Room> rooms) {
    EtapeCalendar etapeCalendar = calendar.getEtapeCalendar(etape.getCodeEtape());
    if (etapeCalendar == null) {
      System.out.println("<WARNING> No calendar was provided in the configuration for " + etape.getLibEtape() + " ("
          + etape.getCodeEtape() + ")" + ". The weeks for its parts will use default values : 1-"
          + calendar.getNrWeeks());
    }
    List<Course> courses = new ArrayList<>();
    for (UACourse course : etape.getCourses()) {
      if (!UniqueId.exists(course.getNoElement())) {
        if (config.getSelectEtape(course.getCodeEtape()).isPeriodeSelected(course.getCodePeriode()))
          courses.add(uaCourseToCourse(config, course, calendar, etapeCalendar, rooms));
        else {
          System.out.println("Course " + course.getNoElement() + " was ignored because its period was not selected");
        }
      }
    }
    return courses;
  }

  private static Course uaCourseToCourse(ConfigurationFile config, UACourse uaCourse, UACalendar calendar,
      EtapeCalendar etapeCalendar,
      List<Room> rooms) {
    // Create course
    Course course = new Course(uaCourse.getNoElement(),
        LabelFormatter.format(uaCourse.getLibElement()) + ", " + LabelFormatter.format(uaCourse.getLibEtape()) + ", "
            + (uaCourse.getLibStructureEtape() != null ? LabelFormatter.format(uaCourse.getLibStructureEtape()) : "")
            + (uaCourse.getCodeCNU() != null ? ", " + LabelFormatter.format("CNU" + uaCourse.getCodeCNU()) : "")
            + (uaCourse.getLibPeriode() != null ? ", " + LabelFormatter.format(uaCourse.getLibPeriode()) : ""));

    // Create allowed slots
    int startingWeek = 1;
    int endingWeek = calendar.getNrWeeks();
    if (etapeCalendar != null) {
      startingWeek = calendar.getStartingWeek() - etapeCalendar.getStartingWeek() + 1;
      endingWeek = etapeCalendar.getEndingWeek() - calendar.getStartingWeek();
    }

    // Get student count
    int studentCount = config.getSelectEtape(uaCourse.getCodeEtape()).getEffectif();

    // Create CM part
    if (uaCourse.getNbHeureCM() > 0) {
      course.addPart(createCM(config, uaCourse, startingWeek, endingWeek, rooms, studentCount));
    }

    // Create TD part
    if (uaCourse.getNbHeureTD() > 0) {
      course.addPart(createTD(config, uaCourse, startingWeek, endingWeek, rooms, studentCount));
    }

    // Create TP part
    if (uaCourse.getNbHeureTP() > 0) {
      course.addPart(createTP(config, uaCourse, startingWeek, endingWeek, rooms, studentCount));
    }

    // Create CMTD part
    if (uaCourse.getNbHeureCMTD() > 0) {
      course.addPart(createCMTD(config, uaCourse, startingWeek, endingWeek, rooms, studentCount));
    }
    return course;
  }

  private static Part createCM(ConfigurationFile config, UACourse uaCourse, int startingWeek, int endingWeek,
      List<Room> rooms, int studentCount) {
    Part part = new Part(uaCourse.getNoElement() + "-CM",
        (int) Math.ceil((uaCourse.getNbHeureCM() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", CM");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_CM);
    int nrClasses = (int) Math.ceil(studentCount / (float) MAX_HEAD_COUNT_CM);
    for (int i = 1; i <= nrClasses; i++) {
      classes.addClass(new Class(part.getId() + "-" + i, part.getLabel() + "-" + i));
    }
    part.setClasses(classes);

    // Create allowed slots
    part.setAllowedSlots(
        new AllowedSlots(uaCourse.getSessionLength() == UACourse.SESSION_LEGNTH80 ? ALLOWED_SLOTS80 : ALLOWED_SLOTS60,
            "1-5", startingWeek + "-" + endingWeek, 80));

    // Create allowed rooms
    List<UniqueId> roomsIds = new ArrayList<>();
    for (Room room : rooms) {
      if (room.getType().equals("Amphi"))
        roomsIds.add(room.getId());
    }
    part.setAllowedRooms(new AllowedRooms(roomsIds, new IntegerRangeMin("1-")));

    List<ServiceSheet> serviceSheets = ServiceSheetParser.parseServiceSheet(config.getYear(), uaCourse.getNoElement(),
        "CM");
    List<AllowedTeacher> allowedTeacherList = new ArrayList<>();
    for (ServiceSheet serviceSheet : serviceSheets) {
      float tempNrSessions = serviceSheet.getHours() / uaCourse.getSessionLength();
      if (Math.floor(tempNrSessions) != tempNrSessions) {
        System.out.println("<WARNING> The number of sessions for " + serviceSheet.getTeacher_uuid() + " on "
            + uaCourse.getNoElement() + " is not an integer. The number of sessions will be rounded up");
      }
      int nrSessions = (int) Math.ceil(tempNrSessions);
      allowedTeacherList
          .add(new AllowedTeacher(serviceSheet.getTeacher_uuid(), new IntegerRange(Integer.toString(nrSessions))));
    }
    part.setAllowedTeachers(new AllowedTeachers(allowedTeacherList, new IntegerRange("1")));
    return part;
  }

  private static Part createTD(ConfigurationFile config, UACourse uaCourse, int startingWeek, int endingWeek,
      List<Room> rooms, int studentCount) {
    Part part = new Part(uaCourse.getNoElement() + "-TD",
        (int) Math.ceil((uaCourse.getNbHeureTD() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", TD");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_TD);
    int nrClasses = (int) Math.ceil(studentCount / (float) MAX_HEAD_COUNT_TD);
    for (int i = 1; i <= nrClasses; i++) {
      classes.addClass(new Class(part.getId() + "-" + i, part.getLabel() + "-" + i));
    }
    part.setClasses(classes);

    // Create allowed slots
    part.setAllowedSlots(new AllowedSlots(uaCourse.getSessionLength() == UACourse.SESSION_LEGNTH80 ? ALLOWED_SLOTS80
        : ALLOWED_SLOTS60, "1-5", startingWeek + "-" + endingWeek, 60));

    // Create allowed rooms
    List<UniqueId> roomsIds = new ArrayList<>();
    for (Room room : rooms) {
      if (room.getType().equals("Salle de TD"))
        roomsIds.add(room.getId());
    }
    part.setAllowedRooms(new AllowedRooms(roomsIds, new IntegerRangeMin("1-")));

    // Create allowed teachers
    List<ServiceSheet> serviceSheets = ServiceSheetParser.parseServiceSheet(config.getYear(), uaCourse.getNoElement(),
        "TD");
    List<AllowedTeacher> allowedTeacherList = new ArrayList<>();
    for (ServiceSheet serviceSheet : serviceSheets) {
      float tempNrSessions = serviceSheet.getHours() / uaCourse.getSessionLength();
      if (tempNrSessions % 1 != 0) {
        System.out.println("<WARNING> The number of sessions for " + serviceSheet.getTeacher_uuid() + " on "
            + uaCourse.getNoElement() + " is not an integer. The number of sessions will be rounded up");
      }
      int nrSessions = (int) Math.ceil(tempNrSessions);
      allowedTeacherList
          .add(new AllowedTeacher(serviceSheet.getTeacher_uuid(), new IntegerRange(Integer.toString(nrSessions))));
    }
    part.setAllowedTeachers(new AllowedTeachers(allowedTeacherList, new IntegerRange("1")));

    return part;
  }

  private static Part createTP(ConfigurationFile config, UACourse uaCourse, int startingWeek, int endingWeek,
      List<Room> rooms, int studentCount) {
    Part part = new Part(uaCourse.getNoElement() + "-TP",
        (int) Math.ceil((uaCourse.getNbHeureTP() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", TP");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_TP);
    int nrClasses = (int) Math.ceil(studentCount / (float) MAX_HEAD_COUNT_TP);
    for (int i = 1; i <= nrClasses; i++) {
      classes.addClass(new Class(part.getId() + "-" + i, part.getLabel() + "-" + i));
    }
    part.setClasses(classes);

    // Create allowed slots
    part.setAllowedSlots(new AllowedSlots(uaCourse.getSessionLength() == UACourse.SESSION_LEGNTH80 ? ALLOWED_SLOTS80
        : ALLOWED_SLOTS60, "1-5", startingWeek + "-" + endingWeek, 80));

    // Create allowed rooms
    List<UniqueId> roomsIds = new ArrayList<>();
    for (Room room : rooms) {
      if (room.getType().equals("Salle de TP"))
        roomsIds.add(room.getId());
    }
    part.setAllowedRooms(new AllowedRooms(roomsIds, new IntegerRangeMin("1-")));

    // Create allowed teachers
    List<ServiceSheet> serviceSheets = ServiceSheetParser.parseServiceSheet(config.getYear(), uaCourse.getNoElement(),
        "TP");
    List<AllowedTeacher> allowedTeacherList = new ArrayList<>();
    for (ServiceSheet serviceSheet : serviceSheets) {
      float tempNrSessions = serviceSheet.getHours() / uaCourse.getSessionLength();
      if (tempNrSessions % 1 != 0) {
        System.out.println("<WARNING> The number of sessions for " + serviceSheet.getTeacher_uuid() + " on "
            + uaCourse.getNoElement() + " is not an integer. The number of sessions will be rounded up");
      }
      int nrSessions = (int) Math.ceil(tempNrSessions);
      allowedTeacherList
          .add(new AllowedTeacher(serviceSheet.getTeacher_uuid(), new IntegerRange(Integer.toString(nrSessions))));
    }
    part.setAllowedTeachers(new AllowedTeachers(allowedTeacherList, new IntegerRange("1")));

    return part;
  }

  private static Part createCMTD(ConfigurationFile config, UACourse uaCourse, int startingWeek, int endingWeek,
      List<Room> rooms, int studentCount) {
    Part part = new Part(uaCourse.getNoElement() + "-CMTD",
        (int) Math.ceil((uaCourse.getNbHeureCMTD() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", CMTD");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_TD);
    int nrClasses = (int) Math.ceil(studentCount / (float) MAX_HEAD_COUNT_TD);
    for (int i = 1; i <= nrClasses; i++) {
      classes.addClass(new Class(part.getId() + "-" + i, part.getLabel() + "-" + i));
    }
    part.setClasses(classes);

    // Create allowed slots
    part.setAllowedSlots(new AllowedSlots(uaCourse.getSessionLength() == UACourse.SESSION_LEGNTH80 ? ALLOWED_SLOTS80
        : ALLOWED_SLOTS60, "1-5", startingWeek + "-" + endingWeek, 80));

    // Create allowed rooms
    List<UniqueId> roomsIds = new ArrayList<>();
    for (Room room : rooms) {
      if (room.getType().equals("Salle de TD"))
        roomsIds.add(room.getId());
    }
    part.setAllowedRooms(new AllowedRooms(roomsIds, new IntegerRangeMin("1-")));

    // Create allowed teachers
    List<ServiceSheet> serviceSheets = ServiceSheetParser.parseServiceSheet(config.getYear(), uaCourse.getNoElement(),
        "CMTD");
    List<AllowedTeacher> allowedTeacherList = new ArrayList<>();
    for (ServiceSheet serviceSheet : serviceSheets) {
      float tempNrSessions = serviceSheet.getHours() / uaCourse.getSessionLength();
      if (tempNrSessions % 1 != 0) {
        System.out.println("<WARNING> The number of sessions for " + serviceSheet.getTeacher_uuid() + " on "
            + uaCourse.getNoElement() + " is not an integer. The number of sessions will be rounded up");
      }
      int nrSessions = (int) Math.ceil(tempNrSessions);
      allowedTeacherList
          .add(new AllowedTeacher(serviceSheet.getTeacher_uuid(), new IntegerRange(Integer.toString(nrSessions))));
    }
    part.setAllowedTeachers(new AllowedTeachers(allowedTeacherList, new IntegerRange("1")));

    return part;
  }

}
