package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Garden {
    public final List<String> fruits = new ArrayList<String>();
    public final List<String> vegetables = new ArrayList<String>();

    public synchronized void addFruit(int index, String fruit) {
        fruits.add(index, fruit);
    }

    public synchronized void removeFruit(int index) {
        fruits.remove(index);
    }

    public synchronized void addVegetable(int index, String vegetable) {
        vegetables.add(index, vegetable);
    }

    public synchronized void removeVegetable(int index) {
        vegetables.remove(index);
    }

    public static void execute() {
        var garden = new Garden();
        var executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(() -> {
            garden.addFruit(0, "apple");
            System.out.println(garden.fruits);
        }, 0, 1, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(() -> {
            garden.addFruit(0, "orange");
            System.out.println(garden.fruits);
        }, 0, 1, TimeUnit.SECONDS);

    }

}
