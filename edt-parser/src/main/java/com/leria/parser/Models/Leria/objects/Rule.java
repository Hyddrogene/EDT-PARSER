package com.leria.parser.Models.Leria.objects;

import java.io.FileWriter;

public class Rule {
	public String[] generators;
	public String[] filters;
	public int  	arity;
	public String constraint;
	public String type;
	public String[] parametersName;
	public String[] parametersValue;
	public int nrParameters;
	
	public Rule() {
		
	}//FinMethod
	
	public void exportXML(FileWriter writer) {
	    try {
	      writer.write("<rule>\n");
	      for(int i = 0; i < arity;i++){
	    	  writer.write("<selector generator='"+"("+generators[i]+")"+"' filters="+"filters[i]"+" />\n");
	      }
	      writer.write("<constraint name=\""+constraint+"\" type=\""+type+"\" ");
	      if(nrParameters == 0) {
	    	  writer.write("/>\n");
	      }
	      else {
	    	  writer.write(">\n");
	    	  writer.write("<parameters>\n");
	    	  for(int i = 0; i < nrParameters ;i++) {
	    		  writer.write("<parameter name=\""+parametersName[i]+"\">"+parametersValue[i]+"</parameter>\n");
	    	  }
	    	  writer.write("</parameters>\n</constraint>\n");
	      }
	      
	      writer.write("</rule>\n");
	    } catch (Exception e) {
	      System.out.println("Error while exporting rule");
	      e.printStackTrace();
	    }
	  }//FinMethod
}//FinClass
