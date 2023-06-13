package com.leria.parser.Models.UA;

public class EtapeCalendar {
  private String etape_id;
  private int startingWeek;
  private int endingWeek;

  public EtapeCalendar(String etape_id, int startingWeek, int endingWeek) {
    this.etape_id = etape_id;
    this.startingWeek = startingWeek;
    this.endingWeek = endingWeek;
  }

  public String getEtape() {
    return etape_id;
  }

  public void setEtape(String etape_id) {
    this.etape_id = etape_id;
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
    return this.etape_id.equals(id);
  }
}
