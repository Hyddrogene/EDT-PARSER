package com.leria.parser.Config;

import java.util.List;

public class ConfigurationFile {
  private String name;
  private String year;
  private String faculty;
  private int nrDaysPerWeek;
  private int nrSlotsPerDay;

  private List<SelectEtape> selectEtapes;

  public ConfigurationFile(String name, String year, String faculty, int nrDaysPerWeek,
      int nrSlotsPerDay, List<SelectEtape> selectEtapes) {
    this.name = name;
    this.year = year;
    this.faculty = faculty;
    this.nrDaysPerWeek = nrDaysPerWeek;
    this.nrSlotsPerDay = nrSlotsPerDay;
    this.selectEtapes = selectEtapes;
  }

  public String getName() {
    return name;
  }

  public String getYear() {
    return year;
  }

  public String getFaculty() {
    return faculty;
  }

  public int getNrDaysPerWeek() {
    return nrDaysPerWeek;
  }

  public int getNrSlotsPerDay() {
    return nrSlotsPerDay;
  }

  public List<SelectEtape> getSelectEtapes() {
    return selectEtapes;
  }

  public void addEtape(SelectEtape selectEtape) {
    this.selectEtapes.add(selectEtape);
  }

  public SelectEtape getSelectEtape(String id) {
    for (SelectEtape selectEtape : selectEtapes) {
      if (selectEtape.getId().equals(id)) {
        return selectEtape;
      }
    }
    return null;
  }

  public String toString() {
    return "ConfigurationFile{" + "name='" + name + '\'' + ", year='" + year + '\'' + ", faculty='"
        + faculty + '\'' + ", nrDaysPerWeek=" + nrDaysPerWeek + ", nrSlotsPerDay=" + nrSlotsPerDay
        + ", selectEtapes=" + selectEtapes + '}';
  }
}
