package com.leria.parser.Api;

public enum DataType {
  MAQUETTE, ETAPES, SALLES, ENSEIGNANTS, ETAPE_CALENDAR, SERVICE_ENSEIGNANT, STUDENT_COUNT;

  public String toString() {
    switch (this) {
      case MAQUETTE:
        return "maquette";
      case ETAPES:
        return "etape";
      case SALLES:
        return "salle";
      case ENSEIGNANTS:
        return "enseignant";
      case ETAPE_CALENDAR:
        return "etape_calendars";
      case SERVICE_ENSEIGNANT:
        return "teacher_service_sheet";
      case STUDENT_COUNT:
        return "student_counts";
      default:
        return "";
    }
  }
}
