package org.webscraper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScraperTest {

    TestConnector testConnector = new TestConnector();

    @DisplayName("""
    Given a Simple website
    it extracts the links from it
    ignoring nonmatching urls
    """)
    @Test
    void scrape() {

        testConnector.setFilePath("src/test/java/org/webscraper/simplewebsite.html");

        Stream<String> links =  new Scraper(testConnector).scrape("monzo.com");

        assertThat(links.count()).isEqualTo(1);

    }
}