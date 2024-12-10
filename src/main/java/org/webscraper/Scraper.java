package org.webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.webscraper.connector.ConnectionException;
import org.webscraper.connector.Connector;

import java.util.Collections;
import java.util.List;

public class Scraper {

    Connector connector;

    public Scraper(Connector connector) {
        this.connector = connector;
    }

    List<String> scrape(String url) {

        try {
            Document website = connector.connectToWebsite(url);
            Elements linkElements = website.select("a[href]");

            return linkElements
                .stream()
                .map(this::extractLink)
                .distinct()
                .toList();
        } catch (Exception e) {
            handleException(url, e);

            return Collections.emptyList();
        }
    }

    private void handleException(String url, Exception e) {
        if(e instanceof ConnectionException) {
            System.out.printf("Error connecting to website, skipping scrape for: %s", url);
        } else {
            System.out.printf("Unknown error occurred: %s", e.getMessage());
            // raise alert to be investigated
        }
    }

    private String extractLink(Element element) {
        return element.attr("abs:href");
    }
}
