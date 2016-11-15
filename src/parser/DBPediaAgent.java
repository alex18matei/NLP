package parser;


import main.Word;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DBPediaAgent {
    protected MyJSONParser parser;
    protected Word word;
    protected String url;

    protected DBPediaAgent(Word word) {
        this.word = word;
        this.setUrl(word.getValue());
        parser = new MyJSONParser(url);
    }

    public abstract void setUrl(String word);

    public abstract void getValues();

}
