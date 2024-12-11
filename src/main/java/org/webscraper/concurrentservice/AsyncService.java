package org.webscraper.concurrentservice;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncService implements ConcurrentService {
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    public void run(Runnable runnable) {
        CompletableFuture.runAsync(runnable, executor).join();
    }

    public void shutdown() { executor.shutdown(); }
}
