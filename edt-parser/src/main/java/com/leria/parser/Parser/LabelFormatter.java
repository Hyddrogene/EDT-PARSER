package com.leria.parser.Parser;

public class LabelFormatter {

  private LabelFormatter() {
  }

  public static String format(String str) {
    if (str == null)
      return "";
    str = str.replace(',', ';');
    str = str.replace('&', '+');
    return str;
  }
}
