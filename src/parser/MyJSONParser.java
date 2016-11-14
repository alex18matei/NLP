package parser;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class MyJSONParser {

    private String uri;
    private String uriContent;

    public MyJSONParser(String uri) {
        this.uri = uri;
        this.uriContent = this.getContentFromURI();
    }

    private String getContentFromURI() {
        URL url = null;
        Scanner scan = null;
        String content = null;

        try {
            url = new URL(this.uri);
            scan = new Scanner(url.openStream());
            content = "";
            while (scan.hasNext())
                content += scan.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
        return content;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUriContent() {
        return uriContent;
    }

}
