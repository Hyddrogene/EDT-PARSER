package com.leria.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import com.leria.parser.Models.UA.GroupTD;
import com.leria.parser.Models.UA.GroupTP;
import com.leria.parser.Models.UA.Repartition;

public class GroupCalculations {

  private GroupCalculations() {
  }

  public static Repartition calculRepartitions(int effectif) {
    int nrClassesCM = (int) Math.ceil(effectif / (float) CourseParser.MAX_HEAD_COUNT_CM);
    int nrClassesTD = (int) Math.ceil(effectif / (float) CourseParser.MAX_HEAD_COUNT_TD);
    int nrClassesTP = (int) Math.ceil(effectif / (float) CourseParser.MAX_HEAD_COUNT_TP);

    List<GroupTD> groupsTD = calculRepartitionTD(effectif);
    List<GroupTP> groupsTP = calculRepartitionTP(effectif);

    return new Repartition(nrClassesCM, nrClassesTD, nrClassesTP, groupsTD, groupsTP);
  }

  private static List<GroupTD> calculRepartitionTD(int effectif) {
    int nrClassesCM = (int) Math.ceil(effectif / (float) CourseParser.MAX_HEAD_COUNT_CM);
    int nrClassesTD = (int) Math.ceil(effectif / (float) CourseParser.MAX_HEAD_COUNT_TD);
    List<GroupTD> groupsTD = new ArrayList<>();
    int count = 1;
    int effectifCount = 0;
    // Put an equal number of groups in each CM class (any extra groups are put in
    // the last class)
    for (int i = 1; i <= nrClassesCM; i++) {
      // Put the same number of groups in each CM class
      for (int j = 1; j <= nrClassesTD / nrClassesCM; j++) {
        // Calculate the number of students for the TD class
        if (effectif > CourseParser.MAX_HEAD_COUNT_TD) {
          effectifCount = CourseParser.MAX_HEAD_COUNT_TD;
        } else {
          effectifCount = effectif;
        }
        groupsTD.add(new GroupTD(count++, effectifCount, i));
        effectif -= effectifCount;
      }
      // Put the extra groups in the last CM class
      if (nrClassesCM - i < nrClassesTD % nrClassesCM) {
        // Calculate the number of students for the TD class
        if (effectif > CourseParser.MAX_HEAD_COUNT_TD) {
          effectifCount = CourseParser.MAX_HEAD_COUNT_TD;
        } else {
          effectifCount = effectif;
        }
        groupsTD.add(new GroupTD(count++, effectifCount, i));
        effectif -= effectifCount;
      }
    }
    if (effectif > 0) {
      throw new IllegalArgumentException("Error while calculating the number of TD classes");
    }
    return groupsTD;
  }

  private static List<GroupTP> calculRepartitionTP(int effectif) {
    int nrClassesTD = (int) Math.ceil(effectif / (float) CourseParser.MAX_HEAD_COUNT_TD);
    int nrClassesTP = (int) Math.ceil(effectif / (float) CourseParser.MAX_HEAD_COUNT_TP);
    List<GroupTP> groupsTP = new ArrayList<>();
    int count = 1;
    int effectifCount = 0;
    for (int i = 1; i <= nrClassesTD; i++) {
      if (nrClassesTP % nrClassesTD == 0 || i <= nrClassesTD - 2) {
        for (int j = 0; j < 2; j++) {
          if (effectif > CourseParser.MAX_HEAD_COUNT_TD) {
            effectifCount = CourseParser.MAX_HEAD_COUNT_TD;
          } else {
            effectifCount = effectif;
          }
          groupsTP.add(new GroupTP(count++, effectif, i));
          effectif -= effectifCount;
        }
      } else {
        if (effectif > CourseParser.MAX_HEAD_COUNT_TD) {
          effectifCount = CourseParser.MAX_HEAD_COUNT_TD;
        } else {
          effectifCount = effectif;
        }
        groupsTP.add(new GroupTP(count++, effectif, i));
        effectif -= effectifCount;
        if (i == nrClassesTD) {
          if (effectif > CourseParser.MAX_HEAD_COUNT_TD) {
            effectifCount = CourseParser.MAX_HEAD_COUNT_TD;
          } else {
            effectifCount = effectif;
          }
          groupsTP.add(new GroupTP(count++, effectif, nrClassesTD, nrClassesTD - 1));
          effectif -= effectifCount;
        }
      }
    }
    return groupsTP;
  }
}
