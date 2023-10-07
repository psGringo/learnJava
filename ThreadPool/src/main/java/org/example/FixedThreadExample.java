package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExample {
    public static void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            final int id = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);

                    System.out.printf("Thread: %s, taskNumber: %d %n", Thread.currentThread().getName(), id);
                } catch (InterruptedException e) {
                    System.out.println("sleep has been interrupted");
                }
            });
        }

        var incomplete = executorService.shutdownNow();
        incomplete.forEach((runnable) -> {
            System.out.printf("%s %n", runnable.toString());
            System.out.println("incomplete tasks");
        });
    }
}


