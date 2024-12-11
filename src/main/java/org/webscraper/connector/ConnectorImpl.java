package org.webscraper.connector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConnectorImpl implements Connector {

    @Override
    public Document connectToWebsite(String url) throws ConnectionException {
        try {
            return Jsoup
                .connect(url)
                .get();
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }
}
