package org.example;

public class KnightDeadLockExample {
    public static void execute() {
        Knight whiteKnight = new Knight("whiteKniht");
        Knight blackKnight = new Knight("blackKnight");

        var thread1 = new Thread(() -> {
            Knight.kill(whiteKnight, blackKnight);
        });
        var thread2 = new Thread(() -> {
            Knight.kill(blackKnight, whiteKnight);
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(whiteKnight);
        System.out.println(blackKnight);
        System.out.println("game over");
    }
}

class Knight {
    public int live = 100;
    public int experience = 0;

    private String name;

    public String getName() {
        return name;
    }

    public Knight(String name) {
        this.name = name;
    }

    public static void kill(Knight knightOne, Knight knightAnother) {
        System.out.printf("Thread: %s, trying to capture resources...%n", Thread.currentThread().getName());
        synchronized (knightOne) {
            try {
                System.out.printf("Thread: %s, resource %s is captured%n", Thread.currentThread().getName(), knightOne.getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (knightAnother) {
                System.out.printf("Thread: %s, resource %s is captured%n", Thread.currentThread().getName(), knightAnother.getName());
                knightAnother.live = 0;
                knightOne.experience += 100;
            }
        }
    }
}
