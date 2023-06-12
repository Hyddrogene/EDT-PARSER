package com.leria.parser.Models.UA;

import java.util.List;

public class UACalendar {
  private List<EtapeCalendar> etapes;
  private int startingWeek;
  private int endingWeek;

  public UACalendar(List<EtapeCalendar> etapes, int startingWeek, int endingWeek) {
    this.etapes = etapes;
    this.startingWeek = startingWeek;
    this.endingWeek = endingWeek;
  }

  public List<EtapeCalendar> getEtapes() {
    return etapes;
  }

  public EtapeCalendar getEtapeCalendar(String id) {
    for (EtapeCalendar etape : etapes) {
      if (etape.compare(id)) {
        return etape;
      }
    }
    return null;
  }

  public void setEtapes(List<EtapeCalendar> etapes) {
    this.etapes = etapes;
  }

  public void addEtape(EtapeCalendar etape) {
    this.etapes.add(etape);
  }

  public int getStartingWeek() {
    return startingWeek;
  }

  public void setStartingWeek(int startingWeek) {
    this.startingWeek = startingWeek;
  }

  public int getEndingWeek() {
    return endingWeek;
  }

  public void setEndingWeek(int endingWeek) {
    this.endingWeek = endingWeek;
  }

  public int getNrWeeks() {
    return endingWeek - startingWeek;
  }
}
