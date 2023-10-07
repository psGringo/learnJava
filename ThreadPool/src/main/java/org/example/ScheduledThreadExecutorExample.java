package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadExecutorExample {
    public static void execute() {
        var executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("hi, this is scheduled executor");
        }, 0, 1, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdownNow();
    }

}
