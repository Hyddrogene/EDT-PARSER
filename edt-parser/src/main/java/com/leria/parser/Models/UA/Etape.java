package com.leria.parser.Models.UA;

import java.util.ArrayList;
import java.util.List;

public class Etape extends BaseEtape {
  private List<UACourse> courses;
  private Repartition repartition;

  public Etape(String codeEtape, String libelleEtape, List<UACourse> courses, Repartition repartition) {
    this.codeEtape = codeEtape;
    this.libelleEtape = libelleEtape;
    this.courses = courses;
    this.repartition = repartition;
  }

  public Etape(BaseEtape baseEtape, UACourse[] courses, Repartition repartition) {
    this.codeEtape = baseEtape.getCodeEtape();
    this.libelleEtape = baseEtape.getLibEtape();
    this.courses = new ArrayList<>();
    for (UACourse course : courses) {
      this.courses.add(course);
    }
    this.repartition = repartition;
  }

  public List<UACourse> getCourses() {
    return courses;
  }

  public void setCourses(List<UACourse> courses) {
    this.courses = courses;
  }

  public Repartition getRepartition() {
    return repartition;
  }

  public void setRepartition(Repartition repartition) {
    this.repartition = repartition;
  }
}
