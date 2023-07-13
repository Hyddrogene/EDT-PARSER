package com.leria.parser.Parser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.leria.parser.Api.API;
import com.leria.parser.Models.Leria.objects.Teacher;
import com.leria.parser.Models.Leria.types.UniqueId;
import com.leria.parser.Models.UA.Enseignant;

public class TeacherParser {

  private TeacherParser() {
  }

  public static List<Teacher> parseEnseignants(String year) throws Exception {
    List<Teacher> teachers = new ArrayList<>();
    System.out.println("Parsing teachers");
    try {
      HttpResponse<String> response = API.requestEnseignants(year);
      Gson gson = new Gson();
      Enseignant[] uaEnseignants = gson.fromJson(response.body(),
          Enseignant[].class);
      for (Enseignant uaEnseignant : uaEnseignants) {
        if (!UniqueId.exists(uaEnseignant.getId()))
          teachers.add(uaEnseignantToTeacher(uaEnseignant));
        else {
          System.out.println("Teacher " + uaEnseignant.getId() + " was ignored because its id already exists");
        }
      }
      System.out.println("Parsed " + teachers.size() + " teachers");
      //Filter for non affected people
      return teachers.stream().filter(t->!Pattern.matches(".*Personnel non affecté.*",t.getLabel().getValue())).toList();
    } catch (NullPointerException e) {
      throw new Exception("The Teacher API returned a null response, check that the server is up and running");
    }

  }

  private static Teacher uaEnseignantToTeacher(Enseignant uaEnseignant) {
    return new Teacher(uaEnseignant.getId(),
        uaEnseignant.getnomEnseignant() + " " + uaEnseignant.getPrenomEnseignant() + ", " +
            uaEnseignant.getAffectation() + ", " + LabelFormatter.format(uaEnseignant.getLibelleServiceAffectation()));
  }
}
