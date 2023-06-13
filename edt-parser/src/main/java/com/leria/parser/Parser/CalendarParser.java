package com.leria.parser.Parser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.leria.parser.Api.API;
import com.leria.parser.Config.ConfigurationFile;
import com.leria.parser.Models.UA.EtapeCalendar;
import com.leria.parser.Models.UA.UACalendar;

public class CalendarParser {
  private CalendarParser() {
  }

  public static UACalendar parseCalendar(ConfigurationFile config) throws Exception {
    System.out.println("Parsing calendar");
    try { // Send request to API
      HttpResponse<String> response = API.requestEtapeCalendars(config.getYear());
      Gson gson = new Gson();
      EtapeCalendar[] etapeCalendars = gson.fromJson(response.body(), EtapeCalendar[].class);
      List<EtapeCalendar> etapes = new ArrayList<>();
      int start = Integer.MAX_VALUE;
      int end = Integer.MIN_VALUE;
      for (EtapeCalendar etapeCalendar : etapeCalendars) {
        etapes.add(etapeCalendar);
        if (etapeCalendar.getStartingWeek() < start) {
          start = etapeCalendar.getStartingWeek();
        }
        if (etapeCalendar.getEndingWeek() > end) {
          end = etapeCalendar.getEndingWeek();
        }
      }
      return new UACalendar(etapes, start, end);
    } catch (Exception e) {
      throw new Exception("The EtapeCalendar API returned a null response, check that the server is up and running");
    }
  }
}
