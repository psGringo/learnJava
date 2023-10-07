package org.example;

public class Main {
    public static void main(String[] args) {
        var task = new Task();
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}

class Task implements Runnable {
    int counter = 0;
    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    ThreadLocal<Integer> threadLocalInitialized = ThreadLocal.withInitial(() -> 0);

    @Override
    public void run() {
        counter++;

        if (threadLocal.get() == null) {
            threadLocal.set(0);
        } else {
            threadLocal.set(threadLocal.get() + 1);
        }

        threadLocalInitialized.set(threadLocalInitialized.get() + 1);

        System.out.printf("counter: %d%n", counter);
        System.out.printf("threadLocalCounter: %d%n", threadLocal.get());
        System.out.printf("threadLocalInitialized: %d%n", threadLocalInitialized.get());
    }
}