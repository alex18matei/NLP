package parser;

import main.Word;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBPediaAgentSparql extends DBPediaAgent{

    public DBPediaAgentSparql(Word word) {
        super(word);
    }

    public void setUrl(String word) {
        url = "http://dbpedia.org/" +
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
    }

    public void getValues() {
        JSONArray array = null;
        try {
            JSONObject content = new JSONObject(parser.getUriContent());
            array = content.getJSONObject("results").getJSONArray("bindings");
            for (int i = 0; i < array.length(); ++i) {
                JSONObject obj = (JSONObject) array.get(i);
                if (obj.has("class")) {
                    JSONObject classObject = obj.getJSONObject("class");
                    word.getDBpediaClass().add(getValueFromURI(classObject.getString("value")));
                }
                if (obj.has("type")) {
                    JSONObject typeObject = obj.getJSONObject("type");
                    word.getDBpediaTypes().add(getValueFromURI(typeObject.getString("value")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getValueFromURI(String value) {
        String[] splits = value.split("/");
        return splits[splits.length-1].split("[0-9]")[0];
    }
}
