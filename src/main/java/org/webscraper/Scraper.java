package org.webscraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.webscraper.connector.Connector;

import java.util.stream.Stream;

public class Scraper {

    Connector connector;

    public Scraper(Connector connector) {
        this.connector = connector;
    }

    Stream<String> scrape(String url) {

        Document website = connector.connectToWebsite(url);

        Elements linkElements = website.select("a[href]");

        return linkElements
                .stream()
                .map(this::extractLink)
                .filter(link -> link.contains(url));
    }


    private String extractLink(Element element) {
        return element.attr("abs:href");
    }
}
