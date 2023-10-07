package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryExample {
    public static void execute() {
        ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "myCustomThread") {
                };
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;
            }


        });

        executor.submit(() -> {
            System.out.println("hi, there, this is a custom thread created with thread factory");
        });
    }
}
