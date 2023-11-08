package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SubmitVsExecute {
    public static void Execute() {
        submitExample();
        executeExample();
    }

    static void executeExample() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> System.out.println("this is runnable example, no return value here to wait"));
    }

    static void submitExample() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        var future = executorService.submit(() -> {
            try {
                Thread.sleep(5000);
                return "this is submit example with callable and future";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            System.out.println(future.get());
            System.out.println("hi, there");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
