package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MultipleDeadlockExample {

    static List<Hacker> hackers = List.of(
            new Hacker("Kevin Mitnick", 100),
            new Hacker("SomebodyElse", 50),
            new Hacker("AnotherOne", 48)
    );


    public static void executeWithPossibleDeadlock() {
        Runnable runnable = () -> {
            Hacker.processWithDeadlock(hackers.get(0), hackers.get(1), hackers.get(2));
        };

        Runnable anotherRunnable = () -> {
            Hacker.processWithDeadlock(hackers.get(2), hackers.get(1), hackers.get(0));
        };

        execute(runnable, anotherRunnable);
    }


    public static void executeWithDecisionOfDeadLock() {
        Runnable runnable = () -> {
            Hacker.processWithSolveDeadLock(hackers.get(0), hackers.get(1), hackers.get(2));
        };

        Runnable anotherRunnable = () -> {
            Hacker.processWithSolveDeadLock(hackers.get(2), hackers.get(1), hackers.get(0));
        };

        execute(runnable, anotherRunnable);
    }

    static void execute(Runnable runnable, Runnable anotherRunnable) {
        var thread = new Thread(runnable);
        var anotherThread = new Thread(anotherRunnable);

        thread.start();
        anotherThread.start();

        try {
            thread.join();
            anotherThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("game over");
    }

}

class Hacker {
    public String name;
    public int skill;

    public Hacker(String name, int skill) {
        this.name = name;
        this.skill = skill;
    }

    public static void processWithDeadlock(Hacker hacker, Hacker hacker2, Hacker hacker3) {
        synchronized (hacker) {
            System.out.printf("Thread: %s, resource %s is captured%n", Thread.currentThread().getName(), hacker.name);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (hacker2) {
                System.out.printf("Thread: %s, resource %s is captured%n", Thread.currentThread().getName(), hacker2.name);
                synchronized (hacker3) {
                    System.out.printf("Thread: %s, resource %s is captured%n", Thread.currentThread().getName(), hacker3.name);
                    System.out.printf("Thread: %s, done%n", Thread.currentThread().getName());
                }
            }
        }
    }

    public static void processWithSolveDeadLock(Hacker hacker, Hacker hacker2, Hacker hacker3) {
        // threads should have one order to not to have deadlock
        // so lets collect them and sort synchronized objects
        List<Hacker> hackers = new ArrayList<>();
        hackers.add(hacker);
        hackers.add(hacker2);
        hackers.add(hacker3);

        hackers.sort(Comparator.comparingInt(a -> a.skill));

        synchronized (hackers.get(0)) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (hackers.get(1)) {
                synchronized (hackers.get(2)) {
                    System.out.println("done");
                }
            }
        }
    }
}


