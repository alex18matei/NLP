package main;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

import lemm.StanfordLemmatizer;
import parser.DBPediaAgent;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public String readFromFile() {
        String text = "At eight o'clock on Thursday morning\n" +
                "Arthur didn't feel very good. churches, walking";
        return text;
    }

    public void run() {

        StanfordLemmatizer slem = new StanfordLemmatizer();
        List<String> lemmatizedWords = slem.lemmatize(readFromFile());
        List<Word> words = new ArrayList<>();

        /*for(String lemmatizedWord : lemmatizedWords){
            String wrong = " .,?!";
            if(!wrong.contains(lemmatizedWord) && !lemmatizedWord.contains("'")){
                Word word = new Word(lemmatizedWord.substring(0,1).toUpperCase() + lemmatizedWord.substring(1));
                words.add(word);
            }
        }

        for(Word word : words){
            DBPediaAgent dbPediaAgent = new DBPediaAgent(word);
            dbPediaAgent.getValuesFromJSON();
        }*/

        Word word = new Word("Horse");word.setDBpediaClass("Mammal");
        words.add(word);
        String xml = createXMLFromWordList(words);
        /*writeXMLToFile(xml);*/
    }

    private void writeXMLToFile(String xml) {
    }

    private String createXMLFromWordList(List<Word> words) {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =
                    dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("words");
            doc.appendChild(rootElement);

            for (Word word : words){
                //  word element
                Element element = doc.createElement("word");
                rootElement.appendChild(element);

                // setting attribute to element
                Attr attr = doc.createAttribute("class");
                attr.setValue(word.getDBpediaClass());
                element.setAttributeNode(attr);
                element.appendChild(
                        doc.createTextNode(word.getValue())
                );
            }

            // write the content into xml file
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer =
                    transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            /*StreamResult result =
                    new StreamResult(new File("C:\\words.xml"));
            transformer.transform(source, result);*/
            // Output to console for testing
            StreamResult consoleResult =
                    new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
