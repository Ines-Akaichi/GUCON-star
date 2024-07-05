# GUCON-star

Here, we present a comprehensive framework called GUCON (Generic Graph Pattern based Policy Framework for Usage Control)-Star that focuses on specifying and enforcing usage control policies in various domains based on different types of constraints. The GUCON framework leverages the power of graph patterns to provide flexible and adaptable policy specification and enforcement mechanisms. By representing policies as graph patterns, we enable the expression of complex relationships and dependencies between different elements of the system. This approach will allow a fine-grained control over the usage of resources, ensuring compliance with specific requirements and restrictions.
GUCOn-Star is built on top of [GUCON](https://link.springer.com/chapter/10.1007/978-3-031-45072-3_3). 

## Scope
For now, GUCON-Star only implements the complaince checker using temporal constraints. 

# Use Case 

An embargo on extracts from a football match usually refers to a restriction placed on media outlets or individuals regarding the publication or dissemination of certain content from the match until a specific time or event has passed. This could include highlights, photographs, interviews, or other media materials. Embargoes are often imposed to ensure fair coverage, prevent spoilers for delayed broadcasts, or control the timing of information releases for strategic reasons.

# Input
The framework expects as input a turle file presenting the Knowledge Base (KB) and a text file with a set of rules, one in each line.
To describe our use case, we developed an [onotlogy](https://github.com/Ines-Akaichi/GUCON-star/blob/main/use-case/ontology.png) based on the [IPTC Sport Schema](https://sportschema.org/schema-diagram/) and the [Ontology for Media Resources 1.0](https://www.w3.org/TR/mediaont-10/). The use case expressed using our ontology can be found [here] (https://github.com/Ines-Akaichi/GUCON-star/blob/main/use-case/use-case.ttl).

An example of a KB expressed using instances from our onotlogy and [the SPECIAL Policy Log Vocabulary] (https://ai.wu.ac.at/policies/policylog/) is described below.  The SPECIAL Policy Log Vocabulary is a vocabulary used to log data processing and sharing events.
```
@prefix  xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix  rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix  rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
@prefix spl: <http://specialprivacy.ercim.eu/langs/usage-policy#> .
@prefix  dct: <http://purl.org/dc/terms/> .
@prefix  prov: <http://www.w3.org/ns/prov#> .
@prefix skos: <http://www.w3.org/2004/02/skos/core#> .
@prefix  eg: <http://www.example.org#> .
@prefix splog: <http://purl.org/specialprivacy/splog#> .

# Declare assertions

eg:JohnSmith rdf:type eg:Person .
eg:JohnSmith eg:position eg:Journalist .

#declare atomic events 

eg:JohnSmith   eg:share  <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> .

# Declare the log entry 

eg:log1 rdf:type splog:Log ;
dct:title             "Log of tracking application of media sharing of the premier league match"@en ;
dct:description       "This contains all traces of  media coverage sharing"@en ;
dct:issued             "2021-02-25T20:30:50Z"^^xsd:dateTimeStamp  ;
prov:wasAttributedTo  eg:TrackingSystemR2D2  ;
splog:event <<eg:JohnSmith  eg:share  <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> >> . 

<<eg:JohnSmith  eg:share  <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> >>  rdf:type   splog:SharingEvent ;
    splog:recipient    eg:recipient1;
    dct:title               "tracking media sharing as part of the premier league"@en ;
    splog:dataSubject       eg:JohnSmith ;
    dct:description         "We tracked a new sharing of match materials"@en ;
    splog:transactionTime   "2021-02-25T20:30:50Z"^^xsd:dateTimeStamp ;
    splog:validityTime      "2021-02-25T20:30:00Z"^^xsd:dateTime  ;
    splog:message           "sharing of match resources ... tracked " ;
    splog:instanceData  <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage>  ;
    splog:occurs  "2021-02-25T20:30:50Z"^^xsd:dateTime . 
                                                  
```
A set of GUCON-star rules. An example of a rule describing a permission stating that the sharing  of specific extracts from a football match, such as highlights, photographs, and interviews, is only allowed after a specified time or event (e.g., the end of the match), can be expressed using GUCON-star as follows:

```
{ ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.example.org#Person> .
?x <http://www.example.org#position> <http://www.example.org#Journalist> .
<< ?x <http://www.example.org#share> <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> >>  <http://purl.org/specialprivacy/splog#occurs>  ?t .
FILTER (?t > "2021-02-25T21:00:00Z"^^<http://www.w3.org/2001/XMLSchema#dateTime> ) }
-> 
{ A {?x <http://www.example.org#share> <http://example.com/MediaCoverage/l.premierleague.com-2020-coverage> } }

``` 
# Output

The output specifies wether a specific Knowledge Base is compliant or not with the rules. Using our example, eg:JohnSmith is non-complaint with the rule specified above.

# How to use our framework
Run the jar under the target folder. The JAR can be invoked from the command line using the following command:
```
java -jar gucon-reified-0.0.1-SNAPSHOT.jar arg1 arg2
```
Where arg1 is the path for the KB file (.ttl), and arg2 is the path for the rules (.txt).
