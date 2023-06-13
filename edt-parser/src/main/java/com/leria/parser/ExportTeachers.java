package com.leria.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.leria.parser.Api.API;

/*
 ********** Class only used to dump all the courses data from the UA API **********
 */
public class ExportTeachers {

  public static final String YEAR = "2022";

  public static void main(String[] args) {
    try (FileWriter writer = new FileWriter("instancesTeachers" + YEAR + ".json")) {
      writeMaquette(writer, YEAR);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void writeMaquette(FileWriter writer, String year) throws IOException {
    Pattern pattern = Pattern.compile("^[12]\\d{3}$");
    if (!pattern.matcher(year).matches()) {
      throw new IllegalArgumentException("Invalid year: " + year);
    }
    HttpResponse<String> response = API.requestEnseignants(year);
    Gson gson = new Gson();
    writer.write(response.body());
  }
}