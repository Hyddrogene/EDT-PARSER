package com.leria.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.leria.parser.Api.API;
import com.leria.parser.Models.UA.BaseEtape;

/*
 ********** Class only used to dump all the courses data from the UA API **********
 */
public class ExportMaquette {
  public static void main(String[] args) {
    try (FileWriter writer = new FileWriter("instances.json")) {
      writeMaquette(writer, "2021");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static void writeMaquette(FileWriter writer, String year) throws IOException {
    Pattern pattern = Pattern.compile("^[12]\\d{3}$");
    if (!pattern.matcher(year).matches()) {
      throw new IllegalArgumentException("Invalid year: " + year);
    }
    writer.write("[");
    HttpResponse<String> maquette = API.requestEtapes(year);
    Gson gson = new Gson();
    BaseEtape[] etapes = gson.fromJson(maquette.body(), BaseEtape[].class);
    int count = 1;
    for (BaseEtape baseEtape : etapes) {
      System.out.print("Récupération de l'étape " + count + "/" + etapes.length + "\r");
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      HttpResponse<String> etape = API.requestMaquette(baseEtape.getCodeEtape(), year);
      if (etape.statusCode() != 200) {
        System.out.println(
            "Error while retrieving data from APIs. \nURI : " + etape.uri() + "\nStatus code : " + etape.statusCode()
                + "\n" + etape.body());
        System.exit(1);
      }
      if (etape.body().equals("[]")) {
        writer.write("[{\"" + baseEtape.getCodeEtape() + "\":\"" + baseEtape.getLibEtape() + "\"}]");
      } else {
        writer.write(etape.body());
      }
      if (count != etapes.length) {
        writer.write(",");
      } else {
        writer.write("]");
      }
      writer.flush();
      count++;
    }
  }
}