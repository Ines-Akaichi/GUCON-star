package wu.ac.at.gucon.classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {
	
	private String  graphPattern;
	private String deonticPattern;
	private String deonticOperator;
	private String actionPattern ;
	

	public Rule (String rule)
	{
		rule.trim();
		Pattern firstPattern = Pattern.compile("\\s*\\{\\s*.*?\\s*\\}\\s*",Pattern.DOTALL);
	    Matcher firstMatcher = firstPattern.matcher(rule);
	        if (firstMatcher.find()) {
	        	graphPattern = firstMatcher.group().trim();
	        }
		
	    Pattern letterPattern = Pattern.compile("[a-zA-Z]\\.*? \\{.*?\\}", Pattern.DOTALL);
	    Matcher letterMatcher = letterPattern.matcher(rule);
	        if (letterMatcher.find()) {
	        	deonticPattern = letterMatcher.group().trim();
	        	
	        }
	     graphPattern = graphPattern.substring(1,graphPattern.length()-1).trim();
		 actionPattern = deonticPattern.substring(3,deonticPattern.length()-1).trim() ;
		 
	

	     deonticOperator= deonticPattern.substring(0,1).trim() ;

	
	
	}
	
	public String replaceGraphPattern (String graphPattern)
	{
		String operator = null;
		String newOperator = null;
		String updatedOperator = null;
		String updatedGraphPattern = graphPattern;
		String unaryOperator = null;


		Pattern operatorPattern = Pattern.compile("FILTER\\s*[(]\\s*.*?\\s* [>|<|>=|<=|!=|=]",Pattern.DOTALL);
		Matcher operatorMatcher = operatorPattern.matcher(graphPattern);
		
		
		if (operatorMatcher.find()) {
			operator = operatorMatcher.group().trim();
			
			Pattern operatorPattern1 = Pattern.compile("[>|<|>=|<=|!=|=]",Pattern.DOTALL);
			Matcher operatorMatcher1 = operatorPattern1.matcher(operator);
			
			if (operatorMatcher1.find()) {
				unaryOperator = operatorMatcher1.group().trim();
				}
			
			if (operator.contains(">")||operator.contains(">="))
				{

					 newOperator = "<";

					 updatedOperator = operator ;

					 updatedOperator=updatedOperator.replace(unaryOperator, newOperator);
					 updatedGraphPattern=updatedGraphPattern.replace(operator, updatedOperator);

				}
				
				if (operator.contains("<") || operator.contains("<="))
				{
					 newOperator = ">";
				     updatedOperator = operator ;
				     updatedOperator=updatedOperator.replace(unaryOperator, newOperator);
					 updatedGraphPattern=updatedGraphPattern.replace(operator, updatedOperator);

				}

			 


    }
		
	
     return updatedGraphPattern;
	}

	public String getGraphPattern() {
		return graphPattern;
	}

	public void setGraphPattern(String graphPattern) {
		this.graphPattern = graphPattern;
	}

	public String getDeonticPattern() {
		return deonticPattern;
	}

	public void setDeonticPattern(String deonticPattern) {
		this.deonticPattern = deonticPattern;
	}

	public String getDeonticOperator() {
		return deonticOperator;
	}

	public void setDeonticOperator(String deonticOperator) {
		this.deonticOperator = deonticOperator;
	}

	public String getActionPattern() {
		return actionPattern;
	}

	public void setActionPattern(String actionPattern) {
		this.actionPattern = actionPattern;
	}
	
	

	
	

}
