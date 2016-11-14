package main;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import lemm.StanfordLemmatizer;
import parser.DBPediaAgent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public String readFromFile() {
        String text = "At eight o'clock on Thursday morning\n" +
                "Arthur didn't feel very good. churches, walking";
        text = "Probably the path to Git executable is not valid.";
        return text;
    }

    public void run() {

        StanfordLemmatizer slem = new StanfordLemmatizer();
        List<String> lemmatizedWords = slem.lemmatize(readFromFile());
        List<Word> words = new ArrayList<>();

        for (String lemmatizedWord : lemmatizedWords) {
            String wrong = " .,?!";
            if (!wrong.contains(lemmatizedWord) && !lemmatizedWord.contains("'")) {
                Word word = new Word(lemmatizedWord.substring(0, 1).toUpperCase() + lemmatizedWord.substring(1));
                words.add(word);
            }
        }

        for (Word word : words) {
            DBPediaAgent dbPediaAgent = new DBPediaAgent(word);
            dbPediaAgent.getValuesFromJSON();
        }

        /*Word word = new Word("Horse");
        word.setDBpediaClass("Mammal");
        words.add(word);*/

        Document xml = createXMLFromWordList(words);
        writeXMLToFile(xml);
    }

    private void writeXMLToFile(Document xml) {
        try {
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer =
                    transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xml);

            // write the content into xml file
            Result output = new StreamResult(new File("output.xml"));
            Source input = new DOMSource(xml);
            transformer.transform(input, output);

            // Output to console for testing
            StreamResult consoleResult =
                    new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Document createXMLFromWordList(List<Word> words) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =
                    dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("words");
            doc.appendChild(rootElement);

            for (Word word : words) {
                //  word element
                Element element = doc.createElement("word");
                rootElement.appendChild(element);

                if (word.getDBpediaClass() != null) {
                    // setting attribute to element
                    Attr classAttr = doc.createAttribute("class");
                    classAttr.setValue(word.getDBpediaClass());
                    element.setAttributeNode(classAttr);
                }

                if (word.getDBpediaTypes().size() > 0) {
                    Attr typeAttr = doc.createAttribute("type");
                    StringBuilder sb = new StringBuilder();
                    for (String type : word.getDBpediaTypes()) {
                        sb.append(" " + type);
                    }
                    typeAttr.setValue(sb.toString().substring(1));
                    element.setAttributeNode(typeAttr);
                }
                element.appendChild(
                        doc.createTextNode(word.getValue())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
}
