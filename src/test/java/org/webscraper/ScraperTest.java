package org.webscraper;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScraperTest {

    TestConnector testConnector = new TestConnector();

    @DisplayName("""
        Given a Simple website
        it extracts the links from it
        ignoring duplicates
        """)
    @Test
    void scrape() {

        testConnector.setTestFilePath("src/test/java/org/webscraper/simplewebsite.html");

        List<String> links = new Scraper(testConnector).scrape("https://monzo.com");

        assertThat(links.size()).isEqualTo(2);
        assertThat(links.get(0)).isEqualTo("https://monzo.com");
        assertThat(links.get(0)).isEqualTo("https://mondo.monzo.com");
    }

    @DisplayName("""
        Given a Exception is raised
        returns a empty list
        """)
    @Test
    void scrapeException() {
        List<String> links = new Scraper(testConnector).scrape("https://monzo.com");
        assertThat(links.size()).isEqualTo(0);
    }

    // once level logging implemented add test to check how the different error types are handled
}