package com.leria.parser.Rule;

import java.util.Arrays;


public class RuleManager {
	private String type;
	private String filterType;
	
	private String effect;
	private String value;
	private String verification;
	private String probality;
	
	private Selector[] selectors;
	private String[][] values;
	private PredicatUTP predicatUTP;
	public RuleManager(String type, String filterType, String effect, String value,
			String verification, String probality ,Selector[] selectors, PredicatUTP predicatUTP) {
		this.type = type;
		this.filterType = filterType;
		this.effect = effect;
		this.value = value;
		this.verification = verification;
		this.probality = probality;
		
		this.selectors = selectors;
		this.predicatUTP = predicatUTP;
		verifications = inflateFilter(this.verification);
		values = inflateFilter(this.value);
		Arrays.stream(verifications).forEach(u->System.out.println(Arrays.toString(u)));
		Arrays.stream(values).forEach(u->System.out.println(Arrays.toString(u)));
		//System.out.println("valide = "+verificationChecker("part",new String []{"pasCM","JK","CM"})); 
	}//FinMethod
	
	String[][] verifications;
	
	private String[][] inflateFilter(String filter) {
		if(filter.equals("")) {
			return  new String[0][0];
		}
		String[] result  = filter.split("},");
		return Arrays.stream(result).map(res->{ 
			String[] output = res.split("\\{");
			String[] output2 = output[1].split(",");
			return Arrays.stream(output2).map(out2 -> out2.replaceAll("\\}", "")).map(
			out2->new String[]{output[0],out2}).toArray(String[][]::new);}).flatMap(Arrays::stream).toArray(String[][]::new);
	}//FinMethod
	
	/**
	 * Checking method for verify labels of elementCourse for apply rules
	 * @return boolean
	 * */
	public boolean verificationChecker(String elementCourse,String[] labels) {
		return Arrays.stream(labels)
					.map(label->{
							return Arrays.stream(verifications).anyMatch(u->u[0].equals(elementCourse) && u[1].equals(label) ); 
					})
					.reduce((a,b)->a || b).get();
	}//FinMethod
	
	/**
	 * Check if labels of the elements or in definition of the rule
	 * if label is good with verification @return true;
	 * else @return false
	 * */
	public boolean checkLabelElement(String label) {
		String labelTmp = label.replaceAll("\\s+", "");
		String[] labels = labelTmp.split(",");
		if(this.filterType.equals("*")||this.filterType.equals("")) {
			return true;
		}
		return Arrays.stream(labels).anyMatch(label2->this.filterType.equals(label2));
	}//FinMethod
	
	public String getType() {
		return type;
	}//FinMethod
	public String getFilterType() {
		return filterType;
	}//FinMethod
	public String getEffect() {
		return effect;
	}//FinMethod
	public String getValue() {
		return value;
	}//FinMethod
	public String getVerification() {
		return verification;
	}//FinMethod
	public String getProbality() {
		return probality;
	}//FinMethod
	public Selector[] getSelectors() {
		return selectors;
	}//FinMethod
	public PredicatUTP getPredicatUTP() {
		return predicatUTP;
	}//FinMethod
}//FinClass
