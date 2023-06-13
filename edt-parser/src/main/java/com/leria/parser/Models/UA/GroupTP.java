package com.leria.parser.Models.UA;

import java.util.List;
import com.leria.parser.Models.Leria.objects.Class;

public class GroupTP {
  private int n;
  private int parent;
  private int parent2;
  List<Class> classes;

  public GroupTP(int n, int parent) {
    this.n = n;
    this.parent = parent;
    this.parent2 = 0;
  }

  public GroupTP(int n, int parent, int parent2) {
    this.n = n;
    this.parent = parent;
    this.parent2 = parent2;
  }

  public int getN() {
    return n;
  }

  public int getParent() {
    return parent;
  }

  public void setN(int n) {
    this.n = n;
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

  @Override
  public String toString() {
    return (has2Parents() ? Integer.toString(parent) + "-" + Integer.toString(parent2) : Integer.toString(parent));
  }
}
