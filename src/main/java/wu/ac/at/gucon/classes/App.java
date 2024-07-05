package wu.ac.at.gucon.classes;

import java.io.IOException;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.riot.RDFDataMgr;

/**
 * Hello world!
 *
*/
public class App 

{
    public static void main( String[] args ) throws IOException
    {
    	
    	 //path for KB   
        String logFile = args[0] ; 
        //path for rules 
        String ruleFile =    args [1] ;  
        //query input string 
        
    	 Dataset dataset;
		 dataset = RDFDataMgr.loadDataset(logFile) ;
		 ComplianceChecker c1 = new ComplianceChecker ();	
		 Boolean compl;
		 Rule r ;
		 //TestCases T1 = new TestCases ();

	    List<String> allRules = TestCases.readRulesFromFile(ruleFile);
	     
	    for (String s: allRules)
	    	{
	    		if (!s.isEmpty())
	    		{
		    		System.out.println("Rule:  " +s);
		    		r = new Rule (s.trim());
		    		compl= c1.checker(dataset, r);
					System.out.println(compl);	
		    		System.out.println("**********");

	    		}

	    		//compl= c1.checker(dataset, r);
				//System.out.println(compl);	
	    	}
		 
    }
    
}
