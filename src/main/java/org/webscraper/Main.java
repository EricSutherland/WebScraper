package org.webscraper;

import org.webscraper.concurrentservice.AsyncService;
import org.webscraper.connector.ConnectorImpl;
import org.webscraper.input.ConsoleInput;
import org.webscraper.webcrawler.WebCrawler;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the WebScraper");
        String domain = new ConsoleInput().run();

        new WebCrawler(
            new Scraper(new ConnectorImpl()),
            new AsyncService(),
            domain
        ).run();
    }
}