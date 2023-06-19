package com.leria.parser.Models.UA;

import java.util.List;
import com.leria.parser.Models.Leria.objects.Class;

public class GroupTP {
  private int numeroGroupe;
  private int effectif;
  private int parent;
  private int parent2;
  List<Class> classes;

  public GroupTP(int numeroGroupe, int effectif, int parent) {
    this.numeroGroupe = numeroGroupe;
    this.effectif = effectif;
    this.parent = parent;
    this.parent2 = 0;
  }

  public GroupTP(int numeroGroupe, int effectif, int parent, int parent2) {
    this.numeroGroupe = numeroGroupe;
    this.effectif = effectif;
    this.parent = parent;
    this.parent2 = parent2;
  }

  public int getNumeroGroupe() {
    return numeroGroupe;
  }

  public int getEffectif() {
    return effectif;
  }

  public int getParent() {
    return parent;
  }

  public void setNumeroGroupe(int numeroGroupe) {
    this.numeroGroupe = numeroGroupe;
  }

  public void setEffectif(int effectif) {
    this.effectif = effectif;
  }

  public void setParent(int parent) {
    this.parent = parent;
  }

  public int getParent2() {
    return parent2;
  }

  public void setParent2(int parent2) {
    this.parent2 = parent2;
  }

  public boolean has2Parents() {
    return parent2 != 0;
  }
}
