package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StealingThreadsExample {
    public static void execute() {
        Runnable task = () -> {
            try {
                System.out.println("Done");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ExecutorService executorService = Executors.newWorkStealingPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(task);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


