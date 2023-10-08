package org.example;

public class Volatile {
    // this ensures that visiblity from differernt threads will be equal
    static volatile int count = 0;

    static synchronized void increase() {
        count++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void read() {
        try {
            System.out.printf("count: %d%n", count);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void execute() {
        new Thread(() -> {
            while (true) {
                increase();
            }
        }).start();


        new Thread(() -> {
            while (true) {
                increase();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                read();
            }
        }).start();
    }
}
