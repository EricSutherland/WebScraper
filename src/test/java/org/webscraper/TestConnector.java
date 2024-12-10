package org.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.webscraper.connector.Connector;

import java.io.File;

public class TestConnector  implements Connector {
    String filePath;

    public void setTestFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Document connectToWebsite(String ignored) {
        File input =  new File(filePath);

        try {
            return Jsoup.parse(input, "UTF-8");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
