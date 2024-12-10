package org.webscraper;

import java.util.List;
import java.util.concurrent.*;

import org.webscraper.connector.Connector;


public class WebScraper {

    Scraper scraper;
    final ExecutorService executor = Executors.newFixedThreadPool(20);
    ConcurrentLinkedQueue<String> found = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<String> toScrape = new ConcurrentLinkedQueue<>();
    String topDomain;

    public WebScraper(Connector connector) {
        scraper = new Scraper(connector);
    }

    void run(String topDomain) {
        this.topDomain = topDomain;
        toScrape.offer(topDomain);

        try {
            CompletableFuture
                .runAsync(this::mainLoop)
                .join();

            executor.shutdown();

            System.out.println(found);
            System.out.println("WebScraperApplication finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void mainLoop() {
        while (!toScrape.isEmpty()) {
            System.out.println(String.format("left to scrape: %s / %s", toScrape.size(), found.size()));

            String next = toScrape.poll();
            futureRun(next).join();
        }
    }

    CompletableFuture<Void> futureRun(String url) {
        return CompletableFuture.runAsync(() -> evaluateUrl(url), executor);
    }


    void evaluateUrl(String newUrl) {
        List<String> temp = scraper.scrape(newUrl)
            .stream()
            .filter(this::filter)
            .distinct()
            .toList();

        found.addAll(temp);
        toScrape.addAll(temp);
    }

    boolean filter(String link) {
        return !found.contains(link)
            && link.startsWith(topDomain);
    }
}
