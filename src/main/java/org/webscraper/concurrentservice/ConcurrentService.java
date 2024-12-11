package org.webscraper.concurrentservice;

public interface ConcurrentService {
    void run(Runnable runnable);

    void shutdown() ;
}
