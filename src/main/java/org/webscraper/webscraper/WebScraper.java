package org.webscraper.webscraper;

import java.util.List;
import java.util.concurrent.*;
import org.webscraper.Scraper;
import org.webscraper.concurrentservice.ConcurrentService;


public class WebScraper {

    Scraper scraper;
    ConcurrentService concurrentService;

    ConcurrentLinkedQueue<String> found = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<String> toScrape = new ConcurrentLinkedQueue<>();
    String topDomain;

    public WebScraper(Scraper scraper, ConcurrentService service, String topDomain) {
        this.scraper = scraper;
        concurrentService = service;
        this.topDomain = topDomain;
        toScrape.offer(topDomain);
        found.offer(topDomain);
    }

    public void run() {
        try {
            concurrentService.run(this::mainLoop);

            found.forEach(System.out::println);
            System.out.println(String.format("WebScrape finished, %s found in total", found.size()));
        } catch (Exception e) {
            System.out.println(String.format("WebScrape failed, %s", e.getMessage()));
        }
        finally {
            concurrentService.shutdown();
        }
    }

    void mainLoop() {
        while (!toScrape.isEmpty()) {
            System.out.println(String.format("left to scrape: %s / %s", toScrape.size(), found.size()));

            String next = toScrape.poll();

            concurrentService.run(() -> evaluateUrl(next));
        }
    }

    void evaluateUrl(String newUrl) {
        List<String> newUrlList = scraper.scrape(newUrl)
            .stream()
            .filter(this::filter)
            .toList();

        found.addAll(newUrlList);
        toScrape.addAll(newUrlList);
    }

    boolean filter(String link) {
        return !found.contains(link)
            && link.startsWith(topDomain);
    }

    // package-private for ease of testing
    ConcurrentLinkedQueue<String> getFound() {
        return found;
    }

    ConcurrentLinkedQueue<String> getToScrape() {
        return toScrape;
    }
}
