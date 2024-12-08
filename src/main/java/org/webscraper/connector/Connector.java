package org.webscraper.connector;

import org.jsoup.nodes.Document;

public interface Connector {

    Document connectToWebsite(String url);

}
