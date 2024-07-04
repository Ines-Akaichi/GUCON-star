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
		//System.out.println(rule);
        // Extract the first part between the first pair of {}
		//"\\{.*?\\}"
		Pattern firstPattern = Pattern.compile("\\s*\\{\\s*.*?\\s*\\}\\s*",Pattern.DOTALL);
	    Matcher firstMatcher = firstPattern.matcher(rule);
	        if (firstMatcher.find()) {
	        	graphPattern = firstMatcher.group().trim();
	        }
		
	    // Extract the deontic pattern 
	    //"http://www.example.org#implies \\s*([a-zA-Z])\\s*\\{"
	    Pattern letterPattern = Pattern.compile("[a-zA-Z]\\.*? \\{.*?\\}", Pattern.DOTALL);
	    Matcher letterMatcher = letterPattern.matcher(rule);
	        if (letterMatcher.find()) {
	        	deonticPattern = letterMatcher.group().trim();
	        	
	        }
	     //System.out.println("deontic pattern" + deonticPattern);   
	     graphPattern = graphPattern.substring(1,graphPattern.length()-1).trim();
		 actionPattern = deonticPattern.substring(3,deonticPattern.length()-1).trim() ;
		 
		 // Extract the deontic operator 
	     //System.out.println("action pattern" + actionPattern);   

	     deonticOperator= deonticPattern.substring(0,1).trim() ;
	     //System.out.println("deontic operator" + deonticOperator);   

	     //System.out.println("First part: " + graphPattern);
	     //System.out.println("Second part: " + actionPattern);
	     //System.out.println("Letter before second braces: " + deonticOperator);
	
	
	}
	
	public String replaceGraphPattern (String graphPattern)
	{
		String operator = null;
		String newOperator = null;
		String updatedOperator = null;
		String updatedGraphPattern = graphPattern;
		String unaryOperator = null;

		//  \\s*\\( \\s* .*? \\s*[>|<|>=|<=|!=|=] .*? \\)
		//FILTER \\s* [(] \\s* .*? \\s* [>|<|>=|<=|!=|=] \\s* .*? \\s* [)]
		Pattern operatorPattern = Pattern.compile("FILTER\\s*[(]\\s*.*?\\s* [>|<|>=|<=|!=|=]",Pattern.DOTALL);
		Matcher operatorMatcher = operatorPattern.matcher(graphPattern);
		//System.out.println ("operatorPattern   " + operatorPattern);
		
		
		if (operatorMatcher.find()) {
			operator = operatorMatcher.group().trim();
			//System.out.println("operator*** " +operator);
			
			Pattern operatorPattern1 = Pattern.compile("[>|<|>=|<=|!=|=]",Pattern.DOTALL);
			Matcher operatorMatcher1 = operatorPattern1.matcher(operator);
			
			if (operatorMatcher1.find()) {
				unaryOperator = operatorMatcher1.group().trim();
				//System.out.println("unaryOperator*** " +unaryOperator);
				}
			
			if (operator.contains(">")||operator.contains(">="))
				{

					 newOperator = "<";
					// System.out.println("new operator "  + newOperator);

					 updatedOperator = operator ;
					// System.out.println("before updatedOperator "  + updatedOperator);

					 updatedOperator=updatedOperator.replace(unaryOperator, newOperator);
					// System.out.println("after updatedOperator "  + updatedOperator);
					 updatedGraphPattern=updatedGraphPattern.replace(operator, updatedOperator);

				}
				
				if (operator.contains("<") || operator.contains("<="))
				{
					 newOperator = ">";
				     updatedOperator = operator ;
				     updatedOperator=updatedOperator.replace(unaryOperator, newOperator);
					 //System.out.println("updatedOperator "  + updatedOperator);
					 updatedGraphPattern=updatedGraphPattern.replace(operator, updatedOperator);

				}
				
			 //System.out.println("operator " +operator);
			// System.out.println("new operator " +updatedOperator);
			 


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
