package com.leria.parser.Models.UA;

public class GroupTD {
  private int n;
  private int parent;

  public GroupTD(int n, int parent) {
    this.n = n;
    this.parent = parent;
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

  @Override
  public String toString() {
    return Integer.toString(parent);
  }
}
