package org.webscraper.connector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConnectorImp implements Connector {

    @Override
    public Document connectToWebsite(String url) {
        try {

            return Jsoup.connect(url).get();

        }
        catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        return null;
    }
}
