package main;

import lemm.StanfordLemmatizer;
import parser.MyJSONParser;
import parser.Parser;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();

        /*String word = "Horse";
        String url = "http://dbpedia.org/" +
                "sparql?default-graph-uri=http%3A%2F%2Fdbpedia." +
                "org&query=PREFIX%20dbr%3A%20%3Chttp%3A%2F%2Fdbpedia." +
                "org%2Fresource%2F%3E%0D%0APREFIX%20dbo%3A%20%3Chttp%3A%2F%2Fdbpedia." +
                "org%2Fontology%2F%3E%0D%0ASELECT%20*%20WHERE%20%7B%0D%0A%7B%0D%0A%20%20dbr%3A" +
                word +
                "%20dbo%3Aclass%20%3Fclass%0D%0A%7D%0D%0AUNION%7B%0D%0A%20%20dbr%3A" +
                word +
                "%20rdf%" +
                "3Atype%20%3Ftype%0D%0A%7D%0D%0A%7D&format=application%2Fsparql-results%2Bjson&CXML" +
                "_redir_for_subjs=121&CXML_redir_for_hrefs=&timeout=30000&debug=on";
        Parser parser = new Parser(url);
        parser.getCountryListFromJSON();*/
    }
}
