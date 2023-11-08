package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadExecutorExample {
    public static void execute() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            final int taskNumber = i;
            var future = executorService.submit(() -> {
                try {
                    System.out.printf("Thread: %s, taskNumber:%d %n", Thread.currentThread().getName(), taskNumber);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
