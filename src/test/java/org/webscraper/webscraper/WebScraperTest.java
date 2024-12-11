package org.webscraper.webscraper;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webscraper.Scraper;
import org.webscraper.concurrentservice.ConcurrentService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebScraperTest {

    ConcurrentService concurrentService = mock(ConcurrentService.class);

    Scraper scraper = mock(Scraper.class);

    @DisplayName("""
        When run is called
        ConcurrentService run is called once
        ConcurrentService shutdown is called once
        """)
    @Test
    void testRun() {
        WebScraper webScraper = new WebScraper(
            scraper,
            concurrentService,
            "test"
        );

        webScraper.run();

        verify(concurrentService, times(1)).run(any());
        verify(concurrentService, times(1)).shutdown();
    }

    @DisplayName("""
        When run is called
        and a error is raised
        ConcurrentService shutdown is still called once
        """)
    @Test
    void testRunError() {
        WebScraper webScraper = new WebScraper(
            scraper,
            concurrentService,
            "test"
        );

        doThrow(RuntimeException.class).when(concurrentService).run(any());

        webScraper.run();

        verify(concurrentService, times(1)).shutdown();
    }

    @DisplayName("""
        When mainLoop is called
        ConcurrentService run is called
        amount of times matching
        the size of toScrape
        """)
    @Test
    void testWebScraper() {
        WebScraper webScraper = new WebScraper(
            scraper,
            concurrentService,
            "test"
        );

        when(scraper.scrape(any())).thenReturn(List.of("test2"));
        webScraper.evaluateUrl("test2"); // increase toScrape to 2


        webScraper.mainLoop();

        verify(concurrentService, times(2)).run(any());
    }

    @DisplayName("""
        When evaluateUrl is called
        found urls are added to queues
        with existing topDomain
        """)
    @Test
    void testEvaluateUrl() {
        WebScraper webScraper = new WebScraper(
            scraper,
            concurrentService,
            "test"
        );


        when(scraper.scrape(any())).thenReturn(List.of("test2"));
        webScraper.evaluateUrl("test2");

        List<String> testList = List.of("test", "test2");
        assertThat(webScraper.getFound().stream().toList()).isEqualTo(testList);
        assertThat(webScraper.getToScrape().stream().toList()).isEqualTo(testList);
    }

    @DisplayName("""
        Given filter is called with a value
        that matches the top domain
        and isn't in found
        the it returns true
        """)
    @Test
    void testFilterTopDomainMatches() {
        WebScraper webScraper = new WebScraper(
            scraper,
            concurrentService,
            "test"
        );

        assertThat(webScraper.filter("test2")).isEqualTo(true);
    }

    @DisplayName("""
        Given filter is called with a value
        that doesn't match the top domain
        and isn't in found
        the it returns true
        """)
    @Test
    void testFilterTopDomainDoesntMatches() {
        WebScraper webScraper = new WebScraper(
            scraper,
            concurrentService,
            "test"
        );

        assertThat(webScraper.filter("monzo")).isEqualTo(false);
    }

    @DisplayName("""
        Given filter is called with a value
        that matches the top domain
        and is in found
        the it returns true
        """)
    @Test
    void testFilterAlreadyFound() {
        WebScraper webScraper = new WebScraper(
            scraper,
            concurrentService,
            "test"
        );

        // run evaluateUrl to populate found list
        when(scraper.scrape(any())).thenReturn(List.of("test2"));
        webScraper.evaluateUrl("test2");

        assertThat(webScraper.filter("test2")).isEqualTo(false);
    }
}