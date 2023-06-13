package com.leria.parser.Parser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.leria.parser.Api.API;
import com.leria.parser.Models.Leria.objects.Room;
import com.leria.parser.Models.Leria.types.UniqueId;
import com.leria.parser.Models.UA.Salle;

public class RoomParser {

  private RoomParser() {
  }

  public static List<Room> parseSalles(String year) throws Exception {
    List<Room> rooms = new ArrayList<>();
    System.out.println("Parsing rooms");
    try {
      HttpResponse<String> response = API.requestSalles(year);
      Gson gson = new Gson();
      Salle[] uaSalles = gson.fromJson(response.body(), Salle[].class);
      for (Salle uaSalle : uaSalles) {
        if (!UniqueId.exists(uaSalle.getNoSalle()))
          rooms.add(uaSalleToSalle(uaSalle));
        else {
          System.out.println("<WARNING> Room " + uaSalle.getNoSalle() + " was ignored because its id already exists");
        }
      }
      System.out.println("Parsed " + rooms.size() + " rooms");
      return rooms;
    } catch (NullPointerException e) {
      throw new Exception("The Room API returned a null response, check that the server is up and running");
    }

  }

  private static Room uaSalleToSalle(Salle uaSalle) {
    String type = "Bureau";
    if (uaSalle.getCapacite() > 60)
      type = "Amphi";
    else if (uaSalle.getCapacite() >= 40)
      type = "Salle de TD";
    else if (uaSalle.getCapacite() >= 10)
      type = "Salle de TP";
    return new Room(uaSalle.getNoSalle(), uaSalle.getCapacite(),
        LabelFormatter.format(uaSalle.getNomSalle()) + ", " + type + ", "
            + LabelFormatter.format(uaSalle.getDepartement()),
        type);
  }
}
