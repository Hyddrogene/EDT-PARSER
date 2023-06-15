package com.leria.parser.Parser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.leria.parser.Api.API;
import com.leria.parser.Config.ConfigurationFile;
import com.leria.parser.Config.SelectEtape;
import com.leria.parser.Models.Leria.objects.AllowedRooms;
import com.leria.parser.Models.Leria.objects.AllowedSlots;
import com.leria.parser.Models.Leria.objects.AllowedTeacher;
import com.leria.parser.Models.Leria.objects.AllowedTeachers;
import com.leria.parser.Models.Leria.objects.Classes;
import com.leria.parser.Models.Leria.objects.Class;
import com.leria.parser.Models.Leria.objects.Course;
import com.leria.parser.Models.Leria.objects.Group;
import com.leria.parser.Models.Leria.objects.Part;
import com.leria.parser.Models.Leria.objects.Room;
import com.leria.parser.Models.Leria.objects.Solution;
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

  public static ResultCourseParser parseCourses(ConfigurationFile config, UACalendar calendar, List<Room> rooms)
      throws Exception {
    List<Course> etapes = new ArrayList<>();
    Solution solution = new Solution(new ArrayList<>());

    System.out.println("Parsing etapes");
    BaseEtape[] baseEtapes = null;
    try { // Send request to API
      HttpResponse<String> response = API.requestEtapes(config.getYear());
      Gson gson = new Gson();
      baseEtapes = gson.fromJson(response.body(), BaseEtape[].class);
    } catch (NullPointerException e) {
      throw new Exception("The Etape API returned a null response, check that the server is up and running");
    }
    try {
      // Parse each etape to get their courses and add them to the list of Courses
      for (int i = 0; i < baseEtapes.length; i++) {
        BaseEtape baseEtape = baseEtapes[i];
        SelectEtape selectEtape = config.getSelectEtape(baseEtape.getCodeEtape());
        if (selectEtape != null) {
          Etape etape = new Etape(baseEtape, parseEtapeCourses(baseEtape.getCodeEtape(), config.getYear()),
              GroupCalculations.calculRepartitions(selectEtape.getEffectif()));

          // Create the groups (from groups TP)
          etape.getRepartition().getGroupsTP().forEach(group -> solution
              .addGroup(new Group(etape.getCodeEtape() + "-G" + group.getN(), new ArrayList<>(), new ArrayList<>())));

          etapes.addAll(etapeToCourses(etape, config, calendar, solution, rooms));
        } else {
          System.out
              .println("<WARNING> The Etape " + baseEtape.getLibEtape() + " (" + baseEtape.getCodeEtape()
                  + ") was ignored because it was not selected");
        }
      }
      System.out.println("Parsed " + baseEtapes.length + " etapes            \n");
      return new ResultCourseParser(etapes, solution);
    } catch (Exception e) {
      throw new Exception("Error while parsing etapes : " + e.getMessage());
    }
  }

  private static UACourse[] parseEtapeCourses(String codeEtape, String year) {
    try {
      HttpResponse<String> response = API.requestMaquette(codeEtape, year);
      Gson gson = new Gson();
      return gson.fromJson(response.body(), UACourse[].class);
    } catch (Exception e) {
      System.out.println("Error while parsing etape " + codeEtape + " : " + e.getMessage());
      return new UACourse[0];
    }
  }

  private static List<Course> etapeToCourses(Etape etape, ConfigurationFile config, UACalendar calendar,
      Solution solution,
      List<Room> rooms) {
    // Get etape calendar
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
          courses.add(uaCourseToCourse(etape, config, course, calendar, etapeCalendar, solution, rooms));
        else {
          System.out.println(
              "<WARNING> The Course " + course.getLibElement() + " (" + course.getNoElement()
                  + ") was ignored because its period was not selected");
        }
      }
    }
    return courses;
  }

  private static Course uaCourseToCourse(Etape etape, ConfigurationFile config, UACourse uaCourse, UACalendar calendar,
      EtapeCalendar etapeCalendar, Solution solution,
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

    // Create CM part
    if (uaCourse.getNbHeureCM() > 0) {
      course.addPart(createCM(etape, config, uaCourse, solution, startingWeek, endingWeek, rooms));
    }

    // Create TD part
    if (uaCourse.getNbHeureTD() > 0) {
      course.addPart(createTD(etape, config, uaCourse, solution, startingWeek, endingWeek, rooms));
    }

    // Create TP part
    if (uaCourse.getNbHeureTP() > 0) {
      course.addPart(createTP(etape, config, uaCourse, solution, startingWeek, endingWeek, rooms));
    }

    // Create CMTD part
    if (uaCourse.getNbHeureCMTD() > 0) {
      course.addPart(createCMTD(etape, config, uaCourse, solution, startingWeek, endingWeek, rooms));
    }
    return course;
  }

  private static Part createCM(Etape etape, ConfigurationFile config, UACourse uaCourse, Solution solution,
      int startingWeek,
      int endingWeek,
      List<Room> rooms) {
    Part part = new Part(uaCourse.getNoElement() + "-CM",
        (int) Math.ceil((uaCourse.getNbHeureCM() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", CM");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_CM);
    for (int i = 1; i <= etape.getRepartition().getNrClassesCM(); i++) {
      Class c = new Class(part.getId() + "-" + i, part.getLabel() + "-" + i);
      classes.addClass(c);
      etape.getRepartition().getGroupsTPByParentCM(i)
          .forEach(g -> solution.getGroupById(etape.getCodeEtape() + "-G" + g.getN())
              .addClass(c.getId().getId()));
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

    // Create allowed teachers
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

  private static Part createTD(Etape etape, ConfigurationFile config, UACourse uaCourse, Solution solution,
      int startingWeek,
      int endingWeek,
      List<Room> rooms) {
    Part part = new Part(uaCourse.getNoElement() + "-TD",
        (int) Math.ceil((uaCourse.getNbHeureTD() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", TD");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_TD);
    for (int i = 1; i <= etape.getRepartition().getNrClassesTD(); i++) {
      String parent = uaCourse.getNoElement() + "-CM-" + etape.getRepartition().getGroupTD(i).getParent();
      Class c;
      if (UniqueId.exists(parent)) {
        c = new Class(part.getId() + "-" + i, parent, part.getLabel() + "-" + i);
      } else {
        c = new Class(part.getId() + "-" + i, part.getLabel() + "-" + i);
      }
      classes.addClass(c);
      // For each group (class TP) that has this TD as parent, add the class to the
      // group (in the solution)
      etape.getRepartition().getGroupsTPByParentTD(i)
          .forEach(g -> solution.getGroupById(etape.getCodeEtape() + "-G" + g.getN())
              .addClass(c.getId().getId()));
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

  private static Part createTP(Etape etape, ConfigurationFile config, UACourse uaCourse, Solution solution,
      int startingWeek,
      int endingWeek,
      List<Room> rooms) {
    Part part = new Part(uaCourse.getNoElement() + "-TP",
        (int) Math.ceil((uaCourse.getNbHeureTP() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", TP");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_TP);
    for (int i = 1; i <= etape.getRepartition().getNrClassesTP(); i++) {
      String parent = uaCourse.getNoElement() + "-TD-" + etape.getRepartition().getGroupTP(i).getParent();
      Class c;
      if (UniqueId.exists(parent)) {
        c = new Class(part.getId() + "-" + i, parent, part.getLabel() + "-" + i);
      } else {
        c = new Class(part.getId() + "-" + i, part.getLabel() + "-" + i);
      }
      classes.addClass(c);
      solution.getGroupById(etape.getCodeEtape() + "-G" + i)
          .addClass(c.getId().getId());
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

  private static Part createCMTD(Etape etape, ConfigurationFile config, UACourse uaCourse, Solution solution,
      int startingWeek,
      int endingWeek,
      List<Room> rooms) {
    Part part = new Part(uaCourse.getNoElement() + "-CMTD",
        (int) Math.ceil((uaCourse.getNbHeureCMTD() * 60) / uaCourse.getSessionLength()),
        LabelFormatter.format(uaCourse.getLibElement()) + ", CMTD");
    // Create classes
    Classes classes = new Classes(new ArrayList<Class>(), MAX_HEAD_COUNT_TD);
    for (int i = 1; i <= etape.getRepartition().getNrClassesTD(); i++) {
      String parent = uaCourse.getNoElement() + "-CM-" + etape.getRepartition().getGroupTD(i).getParent();
      Class c;
      if (UniqueId.exists(parent)) {
        c = new Class(part.getId() + "-" + i, parent, part.getLabel() + "-" + i);
      } else {
        c = new Class(part.getId() + "-" + i, part.getLabel() + "-" + i);
      }
      classes.addClass(c);
      etape.getRepartition().getGroupsTPByParentTD(i)
          .forEach(g -> solution.getGroupById(etape.getCodeEtape() + "-G" + g.getN())
              .addClass(c.getId().getId()));
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
      float tempNrSessions = serviceSheet.getHours() * 60 / uaCourse.getSessionLength();
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
