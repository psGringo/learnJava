package org.example;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Notes {

    static ArrayList<String> notes = new ArrayList<>();

    public static void AddNote(String note, int sleepMiliseconds) {
        synchronized (notes) {
            try {
                Thread.sleep(sleepMiliseconds);
                notes.add(note);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            while (true) {
                AddNote("note added", 3000);
                System.out.printf("note added by %s%n", Thread.currentThread().getName());
            }
        });

        executorService.execute(() -> {
            while (true) {
                AddNote("another note added", 1000);
                System.out.printf("another note added by %s%n", Thread.currentThread().getName());
            }
        });

    }

}
