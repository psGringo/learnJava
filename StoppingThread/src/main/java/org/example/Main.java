package org.example;

public class Main {
    public static void main(String[] args) {
        FirstExample();
        SecondExample();
    }

    static void FirstExample() {
        var task = new Task();
        new Thread(task).start();
        try {
            Thread.sleep(5000);
            task.cancel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void SecondExample() {
        var thread = new Thread(new AnotherTask());
        try {
            thread.start();
            Thread.sleep(5000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
            ;
        }
    }
}

class Task implements Runnable {

    // volatile means no caching
    private volatile boolean needCancel = false;

    public void cancel() {
        needCancel = true;
    }

    @Override
    public void run() {
        while (!needCancel) {
            try {
                Thread.sleep(1000);
                System.out.println("Tik");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class AnotherTask implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                System.out.println("Tik2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}