package com.leria.parser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.leria.parser.Config.*;
import com.leria.parser.Models.Leria.objects.Timetabling;
import com.leria.parser.Parser.Parser;

public class Main {
  public static void main(String[] args) {
    String filename;
    if (args.length != 1) {
      System.out.println("Usage: java -jar edt-parser-1.0.jar <config_file>");
      System.out.println("Using default config file: edt-parser\\config.xml");
      filename = "edt-parser\\config.xml";
    } else {
      filename = args[0];
     System.out.println("filename_config = "+filename);
    }
    try {
    	Date date = new Date();
		SimpleDateFormat formater2 = new SimpleDateFormat("HH_mm_ss");
		SimpleDateFormat formater3 = new SimpleDateFormat("dd_MM_yy");
		String date_formated2 = formater2.format(date).toString();
		String date_formated3 = formater3.format(date).toString();
      File file = new File(filename);
      ConfigurationFile config = ConfigurationParser.parse(file);
      Timetabling timetable = Parser.parseTimetable(config);
      timetable.exportXML("tmp/"+config.getName() + "-" + date_formated3+"_"+date_formated2 + ".xml");
    } catch (Exception e) {
      System.out.println("<ERROR> " + e.getMessage());
    }
  }
}