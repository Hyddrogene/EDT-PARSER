package com.leria.parser.Models.UA;

public class Salle {
  private int annee;
  private String noSalle;
  private String codeSalle;
  private String nomSalle;
  private String departement;
  private String site;
  private String typeSalle;
  private int capacite;

  public int getAnnee() {
    return annee;
  }

  public void setAnnee(int annee) {
    this.annee = annee;
  }

  public String getNoSalle() {
    return "R" + noSalle;
  }

  public void setNoSalle(String noSalle) {
    this.noSalle = noSalle;
  }

  public String getCodeSalle() {
    return codeSalle;
  }

  public void setCodeSalle(String codeSalle) {
    this.codeSalle = codeSalle;
  }

  public String getNomSalle() {
    return nomSalle;
  }

  public void setNomSalle(String nomSalle) {
    this.nomSalle = nomSalle;
  }

  public String getDepartement() {
    return departement;
  }

  public void setDepartement(String departement) {
    this.departement = departement;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getTypeSalle() {
    return typeSalle;
  }

  public void setTypeSalle(String typeSalle) {
    this.typeSalle = typeSalle;
  }

  public int getCapacite() {
    return capacite;
  }

  public void setCapacite(int capacite) {
    this.capacite = capacite;
  }
}
