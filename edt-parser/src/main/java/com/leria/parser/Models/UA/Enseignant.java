package com.leria.parser.Models.UA;

public class Enseignant {
  private String uuidEnseignant;
  private String nomEnseignant;
  private String prenomEnseignant;
  private String affecationEnseignant;
  private String codeServiceAffectation;
  private String libelleServiceAffectation;
  private TypeEnseignant typeEnseignant;
  private float volumeHoraire;
  private String volumeHoraireAnnee;

  public Enseignant(String uuidEnseignant, String nomEnseignant, String prenomEnseignant, String affecationEnseignant,
      String libelleServiceAffectation,
      TypeEnseignant typeEnseignant,
      float volumeHoraire,
      String volumeHoraireAnnee) {
    super();
    this.uuidEnseignant = uuidEnseignant;
    this.nomEnseignant = nomEnseignant;
    this.prenomEnseignant = prenomEnseignant;
    this.affecationEnseignant = affecationEnseignant;
    this.libelleServiceAffectation = libelleServiceAffectation;
    this.typeEnseignant = typeEnseignant;
    this.volumeHoraire = volumeHoraire;
    this.volumeHoraireAnnee = volumeHoraireAnnee;
  }

  public String getId() {
    return "T" + uuidEnseignant;
  }

  public String getuuidEnseignant() {
    return uuidEnseignant;
  }

  public void setuuidEnseignant(String uuidEnseignant) {
    this.uuidEnseignant = uuidEnseignant;
  }

  public String getnomEnseignant() {
    return nomEnseignant;
  }

  public void setnomEnseignant(String nomEnseignant) {
    this.nomEnseignant = nomEnseignant;
  }

  public String getPrenomEnseignant() {
    return prenomEnseignant;
  }

  public void setPrenomEnseignant(String prenomEnseignant) {
    this.prenomEnseignant = prenomEnseignant;
  }

  public String getAffectation() {
    return affecationEnseignant;
  }

  public void setAffectation(String affecationEnseignant) {
    this.affecationEnseignant = affecationEnseignant;
  }

  public String getCodeServiceAffectation() {
    return codeServiceAffectation;
  }

  public void setCodeServiceAffectation(String codeServiceAffectation) {
    this.codeServiceAffectation = codeServiceAffectation;
  }

  public String getLibelleServiceAffectation() {
    return libelleServiceAffectation;
  }

  public void setLibelleServiceAffectation(String libelleServiceAffectation) {
    this.libelleServiceAffectation = libelleServiceAffectation;
  }

  public TypeEnseignant getTypeEnseignant() {
    return typeEnseignant;
  }

  public void setTypeEnseignant(TypeEnseignant typeEnseignant) {
    this.typeEnseignant = typeEnseignant;
  }

  public float getVolumeHoraire() {
    return volumeHoraire;
  }

  public void setVolumeHoraire(float volumeHoraire) {
    this.volumeHoraire = volumeHoraire;
  }

  public String getVolumeHoraireAnnee() {
    return volumeHoraireAnnee;
  }

  public void setVolumeHoraireAnnee(String volumeHoraireAnnee) {
    this.volumeHoraireAnnee = volumeHoraireAnnee;
  }
}
