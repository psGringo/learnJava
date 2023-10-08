package org.example;

public class Main {
    public static void main(String[] args) {
        var task = new Task();
        var yieldingThread = new Thread(task);
        yieldingThread.start();
        System.out.println("main thread: yieldingThread will always give quants to current thread");
    }

    static class Task implements Runnable {

        public volatile boolean ready;
        public volatile int number;

        @Override
        public void run() {
            while (!ready) {
                try {
                    Thread.sleep(1000);
                    System.out.println("another thread: just gave quants of processor time to another thread...");
                    Thread.yield();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            System.out.println(number);
        }
    }
}