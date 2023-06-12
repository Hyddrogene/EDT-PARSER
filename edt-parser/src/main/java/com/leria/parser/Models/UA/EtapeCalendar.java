package com.leria.parser.Models.UA;

public class EtapeCalendar {
  private String etape;
  private int startingWeek;
  private int endingWeek;

  public EtapeCalendar(String etape, int startingWeek, int endingWeek) {
    this.etape = etape;
    this.startingWeek = startingWeek;
    this.endingWeek = endingWeek;
  }

  public String getEtape() {
    return etape;
  }

  public void setEtape(String etape) {
    this.etape = etape;
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

  public boolean compare(String id) {
    return this.etape.equals(id);
  }
}
