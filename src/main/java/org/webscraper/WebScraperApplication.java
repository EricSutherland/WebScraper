package org.webscraper;

import org.webscraper.connector.ConnectorImp;

public class WebScraperApplication {

    Scraper scraper;
    public WebScraperApplication() {
        ConnectorImp connector = new ConnectorImp();
        scraper = new Scraper(connector);
    }

    void run()
    {
        scraper.scrape("https://monzo.com")
                .forEach(System.out::println);
    }
}
