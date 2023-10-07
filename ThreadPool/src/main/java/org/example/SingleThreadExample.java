package org.example;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExample {
    public static void execute() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hi, this is runnable");
            }
        });

        executor.submit(() -> {
            var timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("hello, from timer each second");
                }
            }, 1000, 1000);


            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    timer.cancel();
                    timer.purge();
                }
            }, 2000);


        });


        executor.submit(() -> {
            // impossible to stop whiie infinite loop from executor, 'cause no reference to thread
            // in other cse we could use thread.
            // while (true)
            {
                System.out.println("hello, there, must be infinite here");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
            executor. shutdownNow();
            System.out.println("thread was shut down");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
