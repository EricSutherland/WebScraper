package org.webscraper;

import org.webscraper.concurrentservice.AsyncService;
import org.webscraper.connector.ConnectorImpl;
import org.webscraper.webscraper.WebScraper;

public class Main {
    public static void main(String[] args) {

        new WebScraper(
            new Scraper(new ConnectorImpl()),
            new AsyncService(),
            "https://monzo.com"
        ).run();
    }
}