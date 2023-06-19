package com.leria.parser.Models.UA;

public class GroupTD {
  private int numeroGroupe;
  private int effectif;
  private int parent;

  public GroupTD(int numeroGroupe, int effectif, int parent) {
    this.numeroGroupe = numeroGroupe;
    this.effectif = effectif;
    this.parent = parent;
  }

  public int getNumeroGroupe() {
    return numeroGroupe;
  }

  public int getParent() {
    return parent;
  }

  public void setNumeroGroupe(int n) {
    this.numeroGroupe = numeroGroupe;
  }

  public void setParent(int parent) {
    this.parent = parent;
  }

  public int getEffectif() {
    return effectif;
  }

  public void setEffectif(int effectif) {
    this.effectif = effectif;
  }

  @Override
  public String toString() {
    return Integer.toString(parent);
  }
}
