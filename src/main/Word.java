package main;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private List<String> DBpediaClass;
    private List<String> DBpediaTypes;
    private String value;

    public Word(String value) {
        this.value = value;
        DBpediaTypes = new ArrayList<>();
        DBpediaClass = new ArrayList<>();
    }

    public List<String> getDBpediaClass() {
        return DBpediaClass;
    }

    public void setDBpediaClass(List<String> DBpediaClass) {
        this.DBpediaClass = DBpediaClass;
    }

    public List<String> getDBpediaTypes() {
        return DBpediaTypes;
    }

    public void setDBpediaTypes(List<String> DBpediaTypes) {
        this.DBpediaTypes = DBpediaTypes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nValue: ").append(value);
        sb.append("\nClass: ");
        for(String type : DBpediaClass){
            sb.append(type).append(" ");
        }
        sb.append("\nTypes: ");
        for(String type : DBpediaTypes){
            sb.append(type).append(" ");
        }
        return sb.toString();
    }
}
