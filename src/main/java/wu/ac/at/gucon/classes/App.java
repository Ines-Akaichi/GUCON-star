package wu.ac.at.gucon.classes;

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
    public static void main( String[] args )
    {
    	
    /*
    	
    	String base = "{?x   <http://www.example.org#share>   <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage>. \r\n"
    			+ "	<< ?x  <http://www.example.org#share> <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> >>  <http://purl.org/specialprivacy/splog#occurs> ?t . \r\n"
    			+ " FILTER (?t <\"2021-02-25T21:00:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime> ) } ";
    	
    	String base2 ="{?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.example.org#Person> }";
    			
		String askQuery1 = "Ask WHERE "+base;
		
		String selectQuery2 = "SELECT * WHERE" +base;
		
		String selectQuery1 = "PREFIX eg: <http://www.example.org#> " +
			    "PREFIX splog: <http://purl.org/specialprivacy/splog#> " +
			    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
			    "SELECT * WHERE { " +
			    "  ?x eg:share <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> . " +
			    "  << ?x eg:share <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> >> splog:occurs ?t . " +
			    "  FILTER (?t < \"2021-02-25T21:00:00Z\"^^xsd:dateTime) " +
			    "}";
		
		String logFile1 ="C:/Users/iakaichi/OneDrive - WU Wien/Desktop/PhD/Papers/Paper WU & FORTH/Extension/kb-traces-reified.ttl";
    	Dataset dataset1;
		dataset1 = RDFDataMgr.loadDataset(logFile1) ;
		
		
		// test ask query
		Boolean exist = false;
		Query sparql = QueryFactory.create(askQuery1);
		QueryExecution qe = QueryExecutionFactory.create(sparql, dataset1);
		boolean result = qe.execAsk();
		if(result){
			exist= result;
		}
		System.out.println(exist);
		
		// test select query
		Query sparql1 = QueryFactory.create(selectQuery2);
		QueryExecution qe1 = QueryExecutionFactory.create(sparql1, dataset1);
		ResultSet results = qe1.execSelect();
		
		 if (results.hasNext()) { 
			
		        ResultSetFormatter.out(results);
		    
		} else {
		    System.out.println("No results found");
		

		}
		
	*/
    	
    	String rule1= "{?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.example.org#Person>.\r\n"
    			+ "   ?x <http://www.example.org#position> <http://www.example.org#Journalist> .\r\n"
    			+ "  << ?x <http://www.example.org#share> <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> >>  <http://purl.org/specialprivacy/splog#occurs>  ?t .\r\n"
    			+ "  FILTER (?t > \"2021-02-25T21:00:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>)}\r\n"
    			+ "<http://www.example.org#implies>\r\n"
    			+ "{ A {?x  <http://www.example.org#share>  <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> } }";
        Rule r1 = new Rule (rule1); 
    	String logFile1 ="C:/Users/iakaichi/OneDrive - WU Wien/Desktop/PhD/Papers/Paper WU & FORTH/Extension/kb-traces-reified.ttl";
    	Dataset dataset1;
		dataset1 = RDFDataMgr.loadDataset(logFile1) ;
		ComplianceChecker c1 = new ComplianceChecker ();
		Boolean compl1= c1.checker(dataset1, r1);
		
		 
    }
    
}
