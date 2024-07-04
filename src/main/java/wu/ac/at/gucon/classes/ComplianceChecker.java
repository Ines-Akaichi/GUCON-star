package wu.ac.at.gucon.classes;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class ComplianceChecker {
	
	public String createSelectSparql (String graphPattern)
	{
		
		String sparql = "select * where {" + graphPattern + "}";
		//System.out.println("Select Condition query "+sparql);
		
		
		return sparql;
		
	}
	
   public String createAskSparql (String deonticPattern)
	{
		String askQuery = "ASK where { " + deonticPattern + "}";
	
		//System.out.println("Ask query:  "+askQuery);
		return askQuery;
	}
	
   public Boolean exists (String deonticPattern, Dataset dataset)
	{
		Boolean exist = false;
		//String askSparql = "ASK  where {" + pattern + "}";
		String askSparql = createAskSparql (deonticPattern);
		//System.out.println(askSparql);
		Query sparql = QueryFactory.create(askSparql);
		QueryExecution qe = QueryExecutionFactory.create(sparql, dataset);
		boolean result = qe.execAsk();
		if(result == true){
			exist= true;
		}
		return exist;

	}
	
   
   
   public Boolean checker (Dataset dataset, Rule r)
	{
       	Boolean compliant = true ;
       	String oppositeSparqlCOndition =null;
       	String sparqlCondition=null;
       	Query sparql =null;
       	QueryExecution qe =null;
       	ResultSet results =null;
		//System.out.println(r.getActionPattern().trim());

		switch (r.getDeonticOperator())

		{
		case "A":
			System.out.println("case A");
		 	oppositeSparqlCOndition = r.replaceGraphPattern(r.getGraphPattern().trim());
    		//System.out.println("check rule" +oppositeSparqlCOndition);
			sparqlCondition = createSelectSparql (oppositeSparqlCOndition);
			sparql = QueryFactory.create(sparqlCondition);
			qe = QueryExecutionFactory.create(sparql, dataset);
			results = qe.execSelect();
	
			if(results!=null)  // condition is active -- that mean there is a entry in the log that fired this condition
			{	
				/*System.out.println("The condition of the rule is active");
			
				
				if (exists (r.getActionPattern(),dataset)) {
							compliant =false;
							System.out.println("not compliant");		 
			    }*/	
				if (results.hasNext())
				{
				ResultSetFormatter.out(results);
				compliant =false;
				System.out.println("The knowledge base is not compliant");	
				}
				else System.out.println("The knowledge base is compliant");	
				
				
			}
			
			break;
			
		case "P":
			System.out.println("case P");
			sparqlCondition = createSelectSparql (r.getGraphPattern());
			sparql = QueryFactory.create(sparqlCondition);
			qe = QueryExecutionFactory.create(sparql, dataset);
			results = qe.execSelect();
			
			if(results!=null)  // condition is active -- that mean there is a entry in the log that fired this condition
			{
					/*System.out.println("The condition of the rule is active");
		    		if (exists (r.getActionPattern(),dataset)) {
						compliant =false;
						System.out.println("not compliant");		 
				    }*/
				if (results.hasNext())
				{
				ResultSetFormatter.out(results);
				System.out.println("The knowledge base is not compliant");
				compliant =false;

				}
				else  System.out.println("The knowledge base is  compliant");

			}
			
			


			break;
			
		case "O":
			System.out.println("case O");
			sparqlCondition = createSelectSparql (r.getGraphPattern());
			sparql = QueryFactory.create(sparqlCondition);
			qe = QueryExecutionFactory.create(sparql, dataset);
			results = qe.execSelect();
			
			if(results!=null) 
			{
				if (results.hasNext())
				{
				/*System.out.println("The condition of the rule is active");
					if (!exists (r.getActionPattern(),dataset)) {
						
							compliant =false;
							System.out.println("not compliant");		 
					}	*/			
				//compliant =false;
				ResultSetFormatter.out(results);
				System.out.println("The knowledge base is  compliant");
				}	
				else  
				{
				compliant =false;
				System.out.println("The knowledge base is not compliant"); }
					
			}
			

			break;
			
		case "D":
			System.out.println("case D");
			oppositeSparqlCOndition = r.replaceGraphPattern(r.getGraphPattern());
    		//System.out.println("check rule" +oppositeSparqlCOndition);
			sparqlCondition = createSelectSparql (oppositeSparqlCOndition);
			sparql = QueryFactory.create(sparqlCondition);
			qe = QueryExecutionFactory.create(sparql, dataset);
			results = qe.execSelect();
			
			if(results!=null)  // condition is active -- that mean there is a entry in the log that fired this condition
			{
					/*System.out.println("The condition of the rule is active");
					if (exists (r.getActionPattern(),dataset)) {
							compliant =false;
							System.out.println("not compliant");		 
					}	
					
					//System.out.println("compliant");*/
				if (results.hasNext())
				{
				ResultSetFormatter.out(results);
				System.out.println("The knowledge base is not compliant");
				compliant =false;
				}
				else  System.out.println("The knowledge base is compliant");

			}	

			break;
					
		}
		
		return compliant;

		}
}
