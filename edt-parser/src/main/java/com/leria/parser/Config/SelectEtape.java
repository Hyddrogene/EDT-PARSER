package com.leria.parser.Config;

public class SelectEtape {
  private String id;
  private String label;
  private String periodes;
  private int effectif;

  public SelectEtape(String id, String label, String periodes, int effectif) {
    this.id = id;
    this.label = label;
    this.periodes = periodes;
    this.effectif = effectif;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void setPeriodes(String periodes) {
    this.periodes = periodes;
  }

  public String getLabel() {
    return label;
  }

  public String getPeriodes() {
    return periodes;
  }

  public int getEffectif() {
    return effectif;
  }

  public void setEffectif(int effectif) {
    this.effectif = effectif;
  }

  public boolean isPeriodeSelected(String periode) {
    if (this.periodes.equals("")) {
      return true;
    }
    String[] periodesArray = this.periodes.split(",");
    for (String p : periodesArray) {
      if (p.trim().equals(periode)) {
        return true;
      }
    }
    return false;
  }
}
