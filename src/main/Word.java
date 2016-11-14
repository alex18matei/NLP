package main;

import java.util.List;

public class Word {
    private String DBpediaClass;
    private List<String> DBpediaTypes;
    private String value;

    public Word(String value) {
        this.value = value;
    }

    public String getDBpediaClass() {
        return DBpediaClass;
    }

    public void setDBpediaClass(String DBpediaClass) {
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
}
