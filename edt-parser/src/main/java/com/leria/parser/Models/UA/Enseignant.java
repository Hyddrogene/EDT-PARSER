package com.leria.parser.Models.UA;

public class Enseignant {
  private String teacher_uuid;
  private String nom;
  private String prenom;
  private String affectation;
  private String libelleServiceAffectation;
  private TypeEnseignant typeEnseignant;
  private float volumeHoraire;
  private String volumeHoraireAnnee;

  public Enseignant(String teacher_uuid, String nom, String prenom, String affectation,
      String libelleServiceAffectation,
      TypeEnseignant typeEnseignant,
      float volumeHoraire,
      String volumeHoraireAnnee) {
    super();
    this.teacher_uuid = teacher_uuid;
    this.nom = nom;
    this.prenom = prenom;
    this.affectation = affectation;
    this.libelleServiceAffectation = libelleServiceAffectation;
    this.typeEnseignant = typeEnseignant;
    this.volumeHoraire = volumeHoraire;
    this.volumeHoraireAnnee = volumeHoraireAnnee;
  }

  public String getId() {
    return "T" + teacher_uuid;
  }

  public String getTeacher_uuid() {
    return teacher_uuid;
  }

  public void setTeacher_uuid(String teacher_uuid) {
    this.teacher_uuid = teacher_uuid;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getAffectation() {
    return affectation;
  }

  public void setAffectation(String affectation) {
    this.affectation = affectation;
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
