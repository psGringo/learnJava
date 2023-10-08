package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Main thread starting..");

        Thread thread = new Thread(() -> {
            try {
                System.out.println("Child thread doing smth...");
                Thread.sleep(10000);
                System.out.println("Child thread has done its work");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        try {
            thread.start();
            thread.join(); // main thread waits child thread endlessly
            System.out.println("Main thread in game again");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}