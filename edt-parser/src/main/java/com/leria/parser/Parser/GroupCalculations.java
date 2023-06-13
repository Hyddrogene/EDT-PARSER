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

    List<GroupTD> groupsTD = calculRepartitionTD(nrClassesCM, nrClassesTD);
    List<GroupTP> groupsTP = calculRepartitionTP(nrClassesTD, nrClassesTP);

    return new Repartition(nrClassesCM, nrClassesTD, nrClassesTP, groupsTD, groupsTP);
  }

  private static List<GroupTD> calculRepartitionTD(int nrClassesCM, int nrClassesTD) {
    List<GroupTD> groupsTD = new ArrayList<>();
    int count = 1;
    for (int i = 1; i <= nrClassesCM; i++) {
      for (int j = 1; j <= nrClassesTD / nrClassesCM; j++) {
        groupsTD.add(new GroupTD(count++, i));
      }
      if (nrClassesCM - i < nrClassesTD % nrClassesCM) {
        groupsTD.add(new GroupTD(count++, i));
      }
    }
    return groupsTD;
  }

  private static List<GroupTP> calculRepartitionTP(int nrClassesTD, int nrClassesTP) {
    List<GroupTP> groupsTP = new ArrayList<>();
    int count = 1;
    for (int i = 1; i <= nrClassesTD; i++) {
      if (nrClassesTP % nrClassesTD == 0 || i <= nrClassesTD - 2) {
        for (int j = 0; j < 2; j++) {
          groupsTP.add(new GroupTP(count++, i));
        }
      } else {
        groupsTP.add(new GroupTP(count++, i));
        if (i == nrClassesTD)
          groupsTP.add(new GroupTP(count++, nrClassesTD, nrClassesTD - 1));
      }
    }
    return groupsTP;
  }
}
