package org.webscraper;


import org.webscraper.connector.ConnectorImpl;

public class Main {
    public static void main(String[] args) {

        new WebScraper(
            new ConnectorImpl()
        ).run("https://monzo.com");
    }
}