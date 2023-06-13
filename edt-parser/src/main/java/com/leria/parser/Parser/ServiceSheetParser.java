package com.leria.parser.Parser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.leria.parser.Api.API;
import com.leria.parser.Models.UA.ServiceSheet;

public class ServiceSheetParser {

  private ServiceSheetParser() {
  }

  public static List<ServiceSheet> parseServiceSheet(String year, String courseId, String typeCourse) {
    try {
      HttpResponse<String> response = API.requestServiceEnseignant(year, courseId, typeCourse);
      Gson gson = new Gson();
      if (response.statusCode() != 200) {
        throw new Exception("The ServiceSheet API returned a status code : " + response.statusCode()
            + ", check that the server is up and running");
      }
      ServiceSheet[] serviceSheetArray = gson.fromJson(response.body(), ServiceSheet[].class);
      List<ServiceSheet> serviceSheets = new ArrayList<>();
      for (ServiceSheet serviceSheet : serviceSheetArray) {
        serviceSheets.add(serviceSheet);
      }
      return serviceSheets;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }

  }
}
