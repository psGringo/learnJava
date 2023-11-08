package org.example;

import java.util.concurrent.Executors;

public class Counter {

    private static int count = 0;

    public static synchronized int getCount() {
        return count;
    }

    public static synchronized void increase(int sleepTimeMiliseconds) {
        count++;
        try {
            Thread.sleep(sleepTimeMiliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void decrease(int sleepTimeMiliseconds) {
        count--;
        try {
            Thread.sleep(sleepTimeMiliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void execute() {
        var executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            while (true) {
                increase(1000);
                System.out.printf("count is: %s%n", count);
            }
        });

        executorService.execute(() -> {
            while (true) {
                int count = getCount();
                decrease(2000);
                System.out.printf("count is: %s%n", count);
            }
        });
    }
}
