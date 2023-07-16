package com.leria.parser.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.leria.parser.Config.ConfigurationFile;
import com.leria.parser.Config.SelectEtape;
import com.leria.parser.Rule.PredicatUTP;
import com.leria.parser.Rule.RuleManager;
import com.leria.parser.Rule.Selector;

public class RuleGenerator {
	private String configFile;
	private File   fileRuleConfig;

	public RuleGenerator(String configFile) {
		this.configFile = configFile;
		fileRuleConfig = new File(this.configFile);
		readFileConfig(fileRuleConfig);
	}//FinMethod

	
	public static RuleManager readFileConfig(File file) {
	    try {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder builder = factory.newDocumentBuilder();
	      
	      Document document = builder.parse(file);
	      document.getDocumentElement().normalize();

	      Element root = document.getDocumentElement();
	      NodeList configurations = root.getElementsByTagName("configuration");
	      
	      RuleManager[] ruleManagerTab; 
	      
	      for(int i = 0; i < configurations.getLength() ;i++) {
	    	  NodeList rulemanagers = ((Element)configurations.item(i)).getElementsByTagName("ruleManager");
	    	  
	    	  
	    	  String type   = configurations.item(i).getAttributes().getNamedItem("type").getNodeValue().toString();
	    	  String filter = configurations.item(i).getAttributes().getNamedItem("filter").getNodeValue().toString();
	    	  System.out.println("type : "+type+" filter : "+filter);
	    	  System.out.println("list : "+rulemanagers.item(0)+" length = "+rulemanagers.getLength());
	    	  for(int j = 0; j < rulemanagers.getLength() ;j++) {
	    		  String effect = rulemanagers.item(j).getAttributes().getNamedItem("effect").getNodeValue().toString();
	    		  String value = rulemanagers.item(j).getAttributes().getNamedItem("value").getNodeValue().toString();
	    		  String verification = rulemanagers.item(j).getAttributes().getNamedItem("verification").getNodeValue().toString();
	    		  String probability = rulemanagers.item(j).getAttributes().getNamedItem("probality").getNodeValue().toString();
	    		  System.out.println("effect : "+effect+" value : "+value+" verification : "+verification+" probability :"+probability);
	    		  
	    		  NodeList selectors = ((Element) rulemanagers.item(j)).getElementsByTagName("selector");
	    		  Node constraint = ((Element) rulemanagers.item(j)).getElementsByTagName("constraint").item(0);
	    		  
	    		  Selector[] generatorSelectors = new Selector[selectors.getLength()];
	    		  
	    		  for(int p = 0; p < selectors.getLength() ;p++) {
	    			String generatorSelector = selectors.item(p).getAttributes().getNamedItem("generator").getNodeValue().toString();
	    			String filterSelector = selectors.item(p).getAttributes().getNamedItem("filters").getNodeValue().toString();
	    			//System.out.println(" generatorSelector : "+generatorSelector+" filterSelector : "+filterSelector);
	    			  generatorSelectors[p] = new Selector(generatorSelector,filterSelector);
	    		  }
	    		  PredicatUTP predicat = new PredicatUTP(
	    				  constraint.getAttributes().getNamedItem("name").getNodeValue().toString(),
	    				  constraint.getAttributes().getNamedItem("type").getNodeValue().toString(),
	    				  ""
	    				  );
	    		  
	    		  RuleManager ruleManager = new RuleManager();
	    		  
	    	  }
	      }
	      
	      return new RuleManager();
	  }
	  catch (Exception e) {
		   System.out.println("Error while parsing configuration file");
		   e.printStackTrace();
	  }
	  return null;
	}//FinMethod
	
	
}//FinClass
