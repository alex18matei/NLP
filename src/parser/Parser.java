package parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.image.Image;

public class Parser {

    private MyJSONParser parser;

    public Parser() {
    }

    public Parser(String url) {
        parser = new MyJSONParser(url);
    }

    public void getCountryListFromJSON() {
        List<String> results = new ArrayList<>();
        JSONArray array = null;
        try {
            JSONObject content = new JSONObject(parser.getUriContent());
            array = content.getJSONObject("results").getJSONArray("bindings");
            for (int i = 0; i < array.length(); ++i) {
                JSONObject obj = (JSONObject) array.get(i);
                if (obj.has("class")) {
                    JSONObject classObject = obj.getJSONObject("class");
                    results.add(classObject.getString("value"));
                }
                if (obj.has("type")) {
                    JSONObject typeObject = obj.getJSONObject("type");
                    results.add(typeObject.getString("value"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(results.toString());
    }

    public MyJSONParser getParser() {
        return parser;
    }

    public void setParser(MyJSONParser parser) {
        this.parser = parser;
    }
}
