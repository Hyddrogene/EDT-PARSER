package com.leria.parser.Config;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationParser {
  private ConfigurationParser() {
  }

  public static ConfigurationFile parse(File file) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(file);
      document.getDocumentElement().normalize();

      Element root = document.getDocumentElement();
      Node nameNode = root.getElementsByTagName("name").item(0);
      String name = nameNode.getTextContent();
      Node yearNode = root.getElementsByTagName("year").item(0);
      String year = yearNode.getTextContent();
      Node facultyNode = root.getElementsByTagName("faculty").item(0);
      String faculty = facultyNode.getTextContent();
      Node nrDaysPerWeekNode = root.getElementsByTagName("nrDaysPerWeek").item(0);
      int nrDaysPerWeek = Integer.parseInt(nrDaysPerWeekNode.getTextContent());
      Node nrSlotsPerDayNode = root.getElementsByTagName("nrSlotsPerDay").item(0);
      int neSlotsPerDay = Integer.parseInt(nrSlotsPerDayNode.getTextContent());
      NodeList selectEtapesNode = root.getElementsByTagName("etapes");
      List<SelectEtape> selectEtapes = new ArrayList<>();
      if (selectEtapesNode.getLength() > 0) {
        NodeList selectEtapeNode = ((Element) selectEtapesNode.item(0)).getElementsByTagName("etape");
        for (int i = 0; i < selectEtapeNode.getLength(); i++) {
          Node selectEtape = selectEtapeNode.item(i);
          if (selectEtape.getNodeType() == Node.ELEMENT_NODE) {
            Element selectEtapeElement = (Element) selectEtape;
            String id = selectEtapeElement.getAttribute("id");
            String label = selectEtapeElement.getAttribute("label");
            String periodes = selectEtapeElement.getAttribute("periodes");
            int effectif = Integer.parseInt(selectEtapeElement.getAttribute("effectif"));
            selectEtapes.add(new SelectEtape(id, label, periodes, effectif));
          }
        }
      }

      return new ConfigurationFile(name, year, faculty, nrDaysPerWeek, neSlotsPerDay, selectEtapes);
    } catch (Exception e) {
      System.out.println("Error while parsing configuration file");
      e.printStackTrace();
    }
    return null;
  }
}
