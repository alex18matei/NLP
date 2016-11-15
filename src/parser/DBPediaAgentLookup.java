package parser;

import main.Word;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class DBPediaAgentLookup extends DBPediaAgent{

    public DBPediaAgentLookup(Word word) {
        super(word);
    }

    public void setUrl(String word) {
        url = "http://lookup.dbpedia.org/api/search/KeywordSearch?QueryString=" +
                word;
    }

    public void getValues() {
        JSONArray arrayOfResults = null;
        try {
            JSONObject json = XML.toJSONObject(parser.getUriContent()).getJSONObject("ArrayOfResult");

            if (json.has("Result")) {
                arrayOfResults = json.getJSONArray("Result");
                for (int i = 0; i < arrayOfResults.length(); ++i) {
                    JSONObject resultItem = (JSONObject) arrayOfResults.get(i);

                    if (resultItem.getString("Classes").length() != 0) {
                        JSONObject classesObj = new JSONObject(resultItem.getString("Classes"));
                        JSONArray classes = classesObj.getJSONArray("Class");

                        for (int j = 0; j < classes.length(); ++j) {
                            JSONObject classItem = (JSONObject) classes.get(j);
                            word.getDBpediaClass().add(classItem.getString("Label"));
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
