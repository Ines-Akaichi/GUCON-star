package wu.ac.at.gucon.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.riot.RDFDataMgr;

public class TestCases {
	
	public static List<String>  readRulesFromFile (String FilePath) throws IOException
	{
		BufferedReader br = null; 
    	try {
    		 br  = new BufferedReader(new FileReader(FilePath));				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    	String line;
    	List<String> allStrings = new ArrayList<String>();
    	String fullString = "";
    	String str ="";
    	line=br.readLine();
    	while(line!= null )
    	{

    		fullString += line;
    		fullString += "";

    	    if(line.isEmpty())
    	    {
    	     
    	     	//str= "";
        		allStrings.add(fullString.trim());
	    	    allStrings.add(str);
	    	    fullString = "";
    	    }
    	    
    	line=br.readLine();

    	}
    	return allStrings;
   
	}
	
	
	 public static void main( String[] args ) throws IOException
	    {
		 
		 
		 String ruleFile= "C:/Users/iakaichi/OneDrive - WU Wien/Desktop/PhD/Papers/Paper WU & FORTH/Extension/test-rules.txt";
 	     String logFile ="C:/Users/iakaichi/OneDrive - WU Wien/Desktop/PhD/Papers/Paper WU & FORTH/Extension/kb-traces-reified.ttl";
 	     String lastRule ="{ ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.example.org#Person> .\r\n"
 	     		+ "?x <http://www.example.org#position> <http://www.example.org#Journalist> .\r\n"
 	     		+ "<< ?x <http://www.example.org#share> <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> >>  <http://purl.org/specialprivacy/splog#occurs>  ?t .\r\n"
 	     		+ "FILTER (?t < \"2021-02-25T21:00:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime> ) } <http://www.example.org#implies> \r\n"
 	     		+ "{ D {?x <http://www.example.org#share> <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> } } ";

	     Dataset dataset;
		 dataset = RDFDataMgr.loadDataset(logFile) ;
		 ComplianceChecker c1 = new ComplianceChecker ();	
		 Boolean compl;
		 Rule r ;
			

		 //last rule was not read by the readRulesFromFile. I add it here for now.
	     List<String> allRules = readRulesFromFile(ruleFile);
	     allRules.add(lastRule);
	     
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
	    	
	    //System.out.println(tmp);
		//r = new Rule (allRules.get(0));

	      
	    }    
}
